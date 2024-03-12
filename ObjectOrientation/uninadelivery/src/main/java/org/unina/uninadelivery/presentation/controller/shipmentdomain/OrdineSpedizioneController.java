package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.unina.uninadelivery.presentation.model.shipmentdomain.OrdineSpedizioneModel;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdineSpedizioneController implements Initializable {
    private final OrdineSpedizioneModel ordineSpedizioneModel;

    @FXML
    protected MFXButton actionButton;
    @FXML
    protected Label actionLabel;
    @FXML
    protected Label lblNumeroSpedizione;
    @FXML
    protected Label lblRagSoc;
    @FXML
    protected Label lblStato;
    @FXML
    protected Label lblOrganizzatore;
    @FXML
    protected Label lblDataCreazione;
    @FXML
    protected Label lblDataInizioLav;
    @FXML
    protected Label lblDataFineLav;
    @FXML
    protected Label lblTrackingNum;
    @FXML
    protected ImageView imgCopyToTracking;

    public OrdineSpedizioneController(OrdineSpedizioneModel ordineSpedizioneModel) {
        this.ordineSpedizioneModel = ordineSpedizioneModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblNumeroSpedizione.setText(String.valueOf(ordineSpedizioneModel.getCodiceOrdineSpedizione()));
        lblRagSoc.setText(ordineSpedizioneModel.getCliente());
        lblStato.setText(ordineSpedizioneModel.getStato());
        lblOrganizzatore.setText(ordineSpedizioneModel.getGruppoCorriere());
        lblDataCreazione.setText(ordineSpedizioneModel.getDataCreazione().toString());
        lblDataInizioLav.setText(ordineSpedizioneModel.getDataInizioLavorazione().toString());
        lblDataFineLav.setText(ordineSpedizioneModel.getDataFineLavorazione().toString());
        lblTrackingNum.setText(ordineSpedizioneModel.getSpedizione().getTrackingNumber());

        //todo completare
    }
}
