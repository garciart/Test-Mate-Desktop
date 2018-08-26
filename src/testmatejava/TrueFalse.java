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

import testmatejava.Constants.*;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class TrueFalse extends TestData {
    private String tfQuestion;
    private boolean tfAnswer;
    
    public final String getTFQuestion() {
        return tfQuestion;
    }

    public final boolean getTFAnswer() {
        return tfAnswer;
    }
    
    public final void setTFQuestion(String tfQuestion) {
        if(Utilities.isNullOrEmpty(tfQuestion)) throw new NullPointerException("True/False questions cannot be null or empty.");
        else this.tfQuestion = tfQuestion;
    }
    
    public final void setTFAnswer(boolean tfAnswer) {
        this.tfAnswer = tfAnswer;
    }
    
    public TrueFalse(QuestionType questionType) {
        setQuestionType(questionType);
    }
    
    /*
    public TrueFalse(QuestionType questionType, String tfQuestion, MediaType mediaType, String mediaFileName, boolean tfAnswer, String tfExplanation) {
        setQuestionType(questionType);
        setTFQuestion(tfQuestion);
        validateAndSetMedia(mediaType, mediaFileName);
        setTFAnswer(tfAnswer);
        setTFExplanation(tfExplanation);
    }
    */
}
