package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.unina.uninadelivery.presentation.model.customerdomain.SpedizioneModel;

import java.net.URL;
import java.util.ResourceBundle;

public class SpedizioneController implements Initializable {

    private final SpedizioneModel spedizioneModel;
    @FXML
    protected MFXButton btnGeneraPacchi;
    @FXML
    protected MFXButton btnGeneraOdlTrasporto;
    @FXML
    protected MFXButton btnGeneraOdlPackaging;
    @FXML
    protected ImageView imgCopyToTracking;
    @FXML
    protected Label lblNumeroSpedizione;
    @FXML
    protected Label lblRagSoc;
    @FXML
    protected Label lblStato;
    @FXML
    protected Label lblOrganizzatore;
    @FXML
    protected Label lblCntOrdiniPackagingEmessi;
    @FXML
    protected Label lblCntOrdiniTrasportoEmessi;
    @FXML
    protected Label lblCntPacchiGenerati;
    @FXML
    protected Label lblDataCreazione;
    @FXML
    protected Label lblDataInizioLav;
    @FXML
    protected Label lblDataFineLav;
    @FXML
    protected Label lblTrackingNum;

    public SpedizioneController(SpedizioneModel spedizioneModel) {
        this.spedizioneModel = spedizioneModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Init delle label
        lblNumeroSpedizione.setText(spedizioneModel.getNumeroSpedizione());
        lblRagSoc.setText(spedizioneModel.getRagioneSocialeCliente());
        lblStato.setText(spedizioneModel.getStato());
        lblOrganizzatore.setText(spedizioneModel.getOrganizzatore());
        lblTrackingNum.setText(spedizioneModel.getTrackingNumber());
        lblDataCreazione.setText(spedizioneModel.getDataCreazione().toString());

        if(spedizioneModel.getDataInizioLavorazione() != null) {
            lblDataInizioLav.setText(spedizioneModel.getDataInizioLavorazione().toString());
        }

        if(spedizioneModel.getDataFineLavorazione() != null) {
            lblDataFineLav.setText(spedizioneModel.getDataFineLavorazione().toString());
        }

        lblCntOrdiniPackagingEmessi.setText(String.valueOf(spedizioneModel.getNumeroOrdiniPackaging()));
        lblCntOrdiniTrasportoEmessi.setText(String.valueOf(spedizioneModel.getNumeroOrdiniTrasporto()));
        lblCntPacchiGenerati.setText(String.valueOf(spedizioneModel.getNumeroPacchiGenerati()));

        imgCopyToTracking.setOnMouseClicked(e -> {
            javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
                });

        btnGeneraOdlPackaging.setVisible(spedizioneModel.getNumeroOrdiniPackaging() == 0);
        btnGeneraPacchi.setVisible(spedizioneModel.getNumeroOrdiniPackaging() > 0 && spedizioneModel.getNumeroPacchiGenerati() == 0);
        btnGeneraOdlTrasporto.setVisible(spedizioneModel.getNumeroPacchiGenerati() > 0);

        if(spedizioneModel.getNumeroOrdiniPackaging() == 0) {

        }
    }
}
