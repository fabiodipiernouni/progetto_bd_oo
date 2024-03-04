package org.unina.uninadelivery33.presentation.controller;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery33.presentation.helper.Session;
import org.unina.uninadelivery33.presentation.orchestrator.appdomain.LoginOrchestrator;

import java.io.IOException;

public class UninaController {


    @FXML
    public Label eleAppartenenzaLabel;
    @FXML
    public Label utenteLabel;
    @FXML
    public Label filialeGruppoLabel;
    @FXML
    public Label matricolaLabel;

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