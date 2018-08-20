/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmatejava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import testmatejava.Constants.*;

/**
 * TestMate controller class
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class TestMateJava {
    private static List<TestData> testData;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException When file cannot be opened
     */
    public static void main(String[] args) throws IOException {
        Settings s = new Settings();
        try {
            s.getSettingsFromFile();
        }
        catch(Exception ex) {
            System.out.println("Unable to read settings file: " + ex.toString());
            System.out.println("Applying default settings...");
            s.saveSettingsToFile(QuestionOrder.DEFAULT, TermDisplay.TERMFIRST, ProvideFeedback.YES);
        }
        System.out.println("questionOrderSetting = " + s.getQuestionOrderSetting());
        System.out.println("termDisplaySetting = " + s.getTermDisplaySetting());
        System.out.println("provideFeedbackSetting = " + s.getProvideFeedbackSetting());
        s.setProvideFeedbackSetting(null);
        /*
        String test = "";
        if(Utility.isNullOrEmpty(test)) {
            System.out.println("\nEmpty string!\n");
        }
        else {
            System.out.println("\nString is not empty!\n");
        }
        */
        try {
            KeyTerm td = new KeyTerm("La", MediaType.I, "bob.png", "A note to follow so.");
            System.out.println(td.getMediaFileName());
            td.setMediaFileName("bob.mp3");
            System.out.println(td.getMediaFileName());
            // readFile();
            randomTest();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
    }
    
    /**
     * Method to read file data and create an ArrayList of Animal objects
     * @return The Animal list
     * @throws InvalidAnimalException When an invalid name is encountered
     * @throws FileNotFoundException When file cannot be found
     * @throws IOException When file cannot be opened
     */    
    private static void readFile() throws FileNotFoundException, IOException {
        String line;
        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "\\mta-98-361-01.tmf");
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
                /*
                String[] temp = line.split(",");
                // temp[0] is the species, temp[1] is the name, and temp[2] is the bithdate
                if(temp[0].matches("^[a-zA-Z0-9]*$") && temp[1].matches("^[a-zA-Z0-9]*$") && temp[2].matches("(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])\\/\\d{4}")) {
                    switch (temp[0]) {
                        case "Goat": {
                            animal.add(new Goat(temp[1], temp[2]));
                            break;
                        }
                        case "Lion": {
                            animal.add(new Lion(temp[1], temp[2]));
                            break;
                        }
                        case "Pig": {
                            animal.add(new Pig(temp[1], temp[2]));
                            break;
                        }
                        case "Turtle": {
                            animal.add(new Turtle(temp[1], temp[2]));
                            break;
                        }
                        case "Iguana": {
                            animal.add(new Iguana(temp[1], temp[2]));
                            break;
                        }
                        case "Eagle": {
                            animal.add(new Eagle(temp[1], temp[2]));
                            break;
                        }
                        case "Hawk": {
                            animal.add(new Hawk(temp[1], temp[2]));
                            break;
                        }
                        default:
                            throw new InvalidAnimalException("Invalid animal " + temp[1] + " found!");
                    }
                }
                else {
                    throw new InvalidAnimalException("Format error! Check your parameters!");
                }
                */
            }
        } 
    }
    
    public static void randomTest() {
        // randomTest(int t, int i)
        int t = 3; // Zero-based
        int i = 2; // Zero-based
        int newIndex = 0;
        Random rand = new Random(System.currentTimeMillis());
        int[] randomArray = new int[t + 1];
        if(t <= 3) {
            System.out.println("Four questions or less:");
            // Get ordered number set
            for(int x = 0; x <= t; x++) {
                randomArray[x] = x;
            }
            
            /* TEST */
            for(int x = 0; x <= t; x++) {
                System.out.println(x + ": " + randomArray[x]);
            }
            System.out.println();
            
            // Shuffle the set
            for(int x = 0; x <= t; x++) {
                int r = rand.nextInt(t);
                int temp = randomArray[x];
                randomArray[x] = randomArray[r];
                randomArray[r] = temp;
            }
            
            /* TEST */
            for(int x = 0; x <= t; x++) {
                System.out.println(x + ": " + randomArray[x]);
            }
            System.out.println();
        }
        else if (t >= 4 && t <= 8) {
            System.out.println("Five to nine questions:");
            // Get ordered number set, excluding the index number
            int y = 1;
            for(int x = 0; x <= t; x++) {
                if(x != i) {
                    randomArray[y] = x;
                    y++;
                }
            }
            
            /* TEST */
            for(int x = 0; x <= t; x++) {
                System.out.println(x + ": " + randomArray[x]);
            }
            System.out.println();

            // Shuffle the first set
            for(int x = 1; x <= t; x++) {
                int r = rand.nextInt(t - 1) + 1;
                int temp = randomArray[x];
                randomArray[x] = randomArray[r];
                randomArray[r] = temp;
            }
            
            /* TEST */
            for(int x = 0; x <= t; x++) {
                System.out.println(x + ": " + randomArray[x]);
            }
            System.out.println();

            // Add the index number to the beginning of the set
            randomArray[0] = i;
            // Reshuffle the first four numbers, ensuring the index number is in the new set
            for(int x = 0; x <= 3; x++) {
                int r = rand.nextInt(3);
                int temp = randomArray[x];
                randomArray[x] = randomArray[r];
                randomArray[r] = temp;
            }

            /* TEST */
            for(int x = 0; x <= 3; x++) {
                System.out.println(x + ": " + randomArray[x]);
            }
            System.out.println();
        }
        else {
            System.out.println("Greater than nine questions:");
            // Get ordered number set, excluding the index number
            int y = 0;
            for(int x = (i + 1); x < (i + 8); x++) {
                if(x < t) {
                    randomArray[y + 1] = x;
                }
                else {
                    randomArray[y + 1] = x - t;
                }
                y++;
            }

            /* TEST */
            for(int x = 0; x < 8; x++) {
                System.out.println(x + ": " + randomArray[x]);
            }
            System.out.println();
            
            // Shuffle the first set            
            for(int x = 1; x < 8; x++) {
                int r = rand.nextInt(7) + 1;
                int temp = randomArray[x];
                randomArray[x] = randomArray[r];
                randomArray[r] = temp;
            }

            /* TEST */
            for(int x = 0; x < 8; x++) {
                System.out.println(x + ": " + randomArray[x]);
            }
            System.out.println();

            // Add the index number to the beginning of the set
            randomArray[0] = i;
            // Reshuffle the first four numbers, ensuring the index number is in the new set
            for(int x = 0; x <= 3; x++) {
                int r = rand.nextInt(3);
                int temp = randomArray[x];
                randomArray[x] = randomArray[r];
                randomArray[r] = temp;
            }

            /* TEST */
            for(int x = 0; x < 8; x++) {
                System.out.println(x + ": " + randomArray[x]);
            }
            System.out.println();
        }
        // Get new index
        for(int x = 0; x <= (t < 3 ? t : 3); x++) {
            System.out.println(x + ": " + randomArray[x]);
            if(randomArray[x] == i) newIndex = x;
        }
        System.out.println("New zero-based index is " + newIndex);
    }
}
