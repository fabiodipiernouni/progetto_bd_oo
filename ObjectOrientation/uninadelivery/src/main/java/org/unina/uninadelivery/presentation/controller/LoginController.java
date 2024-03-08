package org.unina.uninadelivery.presentation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestration;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestrator;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField txtUsername;
    @FXML
    public PasswordField txtPassword;
    public Button loginButton;
    @FXML
    private Label lblError;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        /*Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();*/

        LoginOrchestration orchestrator = LoginOrchestrator.getLoginOrchestrator();
        orchestrator.loginClicked(txtUsername.getText(), txtPassword.getText());
    }

    public void SetLabelError(String error) {
        lblError.setText(error);
    }


}
