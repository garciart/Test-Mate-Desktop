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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class TestMateDesktopController implements Initializable {

    public boolean takingTest = false;
    private int count = 0;

    @FXML
    private AnchorPane ap;
    @FXML
    private Label testTimeLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label questionNumberLabel;
    @FXML
    private SplitPane sp;
    @FXML
    private AnchorPane leftAP;
    @FXML
    private ScrollPane questionPane;
    @FXML
    private Label questionLabel;
    @FXML
    private AnchorPane rightAP;
    @FXML
    private ScrollPane choicePane;
    @FXML
    private VBox choiceBox;
    @FXML
    private Button reviewButton;
    @FXML
    private Button scoreButton;
    @FXML
    private Button mediaButton;
    @FXML
    private Button nextButton;
    @FXML
    private CheckMenuItem onTopMenuItem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void startNewTest() throws IOException {
        if (checkTestStatus()) {
            Settings s = new Settings();
            try {
                s.getSettingsFromFile();
            } catch (IOException ex) {
                errorMessage("Unable to read settings file: " + ex.toString() + "\nApplying default settings...");
                s.setQuestionOrderSetting(Constants.QuestionOrder.DEFAULT);
                s.setTermDisplaySetting(Constants.TermDisplay.DEFISQUESTION);
                s.setProvideFeedbackSetting(Constants.ProvideFeedback.NO);
                s.saveSettingsToFile();
            }
            try {
                Stage stage = (Stage) ap.getScene().getWindow();
                FileChooser chooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Test Mate files (*.tmf)", "*.tmf");
                chooser.getExtensionFilters().add(extFilter);
                chooser.setTitle("Start New Test...");
                chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                File file = chooser.showOpenDialog(stage);
                if (file != null) {
                    takingTest = true;
                    nextButton.setDisable(false);
                    String testName = file.toString();
                    int correctAnswerCount = 0;
                    long startTime = System.nanoTime();
                    Test test = new Test();
                    final ArrayList<TestQuestion> testQuestion = test.getTest(testName, s.getQuestionOrderSetting(), s.getTermDisplaySetting());
                    titleLabel.setText(test.getTestTitle());
                    String userResults[][] = new String[testQuestion.size()][3];
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            long elapsedTime = System.nanoTime() - startTime;
                            testTimeLabel.setText(String.format("%02d:%02d:%02d",
                                    TimeUnit.NANOSECONDS.toHours(elapsedTime),
                                    TimeUnit.NANOSECONDS.toMinutes(elapsedTime),
                                    TimeUnit.NANOSECONDS.toSeconds(elapsedTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(elapsedTime))));
                        }
                    }));
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                    askQuestion(count, testQuestion.get(count));
                    questionNumberLabel.setText((count + 1) + " of " + testQuestion.size());
                    nextButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            count++;
                            if (count < testQuestion.size()) {
                                questionNumberLabel.setText((count + 1) + " of " + testQuestion.size());
                                askQuestion(count, testQuestion.get(count));
                            } else {
                                nextButton.setDisable(true);
                                count = 0;
                            }
                        }
                    });
                    // System.out.println();
                }
            } catch (Exception ex) {
                errorMessage(ex.toString());
            }
        }
    }

    @FXML
    public boolean checkTestStatus() {
        if (takingTest) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will end your current test.\nDo you wish to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            return ((result.get() == ButtonType.OK));
        } else {
            return true;
        }
    }

    @FXML
    public void exitTestMate() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public int askQuestion(int questionNumber, TestQuestion tq) {
        choiceBox.getChildren().clear();
        questionLabel.setText(tq.getQuestion());
        ToggleGroup choiceGroup = new ToggleGroup();
        for (int y = 0; y <= tq.getNumberOfChoices(); y++) {
            RadioButton rb = new RadioButton(tq.getChoices().get(y));
            rb.setToggleGroup(choiceGroup);
            rb.setId("rb" + y);
            rb.setWrapText(true);
            rb.setAlignment(Pos.TOP_LEFT);
            choiceBox.getChildren().add(rb);
        }

        // numberOfChoices is zero-based and getAndValidateChoice is one-based
        // Add one to use getAndValidateChoice and subtract 1 to return the correct index
        return 1;
    }
    
    @FXML
    void menuOnTop() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setAlwaysOnTop(onTopMenuItem.isSelected());
    }

    public void errorMessage(String errorMessage) {
        new Alert(AlertType.ERROR, ("Oops! Something went wrong!\n\n" + errorMessage + "\n\nWe've been notified and will start fixing the problem right away!"), ButtonType.OK).showAndWait();
    }
}
