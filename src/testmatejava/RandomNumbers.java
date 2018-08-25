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

import java.util.Random;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class RandomNumbers {
    private int indexLocation = 0;
    private int[] uniqueArray;
    
    public final int getIndexLocation() {
        return this.indexLocation;
    }
    
    public final int[] getUniqueArray() {
        return this.uniqueArray;
    }
    
    public final void setIndexLocation(int indexLocation) {
        this.indexLocation = indexLocation;
    }
    
    public final void setUniqueArray(int[] uniqueArray) {
        this.uniqueArray = uniqueArray;
    }
    
    public RandomNumbers(int max) {
        if(max > 1) {
            Random rand = new Random(System.currentTimeMillis());
            this.uniqueArray = new int[max];
            // Get ordered number set
            for(int x = 0; x < max; x++) {
                uniqueArray[x] = x;
            }
            // Shuffle the set
            for(int x = 0; x < max; x++) {
                int r = rand.nextInt(max);
                int temp = uniqueArray[x];
                uniqueArray[x] = uniqueArray[r];
                uniqueArray[r] = temp;
            }
        }
        else {
            throw new IllegalArgumentException("Ensure the max value is greater than 1.");
        }
    }
    
    public RandomNumbers(int max, int index) {
        // Ensure the arguments are positive and that the index is less than or equal to the max value
        if(max >= 0 && index >= 0 && max >= index) {
            Random rand = new Random(System.currentTimeMillis());
            int[] tempArray = new int[max + 1];
            // Get ordered number set, INCLUDING the index number
              // 0 to 3 zero-based choices
            if(max < 4) {
                for(int x = 0; x <= max; x++) {
                    tempArray[x] = x;
                }
            }
            else {
                // 4 to 7 zero-based choices
                int y = 1;
                if(max >= 4 && max < 8) {
                    // Get ordered number set, EXCLUDING the index number
                    for(int x = 1; x <= max; x++) {
                        if(x != index) {
                            tempArray[y] = x;
                            y++;
                        }
                    }
                }
                else {
                    // 8 total choices or more
                    // Get ordered number set, EXCLUDING the index number
                    for(int x = (index + 1); x < (index + 8); x++) {
                        // Slide back to 0 if x is greater than max
                        if(x <= max) {
                            tempArray[y] = x;
                        }
                        else {
                            tempArray[y] = x - (max + 1);
                        }
                        y++;
                    }
                }
                // Shuffle the set
                for(int x = 1; x < 8; x++) {
                    int r = rand.nextInt(7) + 1;
                    int temp = tempArray[x];
                    tempArray[x] = tempArray[r];
                    tempArray[r] = temp;
                }
                // Add the index number to the beginning of the set
                tempArray[0] = index;
            }
            // Reshuffle the first four numbers, ensuring the index number is in the new set
            for(int x = 0; x <= 3; x++) {
                int r = rand.nextInt(3);
                int temp = tempArray[x];
                tempArray[x] = tempArray[r];
                tempArray[r] = temp;
            }
            // Get the location of the index and transfer the array
            this.uniqueArray = new int[(max < 3 ? max : 3) + 1];
            for(int x = 0; x <= (max < 3 ? max : 3); x++) {
                if(tempArray[x] == index) setIndexLocation(x);
                this.uniqueArray[x] = tempArray[x];
            }
        }
        else {
            throw new IllegalArgumentException("Ensure the max value is greater than 1, the index is greater than zero, and the index is less than or equal to the max value.");
        }
            
    }
}
