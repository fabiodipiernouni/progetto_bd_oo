package org.unina.uninadelivery.presentation.controller.customerdomain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class ordineController implements Initializable {
    private final OrdineClienteDTO ordineDTO;
    @FXML
    public Label lblTempIdOrdine;

    public ordineController(OrdineClienteDTO ordineDTO) {

        this.ordineDTO = ordineDTO;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTempIdOrdine.setText("Ricevuto ordine con id" + ordineDTO.getId() + ", in data " + ordineDTO.getDataOrdine().toString() + ".");
    }
}
