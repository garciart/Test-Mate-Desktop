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

/**
 * TestMate model class for true/false data objects
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class TrueFalse extends TestData {
    private String tfQuestion = "";
    private boolean tfAnswer = true;
    
    /**
     * True/false question getter
     * @return the true/false question
     */
    public final String getTFQuestion() {
        return tfQuestion;
    }

    /**
     * True/false answer getter
     * @return the true/false answer
     */
    public final boolean getTFAnswer() {
        return tfAnswer;
    }
    
    /**
     * True/false question setter
     * @param tfQuestion the true/false question
     */
    public final void setTFQuestion(String tfQuestion) {
        if(Utilities.isNullOrEmpty(tfQuestion)) throw new NullPointerException("True/False questions cannot be null or empty.");
        else this.tfQuestion = tfQuestion;
    }
    
    /**
     * True/false answer setter
     * @param tfAnswer the true/false answer
     */
    public final void setTFAnswer(boolean tfAnswer) {
        this.tfAnswer = tfAnswer;
    }

    /**
     * True/false class constructor 
     */
    public TrueFalse() {
        setQuestionType(Constants.QuestionType.T);
    }
}
