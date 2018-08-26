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

/**
 * TestMate model class for common constants
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class Constants {
    public static final String SETTINGSFILE = System.getProperty("user.dir") + "\\settings.tm";
    protected static final char[] LETTERS = {'A', 'B', 'C', 'D'};
    
    /** Question order settings
     * DEFAULT to display questions as read from the file
     * RANDOM to randomize the order */
    public enum QuestionOrder {
        DEFAULT,
        RANDOM;
    }

    /** Term display settings
     * TERMFIRST to display terms as question (Default),
     * DEFFIRST to display definitions as question,
     * MIXED to mix it up */
    public enum TermDisplay {
        TERMFIRST,
        DEFFIRST,
        MIXED
    }

    /** Provide feedback settings
     * YES to to provide feedback after each answer (Default),
     * NO to wait until the end of the test to provide feedback */
    public enum ProvideFeedback {
        YES,
        NO
    }
    
    /** Question type constants
     * K for Key Term, M for Multiple Choice, T for True or False */    
    public enum QuestionType {
        K, M, T
    }
    
    /** Media flag constants
     * N for none, I for images, A for audio files, and V for video files */    
    public enum MediaType {
        N, I, A, V
    }
}
