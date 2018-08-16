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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class Settings {
    private final String SETTINGSFILE = System.getProperty("user.dir") + "\\settings.tm";
    
    
    /** Display questions as read from the file (0 = default) or randomize the order (1) */
    private int questionOrderSetting = 0;
    /** Display terms as question (0 = default), definition as question (1), or mix it up (2) */
    private int termDisplaySetting = 0;
    /** Provide feedback after each answer (0 = default) or wait until the end of the test (1) */
    private int provideFeedbackSetting = 0;
    
    /** Basic constructor */
    public Settings() {

    }

    /** Question order setting getter
     * @return 0 (Display questions as read from the file (Default)), 1 (Randomize the order) */
    public int getQuestionOrderSetting() {
        return questionOrderSetting;
    }

    /** Term display setting getter
     * @return 0 (Display terms as question (Default)), 1 (Definition as question), 2 (Mix it up) */
    public int getTermDisplaySetting() {
        return termDisplaySetting;
    }

    /** Provide feedback setting getter
     * @return 0 (Provide feedback after each answer (Default)) 1 (Wait until the end of the test) */
    public int getProvideFeedbackSetting() {
        return provideFeedbackSetting;
    }

    /** Question order setting setter
     * @param qo 0 (Display questions as read from the file (Default)), 1 (Randomize the order) */
    public void setQuestionOrderSetting(int qo) {
        qo = (Math.abs(qo) <= 1) ? (Math.abs(qo)) : 0;
        this.questionOrderSetting = qo;
    }

    /** Term display setting setter
     * @param td 0 (Display terms as question (Default)), 1 (Definition as question), 2 (Mix it up) */
    public void setTermDisplaySetting(int td) {
        td = (Math.abs(td) <= 2) ? (Math.abs(td)) : 0;
        this.termDisplaySetting = td;
    }

    /** Provide feedback setting setter
     * @param pf 0 (Provide feedback after each answer (Default)) 1 (Wait until the end of the test) */
    public void setProvideFeedbackSetting(int pf) {
        pf = (Math.abs(pf) <= 1) ? (Math.abs(pf)) : 0;
        this.provideFeedbackSetting = pf;
    }
    
    /**
     * Get saved test settings
     * @throws FileNotFoundException When file cannot be found
     * @throws IOException When file cannot be opened
     */
    public final void getSettingsFromFile() throws FileNotFoundException, IOException {
        String line;
        FileReader fileReader = new FileReader(SETTINGSFILE);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            if((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                setQuestionOrderSetting(Integer.parseInt(line));
            }
            if((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                setTermDisplaySetting(Integer.parseInt(line));
            }
            if((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                setProvideFeedbackSetting(Integer.parseInt(line));
            }
        }
    }
    
    /**
     * Save test settings to file
     * @throws java.io.IOException When file cannot be opened
     */
    public final void saveSettingsToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SETTINGSFILE))) {
            bw.write(Integer.toString(getQuestionOrderSetting()));
            bw.newLine();
            bw.write(Integer.toString(getTermDisplaySetting()));
            bw.newLine();
            bw.write(Integer.toString(getProvideFeedbackSetting()));
        }
        catch (Exception ex) {
            throw new IOException("Unable to update file! " + ex.toString());
        }
    }
}