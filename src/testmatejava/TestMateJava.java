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
import java.util.List;
import testmatejava.Constants.*;

/**
 * TestMate controller class
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class TestMateJava {
    private static List<TestData> testData;
    
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
        /*
        String test = "";
        if(Utilities.isNullOrEmpty(test)) {
            System.out.println("\nEmpty string!\n");
        }
        else {
            System.out.println("\nString is not empty!\n");
        }
        */
        try {
            /*
            KeyTerm td = new KeyTerm(QuestionType.K, "La", MediaType.I, "bob.png", "A note to follow so.");
            System.out.println(td.getMediaFileName());
            td.setMediaFileName("bob.mp3");
            System.out.println(td.getMediaFileName());

            readFile();
            for(int x = 0; x < testData.size(); x++) {
                // TestData td = (TestData)testData.get(x);
                switch(((TestData)testData.get(x)).getQuestionType()) {
                    case K:
                        KeyTerm kt = (KeyTerm)testData.get(x);
                        // Eventually move to readFile...
                        kt.setKeyTerm(Utilities.fixEscapeCharacters(kt.getKeyTerm()));
                        kt.setKTDefinition(Utilities.fixEscapeCharacters(kt.getKTDefinition()));
                        System.out.println(kt.getKeyTerm() + ": " + kt.getKTDefinition());
                        break;
                    case M:
                        MultipleChoice mc = (MultipleChoice)testData.get(x);
                        // Eventually move to readFile...
                        mc.setMCQuestion(Utilities.fixEscapeCharacters(mc.getMCQuestion()));
                        mc.setMCExplanation(Utilities.fixEscapeCharacters(mc.getMCExplanation()));
                        System.out.println(mc.getMCQuestion());
                        int c = 0;
                        for(String choices : mc.getMCChoices()) {
                            choices = Utilities.fixEscapeCharacters(choices);
                            System.out.println(Constants.LETTERS[c] + ". " + choices);
                            c++;
                        }
                        System.out.println(mc.getMCExplanation());
                        break;
                    case T:
                        TrueFalse tf = (TrueFalse)testData.get(x);
                        // Eventually move to readFile...
                        tf.setTFQuestion(Utilities.fixEscapeCharacters(tf.getTFQuestion()));
                        tf.setTFExplanation(Utilities.fixEscapeCharacters(tf.getTFExplanation()));
                        System.out.println(tf.getTFQuestion());
                        System.out.println(tf.getTFAnswer());
                        System.out.println(tf.getTFExplanation());
                        break;
                    default:
                        break;
                }
            }
            */
            for(int y = 0; y <= 10; y++) {
                RandomNumbers rn = new RandomNumbers(40, 6);
                Thread.sleep(1000);
                for(int x = 0; x <= 3; x++) {
                    System.out.print(x + ": " + rn.getUniqueArray()[x]);
                    System.out.println((x == rn.getIndexLocation() ? " - here!" : ""));
                }
                System.out.println();
            }
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
    }
    
    /**
     * Method to read file data and create an ArrayList of Animal objects
     * @return The Animal list
     * @throws InvalidAnimalException When an invalid name is encountered
     * @throws FileNotFoundException When file cannot be found
     * @throws IOException When file cannot be opened
     */    
    private static void readFile() throws FileNotFoundException, IOException {
        String line;
        testData = new ArrayList<>();
        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "\\mta-98-361-01.tmf");
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                if(line.equals(QuestionType.K.toString())) {
                    // MOVE Utilities.fixEscapeCharacters() to here eventually?
                    KeyTerm k = new KeyTerm(QuestionType.K);
                    k.setKeyTerm(bufferedReader.readLine());
                    MediaType tempMT = MediaType.valueOf(bufferedReader.readLine());
                    String tempMF = bufferedReader.readLine();
                    k.validateAndSetMedia(tempMT, tempMF);
                    k.setKTDefinition(bufferedReader.readLine());
                    testData.add(k);
                }
                else if(line.equals(QuestionType.M.toString())) {
                    // MOVE Utilities.fixEscapeCharacters() to here eventually?
                    MultipleChoice m = new MultipleChoice(QuestionType.M);
                    m.setMCQuestion(bufferedReader.readLine());
                    MediaType tempMT = MediaType.valueOf(bufferedReader.readLine());
                    String tempMF = bufferedReader.readLine();
                    m.validateAndSetMedia(tempMT, tempMF);
                    m.setMCNumberOfChoices(Integer.parseInt(bufferedReader.readLine()));
                    for(int x = 0; x <= m.getMCNumberOfChoices(); x++) {
                        m.getMCChoices().add(bufferedReader.readLine());
                    }
                    String tempExplanation  = bufferedReader.readLine();
                    if(tempExplanation.toLowerCase().equals("null") || Utilities.isNullOrEmpty(tempExplanation)) {
                        m.setMCExplanation("The answer is: " + m.getMCChoices().get(0));
                    }
                    else {
                        m.setMCExplanation(tempExplanation);
                    }
                    testData.add(m);
                }
                else if(line.equals(QuestionType.T.toString())) {
                    // MOVE Utilities.fixEscapeCharacters() to here eventually?
                    TrueFalse t = new TrueFalse(QuestionType.T);
                    t.setTFQuestion(bufferedReader.readLine());
                    MediaType tempMT = MediaType.valueOf(bufferedReader.readLine());
                    String tempMF = bufferedReader.readLine();
                    t.validateAndSetMedia(tempMT, tempMF);
                    t.setTFAnswer(Boolean.valueOf(bufferedReader.readLine()));
                    String tempExplanation  = bufferedReader.readLine();
                    if(tempExplanation.toLowerCase().equals("null") || Utilities.isNullOrEmpty(tempExplanation)) {
                        t.setTFExplanation("The answer is: " + t.getTFAnswer());
                    }
                    else {
                        t.setTFExplanation(tempExplanation);
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
