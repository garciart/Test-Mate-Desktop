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
        TestMateView tv = new TestMateView();
        Settings s = new Settings();
        try {
            s.getSettingsFromFile();
        }
        catch(IOException ex) {
            tv.errorView("Unable to read settings file: " + ex.toString() + "\nApplying default settings...");
            s.setQuestionOrderSetting(Constants.QuestionOrder.DEFAULT);
            s.setTermDisplaySetting(Constants.TermDisplay.DEFISQUESTION);
            s.setProvideFeedbackSetting(Constants.ProvideFeedback.NO);
            s.saveSettingsToFile();
        }
        tv.introduction();
        boolean exitFlag = false;
        while(!exitFlag) {
            int choice = tv.mainMenuView();
            switch (choice) {
                case 1:
                    ArrayList<TestQuestion> testQuestion = (new Test()).getTest((System.getProperty("user.dir") + "\\mta-98-361-01.tmf"), s.getQuestionOrderSetting(), s.getTermDisplaySetting());
                    for(int x = 0; x < testQuestion.size(); x++) {
                        int userChoice = tv.askQuestionView(x, testQuestion.get(x));
                        if(userChoice >= 0) {
                            boolean result = (userChoice == testQuestion.get(x).getCorrectAnswerIndex());
                            if(s.getProvideFeedbackSetting() == Constants.ProvideFeedback.YES) tv.feedbackView(result, testQuestion.get(x).getExplanation());
                        }
                        else {
                            if(tv.exitView()) break;
                            else x--;
                        }
                    }
                    break;
                case 2:
                    break;
                case 3:
                    int userChoice = 1;
                    while(userChoice != 0) {
                        userChoice = tv.settingsMenuView(s.getQuestionOrderSetting().name(), s.getTermDisplaySetting().name(), s.getProvideFeedbackSetting().name());
                        switch(userChoice) {
                            case 1:
                                int settingChoice = (tv.questionOrderSettingView(s.getQuestionOrderSetting().name())) - 1;
                                if(settingChoice >= 0 && s.getQuestionOrderSetting().ordinal() != settingChoice) {
                                    s.setQuestionOrderSetting(Constants.QuestionOrder.values()[settingChoice]);
                                    s.saveSettingsToFile();
                                }
                                break;
                            case 2:
                                settingChoice = (tv.termDisplaySettingView(s.getTermDisplaySetting().name())) - 1;
                                if(settingChoice >= 0 && s.getTermDisplaySetting().ordinal() != settingChoice) {
                                    s.setTermDisplaySetting(Constants.TermDisplay.values()[settingChoice]);
                                    s.saveSettingsToFile();
                                }
                                break;
                            case 3:
                                settingChoice = (tv.provideFeedbackSettingView(s.getProvideFeedbackSetting().name())) - 1;
                                if(settingChoice >= 0 && s.getProvideFeedbackSetting().ordinal() != settingChoice) {
                                    s.setProvideFeedbackSetting(Constants.ProvideFeedback.values()[settingChoice]);
                                    s.saveSettingsToFile();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 4:
                    tv.aboutView();
                    break;
                case 5:
                    exitFlag = tv.exitView();
                    break;
                default:
                    break;
            }
        }
    }
}
    