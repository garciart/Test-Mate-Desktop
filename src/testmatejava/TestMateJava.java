/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmatejava;

import java.io.IOException;
import testmatejava.Constants.*;

/**
 * TestMate controller class
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class TestMateJava {
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
        KeyTerm testData = new KeyTerm("La", MediaFlag.N, "sound.mp3", "A note to follow so.");
        System.out.println(testData.getMediaFileName());
        testData.setMediaFileName("bob.doc");
        System.out.println(testData.getMediaFileName());
    }
}
