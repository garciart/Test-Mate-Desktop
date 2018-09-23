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

import java.util.Arrays;
import java.util.Random;

/**
 * TestMate model class to generate unique sets of random numbers
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class RandomNumbers {

    private int indexLocation = 0;
    private int[] uniqueArray;

    /**
     * Index location for key value getter
     *
     * @return the location of a key value in the array
     */
    public final int getIndexLocation() {
        return indexLocation;
    }

    /**
     * Unique array getter
     *
     * @return an integer array of unique numbers in random order
     */
    public final int[] getUniqueArray() {
        return Arrays.copyOf(uniqueArray, uniqueArray.length);
    }

    /**
     * Index location for key value setter
     *
     * @param indexLocation the location of a key value in the array
     */
    public final void setIndexLocation(int indexLocation) {
        this.indexLocation = indexLocation;
    }

    /**
     * Unique array setter
     *
     * @param uniqueArray an integer array of unique numbers in random order
     */
    public final void setUniqueArray(int[] uniqueArray) {
        this.uniqueArray = Arrays.copyOf(uniqueArray, uniqueArray.length);
    }

    /**
     * (Overloaded) RandomNumbers constructor
     *
     * @param max the max value to return (0 is the min value)
     */
    public RandomNumbers(int max) {
        // EVERYTHING IS ZERO-BASED, INCLUDING THE ARGUMENTS
        // Ensure the argument is positive and that there is more than one value to randomize
        if (max > 1) {
            // Seed the psuedo random number generator using the current time
            Random rand = new Random();
            // Initiate the array set to the max size
            this.uniqueArray = new int[max + 1];
            // Get ordered number set
            for (int x = 0; x <= max; x++) {
                uniqueArray[x] = x;
            }
            // Shuffle the set
            for (int x = 0; x <= max; x++) {
                // Get random numbers between 0 and max
                // 0 is inclusive, but max is exclusive, so add 1)
                int r = rand.nextInt(max + 1);
                int temp = uniqueArray[x];
                uniqueArray[x] = uniqueArray[r];
                uniqueArray[r] = temp;
            }
        } else {
            throw new IllegalArgumentException("Ensure the max value is greater than 1.");
        }
    }

    /**
     * (Overloaded) RandomNumbers constructor
     *
     * @param max the max value to return
     * @param index the key value that must appear in the array
     * @param uniqueArraySize the number of elements to return
     */
    public RandomNumbers(int max, int index, int uniqueArraySize) {
        // EVERYTHING IS ZERO-BASED, INCLUDING THE ARGUMENTS
        // Ensure all arguments are positive and that the max value is greater than or equal to the index and desired array size
        if (max >= 0 && index >= 0 && uniqueArraySize >= 0 && max >= index && max >= uniqueArraySize) {
            // Seed the psuedo random number generator using the current time
            Random rand = new Random();
            // Create a temporary array set to the max size
            int[] tempArray = new int[max + 1];
            // Use when the new unique array size and the max value are the same
            if (max == uniqueArraySize) {
                // Get ordered number set, INCLUDING the index number
                for (int x = 0; x <= max; x++) {
                    tempArray[x] = x;
                }
            } else {
                // THE FOLLOWING CODE MITIGATES THE CHANCES OF THE LAST ANSWER FROM APPEARING IN THE NEXT QUESTION
                int y = 1;
                // Use when the difference between the new unique array size and the max value is less than or equal to 7
                if (uniqueArraySize < max && (max - uniqueArraySize) <= 7) {
                    // Get ordered number set, EXCLUDING the index number
                    // Start at 1 to leave room for the index number later
                    for (int x = 1; x <= max; x++) {
                        // Skip the index number
                        if (x != index) {
                            tempArray[y] = x;
                            // Increment only if 
                            y++;
                        }
                    }
                } else {
                    // Use when the difference between the new unique array size and the max value is greater or equal to 8
                    // Get an ordered number set of numbers starting at the index number + 1
                    // Start at 1 to leave room for the index number later
                    for (int x = (index + 1); x <= (index + 7); x++) {
                        if (x <= max) {
                            tempArray[y] = x;
                        } // Slide back to 0 if the index + x goes over the max value
                        else {
                            tempArray[y] = x - (max + 1);
                        }
                        y++;
                    }
                }
                // Shuffle the set
                for (int x = 1; x < y; x++) {
                    // Get random numbers between 1 and 7
                    // 0 is inclusive, but 7 is exclusive, so add 1)
                    int r = rand.nextInt(7) + 1;
                    int temp = tempArray[x];
                    tempArray[x] = tempArray[r];
                    tempArray[r] = temp;
                }
                // Place the index number at the beginning of the array
                tempArray[0] = index;
            }
            // Reshuffle the first four numbers, [0] to [3]. The index number at [0] will be in the new set
            for (int x = 0; x <= uniqueArraySize; x++) {
                // Remember, 4 is exclusive
                int r = rand.nextInt(4);
                int temp = tempArray[x];
                tempArray[x] = tempArray[r];
                tempArray[r] = temp;
            }
            // Get the location of the index and transfer the array
            this.uniqueArray = new int[uniqueArraySize + 1];
            for (int x = 0; x <= uniqueArraySize; x++) {
                if (tempArray[x] == index) {
                    setIndexLocation(x);
                }
                this.uniqueArray[x] = tempArray[x];
            }
        } else {
            throw new IllegalArgumentException("Ensure both arguments are positive and that the index is less than or equal to the max value.");
        }
    }
}
