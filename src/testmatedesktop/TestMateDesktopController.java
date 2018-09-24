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
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Rob Garcia at rgarcia@rgprogramming.com
 */
public class TestMateDesktopController implements Initializable {

    public boolean takingTest = false;

    @FXML
    private AnchorPane ap;
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void startNewTest() {
        if (checkTestStatus()) {
            takingTest = true;
            Stage stage = (Stage) ap.getScene().getWindow();
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            File file = chooser.showOpenDialog(stage);
            System.out.println(file.toString());
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
}
