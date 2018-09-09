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
package testmatedesktop;

import java.io.IOException;
import java.util.ArrayList;

/**
 * TestMate controller class
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class TestMateDesktop {
    /**
     * Main method
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Settings s = new Settings();
        try {
            s.getSettingsFromFile();
        }
        catch(IOException ex) {
            System.out.println("Unable to read settings file: " + ex.toString());
            System.out.println("Applying default settings...");
            s.saveSettingsToFile(Constants.QuestionOrder.DEFAULT, Constants.TermDisplay.TERMISQUESTION, Constants.ProvideFeedback.YES);
        }
        ArrayList<TestQuestion> testQuestion = (new Test()).getTest((System.getProperty("user.dir") + "\\mta-98-361-01.tmf"), s.getQuestionOrderSetting(), s.getTermDisplaySetting());
        for(int x = 0; x < testQuestion.size(); x++) {
            System.out.println((x + 1) + ". " + testQuestion.get(x).getQuestion());
            for(int y = 0; y <= testQuestion.get(x).getNumberOfChoices(); y++) {
                System.out.println(Constants.LETTERS[y] + ". " + testQuestion.get(x).getChoices().get(y) + (y == testQuestion.get(x).getCorrectAnswerIndex() ? " - HERE!" : ""));
            }
            if(s.getProvideFeedbackSetting() == Constants.ProvideFeedback.YES) System.out.println(testQuestion.get(x).getExplanation());
            System.out.println();
        }
    }
}
