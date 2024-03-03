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

import java.io.IOException;

public class UninaController {
    //private static final Logger logger = LoggerFactory.getLogger(UninaController.class);

    @FXML
    private Label welcomeText;

    @FXML
    private Button btn;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {

        var window = ((Node) event.getTarget()).getScene().getWindow();

        // Crea l'effetto di sfocatura
        GaussianBlur blur = new GaussianBlur();

        Stage stage = new Stage();
        stage.setTitle("popup");
        stage.setResizable(false);
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);

        stage.setOnShown(windowEvent -> {
            window.getScene().getRoot().setEffect(blur);
        });

        stage.setOnHidden(windowEvent -> {
            window.getScene().getRoot().setEffect(null);
        });

        //stage.setAlwaysOnTop(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/views/appdomain/login-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        stage.setScene(scene);
        stage.showAndWait();
    }
}