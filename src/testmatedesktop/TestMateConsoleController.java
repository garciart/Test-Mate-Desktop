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
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class TestMateConsoleController {

    /**
     * Main method
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void consoleMain(String[] args) throws IOException {
        TestMateConsoleView tcv = new TestMateConsoleView();
        Settings s = new Settings();
        try {
            s.getSettingsFromFile();
        } catch (IOException ex) {
            tcv.errorView("Unable to read settings file: " + ex.toString() + "\nApplying default settings...");
            s.setQuestionOrderSetting(Constants.QuestionOrder.DEFAULT);
            s.setTermDisplaySetting(Constants.TermDisplay.DEFISQUESTION);
            s.setProvideFeedbackSetting(Constants.ProvideFeedback.NO);
            s.saveSettingsToFile();
        }
        tcv.introduction();
        boolean exitFlag = false;
        while (!exitFlag) {
            int choice = tcv.mainMenuView();
            switch (choice) {
                case 1:
                    String testName = tcv.fileView(".tmf");
                    if (testName == null) {
                        break;
                    } else {
                        tcv.testLoadedView();
                        int correctAnswerCount = 0;
                        long startTime = System.nanoTime();
                        ArrayList<TestQuestion> testQuestion = (new Test()).getTest(testName, s.getQuestionOrderSetting(), s.getTermDisplaySetting());
                        String userResults[][] = new String[testQuestion.size()][3];
                        for (int x = 0; x < testQuestion.size(); x++) {
                            int userChoice = tcv.askQuestionView(x, testQuestion.get(x));
                            if (userChoice >= 0) {
                                boolean result = (userChoice == testQuestion.get(x).getCorrectAnswerIndex());
                                if (result) {
                                    correctAnswerCount++;
                                }
                                if (s.getProvideFeedbackSetting() == Constants.ProvideFeedback.YES) {
                                    tcv.feedbackView(result, testQuestion.get(x).getExplanation());
                                } else {
                                    userResults[x][0] = (result == true) ? "Correct." : "Incorrect.";
                                    userResults[x][1] = testQuestion.get(x).getQuestion();
                                    userResults[x][2] = testQuestion.get(x).getExplanation();
                                }
                            } else {
                                if (tcv.exitView()) {
                                    break;
                                } else {
                                    x--;
                                }
                            }
                        }
                        long endTime = System.nanoTime();
                        long elapsedTime = endTime - startTime;
                        if (s.getProvideFeedbackSetting() == Constants.ProvideFeedback.NO) {
                            tcv.resultView(testQuestion.size(), correctAnswerCount, elapsedTime, userResults);
                        } else {
                            tcv.resultView(testQuestion.size(), correctAnswerCount, elapsedTime);
                        }
                    }
                    break;
                case 2:
                    int userChoice = 1;
                    while (userChoice != 0) {
                        userChoice = tcv.settingsMenuView(s.getQuestionOrderSetting().name(), s.getTermDisplaySetting().name(), s.getProvideFeedbackSetting().name());
                        switch (userChoice) {
                            case 1:
                                int settingChoice = (tcv.questionOrderSettingView(s.getQuestionOrderSetting().name())) - 1;
                                if (settingChoice >= 0 && s.getQuestionOrderSetting().ordinal() != settingChoice) {
                                    s.setQuestionOrderSetting(Constants.QuestionOrder.values()[settingChoice]);
                                    s.saveSettingsToFile();
                                }
                                break;
                            case 2:
                                settingChoice = (tcv.termDisplaySettingView(s.getTermDisplaySetting().name())) - 1;
                                if (settingChoice >= 0 && s.getTermDisplaySetting().ordinal() != settingChoice) {
                                    s.setTermDisplaySetting(Constants.TermDisplay.values()[settingChoice]);
                                    s.saveSettingsToFile();
                                }
                                break;
                            case 3:
                                settingChoice = (tcv.provideFeedbackSettingView(s.getProvideFeedbackSetting().name())) - 1;
                                if (settingChoice >= 0 && s.getProvideFeedbackSetting().ordinal() != settingChoice) {
                                    s.setProvideFeedbackSetting(Constants.ProvideFeedback.values()[settingChoice]);
                                    s.saveSettingsToFile();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 3:
                    tcv.aboutView();
                    break;
                case 0:
                case 4:
                    exitFlag = tcv.exitView();
                    break;
                default:
                    break;
            }
        }
    }
}
