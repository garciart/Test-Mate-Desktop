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

import java.io.File;
import java.io.FilenameFilter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class TestMateConsoleView {

    public void introduction() {
        System.out.println("Welcome to TestMate!");
        System.out.println();
    }

    /**
     * Display main menu and sends choice to controller
     *
     * @return The user's choice
     */
    public int mainMenuView() {
        System.out.println("Please select one of the following choices...");
        System.out.println("[1] Take a Test...");
        System.out.println("[2] Change Settings...");
        System.out.println("[3] About TestMate...");
        System.out.println("[4] Exit Testmate");
        System.out.print("Enter your choice here: ");
        return getAndValidateChoice2(4, Constants.InputType.Numeric);
    }

    public String fileView(String extension) {
        System.out.println("Please select from one of the following tests...");
        File dir = new File(System.getProperty("user.dir") + "\\");
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase(Locale.ENGLISH).endsWith(extension);
            }
        });
        for (int x = 0; x < files.length; x++) {
            System.out.println("[" + (x + 1) + "] " + files[x].getName());
        }
        System.out.print("Enter your choice here or [X] to return to Main Menu: ");
        int result = getAndValidateChoice2(files.length, Constants.InputType.Numeric);
        return (result != 0 ? files[result - 1].toString() : null);
    }

    public void testLoadedView() {
        System.out.println("Test loaded! Press [ENTER] to start...");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        scanner.nextLine();
    }

    public int askQuestionView(int questionNumber, TestQuestion tq) {
        System.out.println((questionNumber + 1) + ". " + tq.getQuestion());
        for (int y = 0; y <= tq.getNumberOfChoices(); y++) {
            System.out.println(Constants.LETTERS[y] + ". " + tq.getChoices().get(y) + (y == tq.getCorrectAnswerIndex() ? " - HERE!" : ""));
        }
        System.out.print("Enter your choice here or [X] to exit the test: ");
        // numberOfChoices is zero-based and getAndValidateChoice is one-based
        // Add one to use getAndValidateChoice and subtract 1 to return the correct index
        return (getAndValidateChoice2(tq.getNumberOfChoices() + 1, Constants.InputType.Alpha) - 1);
    }

    public void feedbackView(boolean result, String explanation) {
        System.out.print(result ? "Correct. " : "Incorrect. ");
        System.out.println(explanation);
        System.out.println();
    }

    public void resultView(int totalQuestions, int totalCorrectAnswers, long elapsedTime) {
        System.out.println("You have completed your test!");
        String score = String.format("%02d", ((totalCorrectAnswers / totalQuestions) * 100));
        System.out.println("You answered " + totalCorrectAnswers + " out of " + totalQuestions + " for a score of " + score + "% in "
                + String.format("%02d:%02d.", TimeUnit.NANOSECONDS.toHours(elapsedTime),
                        TimeUnit.NANOSECONDS.toSeconds(elapsedTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(elapsedTime))));
        System.out.println("Press [ENTER] to continue...");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        scanner.nextLine();
    }

    public void resultView(int totalQuestions, int totalCorrectAnswers, long elapsedTime, String userResults[][]) {
        System.out.println("You have completed your test!");
        String score = String.format("%02d", ((totalCorrectAnswers / totalQuestions) * 100));
        System.out.println("You answered " + totalCorrectAnswers + " out of " + totalQuestions + " for a score of " + score + "% in "
                + String.format("%02d:%02d.", TimeUnit.NANOSECONDS.toHours(elapsedTime),
                        TimeUnit.NANOSECONDS.toSeconds(elapsedTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(elapsedTime))));
        System.out.println("Your results...");
        for (int x = 0; x < userResults.length; x++) {
            System.out.println((x + 1) + ". " + userResults[x][0]);
            System.out.println(userResults[x][1]);
            System.out.println(userResults[x][2]);
            System.out.println();
        }
        System.out.println("Press [ENTER] to continue...");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        scanner.nextLine();
    }

    public int settingsMenuView(String qo, String td, String pf) {
        System.out.println("Please select which setting to change...");
        System.out.println("[1] Question Order: " + qo);
        System.out.println("[2] Term Display: " + td);
        System.out.println("[3] Provide Feedback: " + pf);
        System.out.print("Enter your choice here or [X] to return to Main Menu: ");
        return getAndValidateChoice2(3, Constants.InputType.Numeric);
    }

    public int questionOrderSettingView(String qo) {
        System.out.println("Your current question order setting is " + qo);
        System.out.println("Please select a new setting...");
        System.out.println("[1] Display questions as read from the file (Default)");
        System.out.println("[2] Randomize the order");
        System.out.print("Enter your choice here or [X] to return to Settings Menu: ");
        return getAndValidateChoice2(2, Constants.InputType.Numeric);
    }

    public int termDisplaySettingView(String td) {
        System.out.println("Your current term display setting is " + td);
        System.out.println("Please select which setting to change...");
        System.out.println("[1] Display terms as question (Default)");
        System.out.println("[2] Display definitions as question");
        System.out.println("[3] Mix it up");
        System.out.print("Enter your choice here or [X] to return to Settings Menu: ");
        return getAndValidateChoice2(3, Constants.InputType.Numeric);
    }

    public int provideFeedbackSettingView(String pf) {
        System.out.println("Your current provide feedback setting is " + pf);
        System.out.println("Please select which setting to change...");
        System.out.println("[1] Provide feedback after each answer (Default)");
        System.out.println("[2] Wait until the end of the test");
        System.out.print("Enter your choice here or [X] to return to Settings Menu: ");
        return getAndValidateChoice2(2, Constants.InputType.Numeric);
    }

    public void aboutView() {
        System.out.println("Test Mate");
        System.out.println("Copyright 1993-" + Calendar.getInstance().get(Calendar.YEAR) + " Robert Garcia");
        System.out.println("Test Mate is a mobile self-study system, designed to assist you in achieving your educational and professional goals by allowing you to study what you want, when and where you want. Why study alone?");
        System.out.println();
    }

    public boolean exitView() {
        System.out.println("Are you sure you want to leave?");
        System.out.print("Enter [Y] for Yes or [N] for No: ");
        boolean result = (getAndValidateChoice2(2, Constants.InputType.YesNo) == 1);
        if (result) {
            System.out.println("Goodbye!");
        }
        System.out.println();
        return result;
    }

    public int errorView(String message) {
        System.out.println("Oops! Something went wrong!");
        System.out.println(message);
        System.out.println("We've been notified and will start fixing the problem right away!");
        System.out.println();
        return -1;
    }

    /**
     * Get and validate the user's choice
     *
     * @param numberOfChoices One-based maximum number of choices
     * @param flag Input type; Alpha, Numeric, or YesNo
     * @return the validated choice
     */
    private int getAndValidateChoice2(int numberOfChoices, Constants.InputType flag) {
        boolean validChoice = false;
        int choice = 0;
        while (!validChoice) {
            Scanner scanner = new Scanner(System.in, "UTF-8");
            if (scanner.hasNext()) {
                char userInput = scanner.next().toLowerCase(Locale.ENGLISH).charAt(0);
                if (userInput == 'x' || (flag == Constants.InputType.YesNo && userInput == 'n')) {
                    validChoice = true;
                } else {
                    switch (flag) {
                        case Alpha:
                            if (Character.isLetter(userInput)) {
                                choice = (userInput - 'a') + 1;
                            }
                            break;
                        case Numeric:
                            if (Character.isDigit(userInput)) {
                                choice = userInput - '0';
                            }
                            break;
                        case YesNo:
                            switch (userInput) {
                                case 'y':
                                    choice = 1;
                                    break;
                                case 'n':
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    validChoice = (choice > 0 && choice <= numberOfChoices);
                    if (!validChoice) {
                        System.out.print("That choice is not available. Please try again: ");
                        choice = 0;
                    }
                }
            }
        }
        System.out.println();
        return choice;
    }
}
