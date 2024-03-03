package org.unina.uninadelivery33.presentation.controller;

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
        UtenteDTO userData = session.getUserDto().getValue();

        utenteLabel.setText(userData.getUsername());
    }

}