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

import testmatejava.Constants.*;

/**
 * TestMate model abstract class for test data objects
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public abstract class TestData {
    protected MediaFlag mediaFlag = MediaFlag.N;
    protected String mediaFileName;
    
    public final MediaFlag getMediaFlag() {
        return mediaFlag;
    }
    
    public final String getMediaFileName() {
        return mediaFileName;
    }
    
    public final void setMediaFlag(MediaFlag mediaFlag) {
        if(mediaFlag == null) throw new NullPointerException("Media flag cannot be null.");
        this.mediaFlag = mediaFlag;
    }
    
    public final void setMediaFileName(String mediaFileName) {
        this.mediaFileName = mediaFileName;
    }
    
    protected final void validateAndSetMedia(MediaFlag mediaFlag, String mediaFileName) {
        if(mediaFlag == MediaFlag.N && !Utility.isNullOrEmpty(mediaFileName)) throw new IllegalArgumentException("Filename should be NULL.");
        else {
            if(Utility.isNullOrEmpty(mediaFileName)) throw new IllegalArgumentException("Missing media file name.");
            switch(mediaFlag) {
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
