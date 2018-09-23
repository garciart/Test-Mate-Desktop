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
 * TestMate model class for key term data objects
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class KeyTerm extends TestData {

    private String keyTerm = "";
    private String ktDefinition = "";

    /**
     * Key term getter
     *
     * @return the key term
     */
    public final String getKeyTerm() {
        return keyTerm;
    }

    /**
     * Key term definition getter
     *
     * @return the key term definition
     */
    public final String getKTDefinition() {
        return ktDefinition;
    }

    /**
     * Key term setter
     *
     * @param keyTerm the key term
     */
    public final void setKeyTerm(String keyTerm) {
        if (Utilities.isNullOrEmpty(keyTerm)) {
            throw new NullPointerException("Key terms cannot be null or empty.");
        } else {
            this.keyTerm = keyTerm;
        }
    }

    /**
     * Key term definition setter
     *
     * @param ktDefinition the key term definition
     */
    public final void setKTDefinition(String ktDefinition) {
        if (Utilities.isNullOrEmpty(ktDefinition)) {
            throw new NullPointerException("Key term definitions cannot be null or empty.");
        } else {
            this.ktDefinition = ktDefinition;
        }
    }

    /**
     * Key term class constructor
     */
    public KeyTerm() {
        setQuestionType(Constants.QuestionType.K);
    }
}
