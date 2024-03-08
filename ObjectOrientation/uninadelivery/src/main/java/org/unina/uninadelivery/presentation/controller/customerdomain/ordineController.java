package org.unina.uninadelivery.presentation.controller.customerdomain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.unina.uninadelivery.entity.customerdomain.OrdineDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class ordineController implements Initializable {
    private final OrdineDTO ordineDTO;
    @FXML
    public Label lblTempIdOrdine;

    public ordineController(OrdineDTO ordineDTO) {

        this.ordineDTO = ordineDTO;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTempIdOrdine.setText("Ricevuto ordine con id" + ordineDTO.Id + ", in data " + ordineDTO.DataOrdine.toString() + ".");
    }
}
