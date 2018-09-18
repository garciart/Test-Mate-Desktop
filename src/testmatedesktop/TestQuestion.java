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

import java.util.ArrayList;
import testmatedesktop.Constants.*;

/**
 * TestMate model class for test question objects
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public final class TestQuestion {
    private QuestionType questionType;
    private String question;
    private MediaType mediaType;
    private String mediaFileName;
    private int numberOfChoices;
    private ArrayList<String> choices;
    private int correctAnswerIndex;
    private String explanation;
    
    /**
     * Question type getter
     * @return the question type; K for Key Term, M for Multiple Choice, T for True or False
     */
    public final QuestionType getQuestionType() {
        return questionType;
    }
    
    /**
     * Test question getter
     * @return the test question
     */
    public final String getQuestion() {
        return question;
    }
    
    /**
     * Media type getter
     * @return the media type; N for none, I for images, A for audio files, and V for video files
     */
    public final MediaType getMediaType() {
        return mediaType;
    }
    
    /**
     * Media file name getter
     * @return the media file name
     */
    public final String getMediaFileName() {
        return mediaFileName;
    }

    /**
     * Number of possible answers to the test question getter
     * @return the number of possible answers to the test question; must be less than six
     */
    public final int getNumberOfChoices() {
        return numberOfChoices;
    }
    
    /**
     * Possible answers to the test question getter
     * @return the possible answers to the test question
     */
    public final ArrayList<String> getChoices() {
        return choices;
    }

    /**
     * The index number of the correct answer getter
     * @return the location of the correct answer in the array
     */
    public final int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
    
    /**
     * Question explanation getter
     * @return the explanation of the answer
     */
    public final String getExplanation() {
        return explanation;
    }
    
    /**
     * Question type setter
     * @param questionType the question type; K for Key Term, M for Multiple Choice, T for True or False
     */
    private void setQuestionType(QuestionType questionType) {
        if(questionType == null) throw new NullPointerException("Question type cannot be null.");
        this.questionType = questionType;
    }
    
    /**
     * Test question setter
     * @param question the test question
     */
    private void setQuestion(String question) {
        if(Utilities.isNullOrEmpty(question)) throw new NullPointerException("Question cannot be null or empty.");
        else this.question = question;
    }

    /**
     * Media type setter
     * @param mediaType the media type; N for none, I for images, A for audio files, and V for video files
     */
    private void setMediaType(MediaType mediaType) {
        if(mediaType == null) throw new NullPointerException("Media type cannot be null.");
        this.mediaType = mediaType;
    }
    
    /**
     * Media file name setter
     * @param mediaFileName the media file name
     */
    private void setMediaFileName(String mediaFileName) {
        this.mediaFileName = mediaFileName;
    }
    
    /**
     * Number of possible answers to the test question setter
     * @param numberOfChoices the number of possible answers to the test question; must be less than six
     */
    private void setNumberOfChoices(int numberOfChoices) {
        if(numberOfChoices <= 0) throw new IllegalArgumentException("The number of choices cannot be null or zero.");
        if(numberOfChoices > 6) throw new IllegalArgumentException("The number of multiple choice answers cannot be greater than six.");
        else this.numberOfChoices = numberOfChoices;
    }
    
    /**
     * Possible answers to the test question setter
     * @param choices the possible answers to the test question
     */
    private void setChoices(ArrayList<String> choices) {
        if(choices == null) throw new NullPointerException("Questions must have at least one choice.");
        else this.choices = choices;
    }

    /**
     * The index number of the correct answer setter
     * @param correctAnswerIndex the location of the correct answer in the array
     */
    private void setCorrectAnswerIndex(int correctAnswerIndex) {
        if(correctAnswerIndex < 0) throw new IllegalArgumentException("The number of choices cannot less than zero.");
        else this.correctAnswerIndex = correctAnswerIndex;
    }
    
    /**
     * Question explanation setter
     * @param explanation the explanation of the answer
     */
    private void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    
    
    /**
     * Constructor for adding multiple choice and true/false questions
     * @param questionType the question type; K for Key Term, M for Multiple Choice, T for True or False
     * @param question the test question
     * @param mediaType the media type; N for none, I for images, A for audio files, and V for video files
     * @param mediaFileName the media file name
     * @param numberOfChoices the number of possible answers to the test question; must be less than six
     * @param choices the possible answers to the test question
     * @param correctAnswerIndex the location of the correct answer in the array
     * @param explanation the explanation of the answer
     */
    public TestQuestion(QuestionType questionType, String question, MediaType mediaType, String mediaFileName, int numberOfChoices, ArrayList<String> choices, int correctAnswerIndex, String explanation) {
        this.setQuestionType(questionType);
        this.setQuestion(question);
        this.setMediaType(mediaType);
        this.setMediaFileName(mediaFileName);
        this.setNumberOfChoices(numberOfChoices);
        this.setChoices(choices);
        this.setCorrectAnswerIndex(correctAnswerIndex);
        this.setExplanation(explanation);
    }
}
