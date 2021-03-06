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
 * TestMate model class holding common and useful functions
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class Utilities {

    /**
     * Check if a string is null or empty
     *
     * @param s the string to check
     * @return true if null or empty, false if not
     */
    public static final boolean isNullOrEmpty(String s) {
        return ((s == null || "".equals(s.trim())));
    }

    /**
     * Allow the application to print tabs and carriage returns to STDOUT
     *
     * @param s the safely encoded string
     * @return the string with tabs and carriage returns
     */
    public static final String fixEscapeCharacters(String s) {
        s = s.replace("\\t", "\t");
        s = s.replace("\\n", "\n");
        return s;
    }
}
