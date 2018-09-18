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

import java.util.ArrayList;

/**
 * TestMate model class for multiple choice data objects
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class MultipleChoice extends TestData {
    private String mcQuestion = "";
    private int mcNumberOfChoices = 0;
    private ArrayList<String> mcChoices = new ArrayList<>();
    
    /**
     * Multiple choice question getter
     * @return the multiple choice question
     */
    public final String getMCQuestion() {
        return mcQuestion;
    }

    /**
     * Number of possible answers to the multiple choice question getter
     * @return the number of possible answers to the multiple choice question; must be less than six
     */
    public final int getMCNumberOfChoices() {
        return mcNumberOfChoices;
    }
    
    /**
     * Possible answers to the multiple choice question getter
     * @return the possible answers to the multiple choice question
     */
    public final ArrayList<String> getMCChoices() {
        return mcChoices;
    }
    
    /**
     * Multiple choice question setter
     * @param mcQuestion the multiple choice question
     */
    public final void setMCQuestion(String mcQuestion) {
        if(Utilities.isNullOrEmpty(mcQuestion)) throw new NullPointerException("Multiple choice questions cannot be null or empty.");
        else this.mcQuestion = mcQuestion;
    }
    
    /**
     * Number of possible answers to the multiple choice question setter
     * @param mcNumberOfChoices the number of possible answers to the multiple choice question; must be less than six
     */
    public final void setMCNumberOfChoices(int mcNumberOfChoices) {
        if(mcNumberOfChoices <= 0) throw new IllegalArgumentException("The number of multiple choice answers cannot be null or zero.");
        if(mcNumberOfChoices > 6) throw new IllegalArgumentException("The number of multiple choice answers cannot be greater than six.");
        else this.mcNumberOfChoices = mcNumberOfChoices;
    }
    
    /**
     * Possible answers to the multiple choice question setter
     * @param mcChoices possible answers to the multiple choice question
     */
    public final void setMCChoices(ArrayList<String> mcChoices) {
        if(mcChoices == null) throw new NullPointerException("Multiple choice questions must have at least one choice.");
        else this.mcChoices = mcChoices;
    }
    
    /**
     * Multiple choice class constructor 
     */
    public MultipleChoice() {
        this.mcChoices = new ArrayList<>();
        setQuestionType(Constants.QuestionType.M);
    }
}