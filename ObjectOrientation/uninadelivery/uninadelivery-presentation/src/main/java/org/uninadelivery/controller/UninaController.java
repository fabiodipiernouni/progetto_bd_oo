package org.uninadelivery.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.uninadelivery.controller.appdomain.LoginController;

import java.io.IOException;

public class UninaController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button btn;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();

        Popup popup = new Popup();
        //LoginController controller = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/appdomain/login-view.fxml"));
        //loader.setController(controller);
        popup.getContent().add((Parent) loader.load());
        popup.show(stage);
    }
}