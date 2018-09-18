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

import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class TestMateView {
    public void introduction() {
        System.out.println("Welcome to TestMate!");
    }
    
    /**
     * Display main menu and sends choice to controller
     * @return The user's choice
     */
    public int mainMenu() {
        System.out.println("Please select one of the following choices...");
        System.out.println("[1] Take a Test...");
        System.out.println("[2] Continue a Test...");
        System.out.println("[3] Change Settings...");
        System.out.println("[4] About TestMate...");
        System.out.println("[5] Exit Testmate");
        System.out.print("Enter your choice here: ");
        return getAndValidateChoice(5, false);
    }
    
    public void aboutView() {
        System.out.println("Test Mate");
        System.out.println("Copyright 1993-" + Calendar.getInstance().get(Calendar.YEAR) + " Robert Garcia");
        System.out.println("Test Mate is a mobile self-study system, designed to assist you in achieving your educational and professional goals by allowing you to study what you want, when and where you want. Why study alone?");
    }
    
    public int askQuestion(int questionNumber, TestQuestion tq) {
        System.out.println((questionNumber + 1) + ". " + tq.getQuestion());
        for(int y = 0; y <= tq.getNumberOfChoices(); y++) {
            System.out.println(Constants.LETTERS[y] + ". " + tq.getChoices().get(y) + (y == tq.getCorrectAnswerIndex() ? " - HERE!" : ""));
        }
        System.out.println();
        return getAndValidateChoice(tq.getNumberOfChoices() + 1, true);
    }
    
    /**
     * Get and validate the user's choice
     * @param numberOfChoices One-based maximum number of choices
     * @param hexFlag Only accept values between A through F
     * @return the validated choice
     */
    private int getAndValidateChoice(int numberOfChoices, boolean hexFlag) {
        boolean validChoice = false;
        int choice = 0;
        while (!validChoice) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt(16)) {
                choice = (hexFlag == true ? scanner.nextInt(16) - 9 : scanner.nextInt(16));
                if (choice > 0 && choice <= numberOfChoices) {
                    validChoice = true;
                }
                else {
                    System.out.print("That choice is not available. Please try again: ");
                }
            }
            else {
                System.out.print("Invalid input. Please try again: ");
            }
        }
        return choice;
    }
}
