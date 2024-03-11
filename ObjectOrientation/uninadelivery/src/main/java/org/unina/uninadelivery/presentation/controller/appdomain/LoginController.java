package org.unina.uninadelivery.presentation.controller.appdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXTooltip;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Setter;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.helper.ResourceLoader;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestration;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestrator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @Setter
    public Stage dashboardStage;

    @FXML
    public MFXTextField txtUsername;
    @FXML
    public MFXPasswordField txtPassword;
    public MFXButton loginButton;
    @FXML
    public MFXFontIcon closeIcon;
    @FXML
    public BorderPane loginBp;
    /*@FXML
    private Label lblError;*/

    public LoginController() {
    }

    @FXML
    protected void onLoginButtonClick() {

        DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();

        if(txtUsername.getText().equals("")) {
            dashboardController.showDialog("error", "Errore di login", "Inserire username");
            //SetLabelError("Inserire username");
            return;
        }

        if(txtPassword.getText().equals("")) {
            dashboardController.showDialog("error", "Errore di login", "Inserire password");
            //SetLabelError("Inserire password");
            return;
        }

        LoginOrchestration orchestrator = LoginOrchestrator.getLoginOrchestrator();
        orchestrator.loginClicked(txtUsername.getText(), txtPassword.getText());
    }

    /*public void SetLabelError(String error) {
        lblError.setText(error);
    }*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBp.getStylesheets().add(ResourceLoader.load("/css/Primary.css"));

        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Platform.exit();
            System.exit(0);
        });

        MFXTooltip.of(closeIcon, "Chiudi").install();

        txtUsername.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER )
                onLoginButtonClick();

        } );

        txtPassword.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER )
                onLoginButtonClick();

        } );

    }


}
