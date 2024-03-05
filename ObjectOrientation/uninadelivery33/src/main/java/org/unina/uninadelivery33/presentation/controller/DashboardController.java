package org.unina.uninadelivery33.presentation.controller;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery33.presentation.helper.Session;
import org.unina.uninadelivery33.presentation.orchestrator.appdomain.LoginOrchestrator;

public class DashboardController {


    private final Stage dashboardStage;
    @FXML
    public Label eleAppartenenzaLabel;
    @FXML
    public Label utenteLabel;
    @FXML
    public Label filialeGruppoLabel;
    @FXML
    public Label matricolaLabel;
    public StackPane logoContainer;
    public MFXScrollPane scrollPane;
    public VBox navBar;
    public AnchorPane rootPane;
    public MFXFontIcon alwaysOnTopIcon;
    public MFXFontIcon minimizeIcon;
    public MFXFontIcon closeIcon;
    public HBox windowHeader;
    public StackPane contentPane;
    public AnchorPane side_anchorpane;
    public HBox root;

    public DashboardController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
    }

    public void setupUserData() {
        Session session = Session.getInstance();
        Property<UtenteDTO> prop = session.getUserDto();
        Scene scene = utenteLabel.getScene();

        //Aggiungo un evento per gestire il momento del logout. Il logout non farà altro che invalidare l'utente corrente
        //a quel punto il sistema chiederà di fare il login
        prop.addListener((observable, oldValue, newValue) -> {
            if(newValue == null || newValue.getUsername().isEmpty()) {
                LoginOrchestrator loginOrchestrator = LoginOrchestrator.getLoginOrchestrator();
                loginOrchestrator.showLoginPopup(scene);
            }
        });

        UtenteDTO userData = session.getUserDto().getValue();

        utenteLabel.setText(userData.getUsername());
    }

    public void onBtnLogout(ActionEvent actionEvent) {
        Session session = Session.getInstance();
        Property<UtenteDTO> user = session.getUserDto();
        user.setValue(new UtenteDTO());
    }
}