package org.unina.uninadelivery.presentation.controller;

import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestrator;

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