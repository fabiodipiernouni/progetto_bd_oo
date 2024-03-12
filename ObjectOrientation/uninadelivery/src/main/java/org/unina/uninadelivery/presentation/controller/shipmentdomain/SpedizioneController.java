package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.customerdomain.SpedizioneModel;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.util.ResourceBundle;

public class SpedizioneController implements Initializable {

    private final SpedizioneModel spedizioneModel;
    @FXML
    public Label lblCntOrdiniTrasportoDaCompletare;

    @FXML
    protected Label lblCntOrdiniPackagingDaCompletare;
    @FXML
    protected Label lblCntPacchiDaSpedire;
    @FXML
    protected MFXButton pacchiButton;
    @FXML
    protected MFXButton odlTrasportoButton;
    @FXML
    protected MFXButton odlPackagingButton;
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
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        Stage dashboardStage = (Stage)session.getSessionData("dashboardStage");
        OdlOrchestrator orchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);

        //Init delle label
        lblNumeroSpedizione.setText(spedizioneModel.getNumeroSpedizione());
        lblRagSoc.setText(spedizioneModel.getRagioneSocialeCliente());
        lblStato.setText(spedizioneModel.getStato());
        lblOrganizzatore.setText(spedizioneModel.getOrganizzatore());
        lblTrackingNum.setText(spedizioneModel.getTrackingNumber());
        lblDataCreazione.setText(spedizioneModel.getDataCreazione().toString());

        if (spedizioneModel.getDataInizioLavorazione() != null) {
            lblDataInizioLav.setText(spedizioneModel.getDataInizioLavorazione().toString());
        } else {
            lblDataInizioLav.setText("N/A");
        }

        if (spedizioneModel.getDataFineLavorazione() != null) {
            lblDataFineLav.setText(spedizioneModel.getDataFineLavorazione().toString());
        } else {
            lblDataFineLav.setText("N/A");
        }

        lblCntOrdiniPackagingEmessi.setText(String.valueOf(spedizioneModel.getNumeroOrdiniPackaging()));
        lblCntOrdiniPackagingDaCompletare.setText(String.valueOf(spedizioneModel.getNumeroOrdiniPackagingDaCompletare()));
        lblCntPacchiGenerati.setText(String.valueOf(spedizioneModel.getNumeroPacchiGenerati()));
        lblCntPacchiDaSpedire.setText(String.valueOf(spedizioneModel.getNumeroPacchiDaSpedire()));
        lblCntOrdiniTrasportoEmessi.setText(String.valueOf(spedizioneModel.getNumeroOrdiniTrasporto()));
        lblCntOrdiniTrasportoDaCompletare.setText(String.valueOf(spedizioneModel.getNumeroOrdiniTrasportoDaCompletare()));

        imgCopyToTracking.setOnMouseClicked(e -> {
            javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
            javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
            content.putString(spedizioneModel.getTrackingNumber());
            clipboard.setContent(content);
            //todo: dare feedback della copia all'utente
        });

        if (spedizioneModel.getStato().equals("DaLavorare")) {
            if (utente.getProfilo().equals("OperatoreCorriere")) {
                odlPackagingButton.setText("Genera");
                odlPackagingButton.setOnAction(e -> {
                    //todo chiamata all'orchestratore
                    orchestrator.generaOdlPackagingClicked(spedizioneModel.getNumeroSpedizione());
                });
            } else {
                odlPackagingButton.setVisible(false);
            }
            pacchiButton.setVisible(false);
            odlTrasportoButton.setVisible(false);
        } else {
            odlPackagingButton.setText("Visualizza");
            odlPackagingButton.setOnAction(e -> {
                //todo chiamata all'orchestratore
            });
        }

        if (spedizioneModel.getStato().equals("LavorataPackaging")) {
            odlTrasportoButton.setText("Genera");
        } else if (spedizioneModel.getStato().equals("InLavorazioneSpedizione") || spedizioneModel.getStato().equals("LavorataSpedizione")) {
            odlTrasportoButton.setText("Visualizza");
        } else {
            odlTrasportoButton.setVisible(false);
        }

        pacchiButton.setVisible(spedizioneModel.getNumeroPacchiGenerati() > 0);
    }
}
