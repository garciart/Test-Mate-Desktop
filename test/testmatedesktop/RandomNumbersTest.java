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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class RandomNumbersTest {
    
    public RandomNumbersTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIndexLocation method, of class RandomNumbers.
     */
    @Test
    public void testGetIndexLocation() {
        System.out.println("getIndexLocation");
        RandomNumbers instance = null;
        int expResult = 0;
        int result = instance.getIndexLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUniqueArray method, of class RandomNumbers.
     */
    @Test
    public void testGetUniqueArray() {
        System.out.println("getUniqueArray");
        RandomNumbers instance = null;
        int[] expResult = null;
        int[] result = instance.getUniqueArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIndexLocation method, of class RandomNumbers.
     */
    @Test
    public void testSetIndexLocation() {
        System.out.println("setIndexLocation");
        int indexLocation = 0;
        RandomNumbers instance = null;
        instance.setIndexLocation(indexLocation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUniqueArray method, of class RandomNumbers.
     */
    @Test
    public void testSetUniqueArray() {
        System.out.println("setUniqueArray");
        int[] uniqueArray = null;
        RandomNumbers instance = null;
        instance.setUniqueArray(uniqueArray);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    /**
     * Method tested: public RandomNumbers(int max)
     * Short description: Tests that the constructor throws an IllegalArgumentException if the input is MIN_VALUE.
     * Input: Integer.MIN_VALUE
     * Expected result: Throw IllegalArgumentException
     */
    public void testRandomNumbersLowerBound() {
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
     * Method tested: public RandomNumbers(int max)
     * Short description: Tests that the constructor throws an IllegalArgumentException if the input is 0.
     * Input: 0
     * Expected result: Throw IllegalArgumentException
     */
    public void testRandomNumbersZero() {
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
     * Method tested: public RandomNumbers(int max)
     * Short description: Tests that the constructor creates an array the size of the parameter.
     * Input: 5
     * Expected result: 5
     */
    public void testRandomNumbersValidParameter() {
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
     * Method tested: public RandomNumbers(int max)
     * Short description: Tests that the constructor creates an array with a max value of the parameter - 1 (zero-based).
     * Input: 5
     * Expected result: 4
     */
    public void testRandomNumbersMaxValue() {
        try {
            RandomNumbers rn = new RandomNumbers(5);
            Class<? extends RandomNumbers> rnClass = rn.getClass();
            Field f1;
            f1 = rnClass.getDeclaredField("uniqueArray");
            f1.setAccessible(true);
            int[] uniqueArray = (int[]) f1.get(rn);
            int max = uniqueArray[0];
            for(int i = 1; i < uniqueArray.length; i++) {
                if(uniqueArray[i] > max) {
                    max = uniqueArray[i];
                }
            }
            assertEquals(4, max);
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
}
