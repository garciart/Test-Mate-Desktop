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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import testmatedesktop.Constants.*;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class TestMateDesktopController implements Initializable {

    private boolean takingTest = false;
    private int count = 0;
    private final Settings settings = new Settings();
    private String testName;
    private Timeline timeline;

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
    @FXML
    private CheckMenuItem provideFeedbackMenuItem;
    @FXML
    private CheckMenuItem questionOrderMenuItem;
    @FXML
    private CheckMenuItem hideClockMenuItem;
    @FXML
    private ToggleGroup choiceGroup;
    @FXML
    private ToggleGroup keyTermDisplay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            settings.getSettingsFromFile();
            provideFeedbackMenuItem.setSelected(settings.getProvideFeedbackSetting() == ProvideFeedback.YES);
            questionOrderMenuItem.setSelected(settings.getQuestionOrderSetting() == QuestionOrder.RANDOM);
            keyTermDisplay.selectToggle(keyTermDisplay.getToggles().get(settings.getTermDisplaySetting().ordinal()));
        } catch (IOException ex) {
            errorMessage("Unable to read settings file: " + ex.toString() + "\nApplying default settings...");
            settings.setQuestionOrderSetting(QuestionOrder.DEFAULT);
            settings.setTermDisplaySetting(TermDisplay.DEFISQUESTION);
            settings.setProvideFeedbackSetting(ProvideFeedback.YES);
            try {
                settings.saveSettingsToFile();
            } catch (IOException ex1) {
                errorMessage("Unable to reset settings file: " + ex.toString() + "\nExiting application...");
                exit(0);
            }
        }
    }

    public void startNewTest() throws IOException {
        if (confirmRestart()) {
            try {
                Stage stage = (Stage) ap.getScene().getWindow();
                FileChooser chooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Test Mate files (*.tmf)", "*.tmf");
                chooser.getExtensionFilters().add(extFilter);
                chooser.setTitle("Start New Test...");
                chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                File file = chooser.showOpenDialog(stage);
                if (file != null) {
                    testName = file.toString();
                    if (takingTest) {
                        restartTest();
                    }
                    administerTest(testName);
                }
            } catch (IOException ex) {
                errorMessage(ex.toString());
            }
        }
    }

    void administerTest(String testName) throws IOException {
        takingTest = true;
        nextButton.setDisable(false);
        reviewButton.setDisable(false);
        mediaButton.setDisable(false);
        count = 0;
        int correctAnswerCount = 0;
        startTimer(System.nanoTime());
        Test test = new Test();
        final ArrayList<TestQuestion> testQuestion = test.getTest(testName, settings.getQuestionOrderSetting(), settings.getTermDisplaySetting());
        titleLabel.setText(test.getTestTitle());
        String userResults[][] = new String[testQuestion.size()][3];
        displayQuestion(count, testQuestion.get(count));
        questionNumberLabel.setText((count + 1) + " of " + testQuestion.size());
        nextButton.setOnAction((ActionEvent e) -> {
            Toggle toggle = choiceGroup.getSelectedToggle();
            if (toggle != null) {
                int userChoice = (int) toggle.getUserData();
                boolean result = (userChoice == testQuestion.get(count).getCorrectAnswerIndex());
                if (settings.getProvideFeedbackSetting() == ProvideFeedback.YES) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Feedback");
                    alert.setHeaderText(result ? "Correct!" : "Sorry!");
                    alert.setContentText(testQuestion.get(count).getExplanation());
                    alert.showAndWait();
                }
                count++;
                if (count < testQuestion.size()) {
                    questionNumberLabel.setText((count + 1) + " of " + testQuestion.size());
                    displayQuestion(count, testQuestion.get(count));
                } else {
                    finishTest();
                }
            } else {
                (new Alert(AlertType.INFORMATION, ("Nothing selected!"), ButtonType.OK)).showAndWait();
            }
        });
        reviewButton.setOnAction((ActionEvent e) -> {
            if (count > 0) {
                count--;
                questionNumberLabel.setText((count + 1) + " of " + testQuestion.size());
                displayQuestion(count, testQuestion.get(count));
            }
        });
        mediaButton.setOnAction((ActionEvent e) -> {
            // try {
                final Stage mediaDialog = new Stage();
                mediaDialog.initModality(Modality.WINDOW_MODAL);
                mediaDialog.setMaxWidth(640.0);
                mediaDialog.setMaxHeight(480.0);
                Group root = new Group();
                Scene mediaScene = new Scene(root);
                Media media = new Media(new File("History.mp4").toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                MediaView mediaView = new MediaView(mediaPlayer);
                root.getChildren().add(mediaView);
                /*
                Image image = new Image(new FileInputStream("art.jpg"));
                ImageView imageView = new ImageView(image);
                root.getChildren().add(imageView);
                */
                /*
                Button closeButton = new Button("Close");
                closeButton.setOnAction((ActionEvent e1) -> {
                    mediaDialog.close();
                });
                root.getChildren().add(closeButton);
                */
                mediaDialog.setScene(mediaScene);
                mediaDialog.show();
            //} catch (FileNotFoundException ex) {
            //    errorMessage(ex.toString());
            //}
        });
    }

    @FXML
    boolean confirmRestart() {
        if (takingTest) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will end your current test.\nDo you wish to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            return ((result.get() == ButtonType.OK));
        } else {
            return true;
        }
    }

    @FXML
    void menuExit() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void displayQuestion(int questionNumber, TestQuestion tq) {
        questionLabel.setText(tq.getQuestion());
        choiceBox.getChildren().clear();
        choiceGroup.getToggles().clear();
        for (int y = 0; y <= tq.getNumberOfChoices(); y++) {
            RadioButton rb = new RadioButton(tq.getChoices().get(y));
            rb.setToggleGroup(choiceGroup);
            rb.setUserData(y);
            rb.setWrapText(true);
            rb.setAlignment(Pos.TOP_LEFT);
            rb.setToggleGroup(choiceGroup);
            choiceBox.getChildren().add(rb);
        }
    }

    @FXML
    void menuOnTop() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setAlwaysOnTop(onTopMenuItem.isSelected());
    }

    @FXML
    void menuHideClock() {
        testTimeLabel.setVisible(hideClockMenuItem.isSelected());
    }

    @FXML
    void menuTermDisplay() {
        if (confirmRestart()) {
            TermDisplay td = TermDisplay.values()[keyTermDisplay.getToggles().indexOf(keyTermDisplay.getSelectedToggle())];
            settings.setTermDisplaySetting(td);
            try {
                settings.saveSettingsToFile();
                if (takingTest) {
                    restartTest();
                }
                administerTest(testName);
            } catch (IOException ex) {
                errorMessage("Unable to reset settings file: " + ex.toString() + "\nExiting application...");
                exit(0);
            }
        }
    }

    @FXML
    void menuProvideFeedback() {
        if (confirmRestart()) {
            settings.setProvideFeedbackSetting(provideFeedbackMenuItem.isSelected() ? ProvideFeedback.YES : ProvideFeedback.NO);
            try {
                settings.saveSettingsToFile();
                if (takingTest) {
                    restartTest();
                }
                administerTest(testName);
            } catch (IOException ex) {
                errorMessage("Unable to reset settings file: " + ex.toString() + "\nExiting application...");
                exit(0);
            }
        }
    }

    @FXML
    void menuQuestionOrder() {
        if (confirmRestart()) {
            settings.setQuestionOrderSetting(questionOrderMenuItem.isSelected() ? QuestionOrder.RANDOM : QuestionOrder.DEFAULT);
            try {
                settings.saveSettingsToFile();
                if (takingTest) {
                    restartTest();
                }
                administerTest(testName);
            } catch (IOException ex) {
                errorMessage("Unable to reset settings file: " + ex.toString() + "\nExiting application...");
                exit(0);
            }
        }
    }

    @FXML
    void menuHelp() {
        // "TestMateHelp.html"
    }

    @FXML
    void menuAbout() {
        Alert aboutAlert = new Alert(AlertType.INFORMATION, ("Copyright 1993-" + Calendar.getInstance().get(Calendar.YEAR) + " Robert Garcia\n\n"
                + "Test Mate is a mobile self-study system, designed to assist you in achieving your educational and professional goals by allowing you to study what you want, when and where you want.\n\n"
                + "Why study alone?"), ButtonType.OK);
        aboutAlert.setTitle("Test Mate!");
        aboutAlert.setHeaderText("Test Mate!");
        aboutAlert.setGraphic(new ImageView("file:tmicon48a.png"));
        Stage stage = (Stage) aboutAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:tmicon48a.png"));
        aboutAlert.showAndWait();
    }

    public void errorMessage(String errorMessage) {
        new Alert(AlertType.ERROR, ("Oops! Something went wrong!\n\n" + errorMessage + "\n\nWe've been notified and will start fixing the problem right away!"), ButtonType.OK).showAndWait();
    }

    void startTimer(long startTime) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent t) -> {
            long elapsedTime = System.nanoTime() - startTime;
            testTimeLabel.setText(String.format("%02d:%02d:%02d",
                    TimeUnit.NANOSECONDS.toHours(elapsedTime),
                    TimeUnit.NANOSECONDS.toMinutes(elapsedTime),
                    TimeUnit.NANOSECONDS.toSeconds(elapsedTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(elapsedTime))));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    void restartTest() {
        count = 0;
        timeline.stop();
    }

    void finishTest() {
        nextButton.setDisable(true);
        reviewButton.setDisable(true);
        count = 0;
        choiceGroup.getToggles().forEach(toggle -> {
            Node node = (Node) toggle;
            node.setDisable(true);
        });
        timeline.stop();
    }
}
