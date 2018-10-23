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

import java.lang.reflect.Field;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class RandomNumbersTest {
    @Test
    /**
     * Method tested: public RandomNumbers(int maxCount)
     * Short description: Tests that the constructor throws an IllegalArgumentException if the input is MIN_VALUE.
     * Input: Integer.MIN_VALUE
     * Expected result: Throw IllegalArgumentException
     */
    public void testRandomNumbersConstructor1LowerBound() {
        try {
            RandomNumbers rn = new RandomNumbers(Integer.MIN_VALUE);
            fail("RandomNumbers() should have thrown an exception when the max parameter is not equal to or greater than 1.");
        }
        catch (IllegalArgumentException ex) {
            assertEquals("The RandomNumbers() max parameter must be be equal to or greater than 1.", ex.getLocalizedMessage());
        }
    }

    @Test
    /**
     * Method tested: public RandomNumbers(int maxCount)
     * Short description: Tests that the constructor throws an IllegalArgumentException if the input is 0.
     * Input: 0
     * Expected result: Throw IllegalArgumentException
     */
    public void testRandomNumbersConstructor1Zero() {
        try {
            RandomNumbers rn = new RandomNumbers(0);
            fail("RandomNumbers() should have thrown an exception when the max parameter is not equal to or greater than 1.");
        }
        catch (IllegalArgumentException ex) {
            assertEquals("The RandomNumbers() max parameter must be be equal to or greater than 1.", ex.getLocalizedMessage());
        }
    }

    @Test
    /**
     * Method tested: public RandomNumbers(int maxCount)
     * Short description: Tests that the constructor creates an array the size of the parameter.
     * Input: 5
     * Expected result: 5
     */
    public void testRandomNumbersConstructor1ValidParameter() {
        try {
            RandomNumbers rn = new RandomNumbers(5);
            Class<? extends RandomNumbers> rnClass = rn.getClass();
            Field f1;
            f1 = rnClass.getDeclaredField("uniqueArray");
            f1.setAccessible(true);
            int[] uniqueArray = (int[]) f1.get(rn);
            assertEquals(5, uniqueArray.length);
        }
        catch (IllegalAccessException ex) {
            fail("Test issue: Field object is enforcing Java language access control and the underlying field is inaccessible.");
        }
        catch (IllegalArgumentException ex) {
            fail("Test issue: The RandomNumbers() max parameter must be be equal to or greater than 1.");
        }
        catch (NoSuchFieldException ex) {
            fail("Code issue: RandomNumbers class missing expected attribute uniqueArray.");
        }
    }

    @Test
    /**
     * Method tested: public RandomNumbers(int maxCount)
     * Short description: Tests that the constructor creates an array of non-repeating numbers,
     * maxCount in size, containing all numbers greater than or equal to 0 and less than maxCount.
     * Input: 5
     * Expected result: Pass
     */
    public void testRandomNumbersConstructor1CheckValues() {
        try {
            RandomNumbers rn = new RandomNumbers(5);
            Class<? extends RandomNumbers> rnClass = rn.getClass();
            Field f1;
            f1 = rnClass.getDeclaredField("uniqueArray");
            f1.setAccessible(true);
            int[] uniqueArray = (int[]) f1.get(rn);
            for(int i = 0; i < uniqueArray.length; i++) {
                for(int j = 0; j < uniqueArray.length; j++) {
                    if(uniqueArray[i] == j) {
                        break;
                    }
                    else if(j == (uniqueArray.length - 1)) {
                        fail("Code issue: The array does not contain all the required values.");
                    }
                }
            }
        }
        catch (IllegalAccessException ex) {
            fail("Test issue: Field object is enforcing Java language access control and the underlying field is inaccessible.");
        }
        catch (IllegalArgumentException ex) {
            fail("Test issue: The RandomNumbers() maxCount parameter must be be equal to or greater than 1.");
        }
        catch (NoSuchFieldException ex) {
            fail("Code issue: RandomNumbers class missing expected attribute uniqueArray.");
        }
    }
    
    @Test
    /**
     * Method tested: public RandomNumbers(int max, int index, int uniqueArraySize)
     * Short description: Tests that the constructor throws an IllegalArgumentException if the input is MIN_VALUE.
     * Input: Integer.MIN_VALUE
     * Expected result: Throw IllegalArgumentException
     */
    public void testRandomNumbersConstructor2LowerBound() {
        try {
            RandomNumbers rn = new RandomNumbers(Integer.MIN_VALUE, 0, 0);
            fail("RandomNumbers() should have thrown an exception when the max parameter is not equal to or greater than 1.");
        }
        catch (IllegalArgumentException ex) {
            assertEquals("The RandomNumbers() max parameter must be be equal to or greater than 1.", ex.getLocalizedMessage());
        }
    }
}
