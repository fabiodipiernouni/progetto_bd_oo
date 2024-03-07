package org.unina.uninadelivery.presentation.controller.appdomain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestration;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private final Stage primaryStage;
    @FXML
    public TextField txtUsername;
    @FXML
    public PasswordField txtPassword;
    public Button loginButton;
    @FXML
    private Label lblError;

    public LoginController(Stage stage) {
        this.primaryStage = stage;

    }

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
