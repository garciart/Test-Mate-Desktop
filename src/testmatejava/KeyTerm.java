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
public final class KeyTerm extends TestData {
    private String keyTerm;
    private String ktDefinition;
    
    public final String getKeyTerm() {
        return keyTerm;
    }

    public final String getKTDefinition() {
        return ktDefinition;
    }
    
    public final void setKeyTerm(String keyTerm) {
        if(Utilities.isNullOrEmpty(keyTerm)) throw new NullPointerException("Key terms cannot be null or empty.");
        else this.keyTerm = keyTerm;
    }
    
    public final void setKTDefinition(String ktDefinition) {
        if(Utilities.isNullOrEmpty(ktDefinition)) throw new NullPointerException("Key term definitions cannot be null or empty.");
        else this.ktDefinition = ktDefinition;
    }
    
    public KeyTerm(QuestionType questionType) {
        setQuestionType(questionType);
    }
    
    /*
    public KeyTerm(QuestionType questionType, String keyTerm, MediaType mediaType, String mediaFileName, String definition) {
        setQuestionType(questionType);
        setKeyTerm(keyTerm);
        validateAndSetMedia(mediaType, mediaFileName);
        setKTDefinition(ktDefinition);
    }
    */
}
