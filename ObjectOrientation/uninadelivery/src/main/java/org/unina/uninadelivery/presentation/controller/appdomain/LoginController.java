package org.unina.uninadelivery.presentation.controller.appdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXTooltip;
import io.github.palexdev.materialfx.validation.Constraint;
import io.github.palexdev.materialfx.validation.Severity;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Setter;
import org.unina.uninadelivery.presentation.exception.LoginErrorException;
import org.unina.uninadelivery.presentation.helper.ResourceLoader;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestration;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestrator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static io.github.palexdev.materialfx.validation.Validated.INVALID_PSEUDO_CLASS;

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
    @FXML
    public Label validationLabel;


    public LoginController() {
    }

    @FXML
    protected void onLoginButtonClick() {
        try {
            if (txtUsername.isValid() && txtPassword.isValid()) {
                LoginOrchestration orchestrator = LoginOrchestrator.getLoginOrchestrator();
                Boolean isValid = orchestrator.loginClicked(txtUsername.getText(), txtPassword.getText());
                if (!isValid) {
                    txtUsername.getStyleClass().add("invalid");
                    txtPassword.getStyleClass().add("invalid");
                    validationLabel.setText("Username o password non corretti");
                    validationLabel.setVisible(true);
                } else {
                    txtUsername.getStyleClass().remove("invalid");
                    txtPassword.getStyleClass().remove("invalid");
                    validationLabel.setVisible(false);
                }
            }
        } catch (LoginErrorException e) {
            validationLabel.setText("Errore durante il login (" + e.getMessage() + ")");
            validationLabel.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBp.getStylesheets().add(ResourceLoader.load("/css/Primary.css"));

        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Platform.exit();
            System.exit(0);
        });

        MFXTooltip.of(closeIcon, "Chiudi").install();

        txtUsername.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                onLoginButtonClick();
        });

        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                onLoginButtonClick();
        });

        Constraint userEmptyConstraint = Constraint.Builder.build()
                .setSeverity(Severity.ERROR)
                .setMessage("Valorizzare la username correttamente.")
                .setCondition(txtUsername.textProperty().length().greaterThan(3)) //condizione di ok
                .get();

        Constraint passEmptyConstraint = Constraint.Builder.build()
                .setSeverity(Severity.ERROR)
                .setMessage("Valorizzare la password correttamente.")
                .setCondition(txtPassword.textProperty().length().greaterThan(3)) //condizione di ok
                .get();

        //
        txtUsername.getValidator().constraint(userEmptyConstraint);
        txtPassword.getValidator().constraint(passEmptyConstraint);

        txtUsername.getValidator().validProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                validationLabel.setVisible(false);
                txtUsername.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, false);
            }
        });

        txtPassword.getValidator().validProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                validationLabel.setVisible(false);
                txtPassword.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, false);
            }
        });

        txtUsername.delegateFocusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                List<Constraint> constraints = txtUsername.validate();
                if (!constraints.isEmpty()) {
                    txtUsername.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, true);
                    validationLabel.setText(constraints.getFirst().getMessage());
                    validationLabel.setVisible(true);
                } else {
                    txtUsername.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, false);
                    validationLabel.setVisible(false);
                }
            }
        });

        txtPassword.delegateFocusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                List<Constraint> constraints = txtPassword.validate();
                if (!constraints.isEmpty()) {
                    txtPassword.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, true);
                    validationLabel.setText(constraints.getFirst().getMessage());
                    validationLabel.setVisible(true);
                } else {
                    txtPassword.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, false);
                    validationLabel.setVisible(false);
                }
            }
        });

        loginButton.disableProperty().bind(txtUsername.getValidator().validProperty().not().or(txtPassword.getValidator().validProperty().not()));

    }


}
