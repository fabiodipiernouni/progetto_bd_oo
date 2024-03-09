package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class SpedizioneController implements Initializable {

    private final SpedizioneDTO spedizioneDTO;
    @FXML
    public Label lblCntOrdiniPackagingEmessi;

    public SpedizioneController(SpedizioneDTO spedizioneDTO) {
        this.spedizioneDTO = spedizioneDTO;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
