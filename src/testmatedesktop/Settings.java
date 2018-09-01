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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import testmatedesktop.Constants.*;

/**
 * TestMate model class for test settings
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class Settings {
    /** Display questions as read from the file (0 = default) or randomize the order (1) */
    private QuestionOrder questionOrderSetting = QuestionOrder.DEFAULT;
    /** Display terms as question (0 = default), definitions as question (1), or mix it up (2) */
    private TermDisplay termDisplaySetting = TermDisplay.TERMISQUESTION;
    /** Provide feedback after each answer (0 = default) or wait until the end of the test (1) */
    private ProvideFeedback provideFeedbackSetting = ProvideFeedback.YES;
    
    /** Question order setting getter
     * @return DEFAULT - Display questions as read from the file,
     *         RANDOM - Randomize the order */
    public QuestionOrder getQuestionOrderSetting() {
        return questionOrderSetting;
    }

    /** Term display setting getter
     * @return TERMISQUESTION - Display terms as question (Default)),
     *         DEFISQUESTION - Display definition as question,
     *         MIXEDQUESTION - Mix it up */
    public TermDisplay getTermDisplaySetting() {
        return termDisplaySetting;
    }

    /** Provide feedback setting getter
     * @return YES - Provide feedback after each answer (Default),
     *         NO - Wait until the end of the test to provide feedback */
    public ProvideFeedback getProvideFeedbackSetting() {
        return provideFeedbackSetting;
    }

    /** Question order setting setter
     * @param qo DEFAULT to display questions as read from the file or RANDOM to randomize the order */
    public void setQuestionOrderSetting(QuestionOrder qo) {
        this.questionOrderSetting = qo;
    }

    /** Term display setting setter
     * @param td TERMISQUESTION to display terms as question (Default), DEFISQUESTION to display definitions as question, MIXEDQUESTION to mix it up */
    public void setTermDisplaySetting(TermDisplay td) {
        this.termDisplaySetting = td;
    }

    /** Provide feedback setting setter
     * @param pf YES to to provide feedback after each answer (Default), NO to wait until the end of the test to provide feedback */
    public void setProvideFeedbackSetting(ProvideFeedback pf) {
        this.provideFeedbackSetting = pf;
    }
    
    /**
     * Get saved test settings from file
     * @throws FileNotFoundException When file cannot be found
     * @throws IOException When file cannot be opened
     */
    public final void getSettingsFromFile() throws FileNotFoundException, IOException {
        String line;
        FileReader fileReader = new FileReader(Constants.SETTINGSFILE);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            if((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                setQuestionOrderSetting(QuestionOrder.valueOf(line));
            }
            if((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                setTermDisplaySetting(TermDisplay.valueOf(line));
            }
            if((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                setProvideFeedbackSetting(ProvideFeedback.valueOf(line));
            }
        }
    }
    
    /**
     * Save test settings to file
     * @param qo DEFAULT to display questions as read from the file or RANDOM to randomize the order
     * @param td TERMISQUESTION to display terms as question (Default), DEFISQUESTION to display definitions as question, MIXEDQUESTION to mix it up
     * @param pf YES to to provide feedback after each answer (Default), NO to wait until the end of the test to provide feedback
     * @throws java.io.IOException When file cannot be opened
     */
    public final void saveSettingsToFile(QuestionOrder qo, TermDisplay td, ProvideFeedback pf) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.SETTINGSFILE))) {
            bw.write(qo.toString());
            bw.newLine();
            bw.write(td.toString());
            bw.newLine();
            bw.write(pf.toString());
        }
        catch (Exception ex) {
            throw new IOException("Unable to update file! " + ex.toString());
        }
    }
}