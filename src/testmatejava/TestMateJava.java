/*
 * The MIT License
 *
 * Copyright 2018 Rob Garcia at rgarcia@rgprogramming.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package testmatejava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import testmatejava.Constants.*;

/**
 * TestMate controller class
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class TestMateJava {
    private static ArrayList<TestData> testData;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException When file cannot be opened
     */
    public static void main(String[] args) throws IOException {
        Settings s = new Settings();
        try {
            s.getSettingsFromFile();
        }
        catch(Exception ex) {
            System.out.println("Unable to read settings file: " + ex.toString());
            System.out.println("Applying default settings...");
            s.saveSettingsToFile(QuestionOrder.DEFAULT, TermDisplay.TERMFIRST, ProvideFeedback.YES);
        }
        System.out.println("questionOrderSetting = " + s.getQuestionOrderSetting());
        System.out.println("termDisplaySetting = " + s.getTermDisplaySetting());
        System.out.println("provideFeedbackSetting = " + s.getProvideFeedbackSetting());
        s.setProvideFeedbackSetting(null);
        try {
            readFile();
            for(int x = 0; x < testData.size(); x++) {
                TestData td = (TestData)testData.get(x);
                switch(td.getQuestionType()) {
                    case K:
                        KeyTerm kt = (KeyTerm)testData.get(x);
                        System.out.println(kt.getKeyTerm());
                        System.out.println(kt.getKTDefinition());
                        System.out.println(kt.getMediaType());
                        System.out.println(kt.getMediaFileName());
                        System.out.println(kt.getExplanation());
                        break;
                    case M:
                        MultipleChoice mc = (MultipleChoice)testData.get(x);
                        System.out.println(mc.getMCQuestion());
                        System.out.println(mc.getMediaType());
                        System.out.println(mc.getMediaFileName());
                        int c = 0;
                        for(String choices : mc.getMCChoices()) {
                            System.out.println(Constants.LETTERS[c] + ". " + choices);
                            c++;
                        }
                        System.out.println(mc.getExplanation());
                        break;
                    case T:
                        TrueFalse tf = (TrueFalse)testData.get(x);
                        System.out.println(tf.getTFQuestion());
                        System.out.println(tf.getMediaType());
                        System.out.println(tf.getMediaFileName());
                        System.out.println(tf.getTFAnswer());
                        System.out.println(tf.getExplanation());
                        break;
                    default:
                        break;
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        RandomNumbers rn = new RandomNumbers(50, 49, 3);
        int total = 0;
        for(int x = 0; x <= 3; x++) {
            System.out.println(x + ". " + rn.getUniqueArray()[x]);
            total += rn.getUniqueArray()[x];
        }
        System.out.println(total);
    }
    
    /**
     * Method to read file data and create an ArrayList of Animal objects
     * @return The Animal list
     * @throws InvalidAnimalException When an invalid name is encountered
     * @throws FileNotFoundException When file cannot be found
     * @throws IOException When file cannot be opened
     */    
    private static void readFile() throws FileNotFoundException, IOException {
        // Due to MultipleChoice's fluctuating size, we will use getters and setters instead of a constructor for all question types
        testData = new ArrayList<>();
        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "\\mta-98-361-01.tmf");
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String firstLine;
            while(!Utilities.isNullOrEmpty(firstLine = bufferedReader.readLine())) {
                if(firstLine.equals(QuestionType.K.toString())) {
                    KeyTerm k = new KeyTerm(QuestionType.K);
                    k.setKeyTerm(Utilities.fixEscapeCharacters(bufferedReader.readLine()));
                    MediaType tempMT = MediaType.valueOf(bufferedReader.readLine());
                    String tempMF = bufferedReader.readLine();
                    k.validateAndSetMedia(tempMT, tempMF);
                    k.setKTDefinition(Utilities.fixEscapeCharacters(bufferedReader.readLine()));
                    k.setExplanation(k.getKeyTerm() + ": " + k.getKTDefinition());
                    testData.add(k);
                }
                else if(firstLine.equals(QuestionType.M.toString())) {
                    MultipleChoice m = new MultipleChoice(QuestionType.M);
                    m.setMCQuestion(Utilities.fixEscapeCharacters(bufferedReader.readLine()));
                    MediaType tempMT = MediaType.valueOf(bufferedReader.readLine());
                    String tempMF = bufferedReader.readLine();
                    m.validateAndSetMedia(tempMT, tempMF);
                    m.setMCNumberOfChoices(Integer.parseInt(bufferedReader.readLine()));
                    for(int x = 0; x <= m.getMCNumberOfChoices(); x++) {
                        m.getMCChoices().add(Utilities.fixEscapeCharacters(bufferedReader.readLine()));
                    }
                    String tempExplanation  = Utilities.fixEscapeCharacters(bufferedReader.readLine());
                    if(tempExplanation.toLowerCase().equals("null") || Utilities.isNullOrEmpty(tempExplanation)) {
                        m.setExplanation("The answer is: " + m.getMCChoices().get(0));
                    }
                    else {
                        m.setExplanation(tempExplanation);
                    }
                    testData.add(m);
                }
                else if(firstLine.equals(QuestionType.T.toString())) {
                    TrueFalse t = new TrueFalse(QuestionType.T);
                    t.setTFQuestion(Utilities.fixEscapeCharacters(bufferedReader.readLine()));
                    MediaType tempMT = MediaType.valueOf(bufferedReader.readLine());
                    String tempMF = bufferedReader.readLine();
                    t.validateAndSetMedia(tempMT, tempMF);
                    t.setTFAnswer(Boolean.valueOf(bufferedReader.readLine()));
                    String tempExplanation  = Utilities.fixEscapeCharacters(bufferedReader.readLine());
                    if(tempExplanation.toLowerCase().equals("null") || Utilities.isNullOrEmpty(tempExplanation)) {
                        t.setExplanation("The answer is: " + t.getTFAnswer());
                    }
                    else {
                        t.setExplanation(tempExplanation);
                    }
                    testData.add(t);
                }
                else {
                    throw new IllegalArgumentException("Corrunpt data file. Check structure and values.");
                }
            }
        } 
    }
}
