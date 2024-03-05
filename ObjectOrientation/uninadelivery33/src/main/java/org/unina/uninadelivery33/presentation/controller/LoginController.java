package org.unina.uninadelivery33.presentation.controller;

import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.unina.uninadelivery33.presentation.orchestrator.appdomain.LoginOrchestration;
import org.unina.uninadelivery33.presentation.orchestrator.appdomain.LoginOrchestrator;

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
    private final ToggleGroup toggleGroup;

    public LoginController(Stage stage) {
        this.primaryStage = stage;
        toggleGroup = new ToggleGroup();
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

    private ToggleButton createToggle(String icon, String text) {
        return createToggle(icon, text, 0);
    }

    private ToggleButton createToggle(String icon, String text, double rotate) {
        MFXIconWrapper wrapper = new MFXIconWrapper(icon, 24, 32);
        MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
        toggleNode.setAlignment(Pos.CENTER_LEFT);
        toggleNode.setMaxWidth(Double.MAX_VALUE);
        toggleNode.setToggleGroup(toggleGroup);
        if (rotate != 0) wrapper.getIcon().setRotate(rotate);
        return toggleNode;
    }
}
