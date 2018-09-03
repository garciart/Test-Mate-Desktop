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

import testmatedesktop.Constants.*;

/**
 * TestMate model abstract class for test data objects
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public abstract class TestData {
    private QuestionType questionType = QuestionType.K;
    private MediaType mediaType = MediaType.N;
    private String mediaFileName = new String();
    private String explanation = new String();
    
    public final QuestionType getQuestionType() {
        return questionType;
    }

    public final MediaType getMediaType() {
        return mediaType;
    }
    
    public final String getMediaFileName() {
        return mediaFileName;
    }
    
    public final String getExplanation() {
        return explanation;
    }
    
    public final void setQuestionType(QuestionType questionType) {
        if(questionType == null) throw new NullPointerException("Question type cannot be null.");
        this.questionType = questionType;
    }

    public final void setMediaType(MediaType mediaType) {
        if(mediaType == null) throw new NullPointerException("Media type cannot be null.");
        this.mediaType = mediaType;
    }
    
    public final void setMediaFileName(String mediaFileName) {
        this.mediaFileName = mediaFileName;
    }
    
    public final void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    
    protected final void validateAndSetMedia(MediaType mediaType, String mediaFileName) {
        if(mediaType == MediaType.N) {
            if(!mediaFileName.toLowerCase().equals("null")) throw new IllegalArgumentException("Filename should be NULL.");
        }
        else {
            if(mediaFileName.toLowerCase().equals("null") || Utilities.isNullOrEmpty(mediaFileName)) throw new IllegalArgumentException("Missing media file name.");
            switch(mediaType) {
                case I: {
                    if(!mediaFileName.matches("^[\\w\\- ]+(.jpg|.png)$")) throw new IllegalArgumentException("Media format not supported for that media type.");
                    break;
                }
                case A: {
                    if(!mediaFileName.matches("^[\\w\\- ]+(.mp3)$")) throw new IllegalArgumentException("Media format not supported for that media type.");
                    break;
                }
                case V: {
                    if(!mediaFileName.matches("^[\\w\\- ]+(.mpg|.mpeg|.mp4)$")) throw new IllegalArgumentException("Media format not supported for that media type.");
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unsupported media type.");
                }
            }
            setMediaFileName(mediaFileName);
        }
    }
}
