package org.unina.uninadelivery.presentation.controller.orgdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.ManagerOrchestrator;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;

public class HomeManagerController implements Initializable {

    private final Stage dashboardStage;

    private ManagerOrchestrator managerOrchestrator;

    @FXML
    private MFXDatePicker filtroDataInizio;

    @FXML
    private MFXDatePicker filtroDataFine;

    @FXML
    private Label lblCntNumeroTotaleOrdini;

    @FXML
    private Label lblCntNumeroMedioOrdini;

    @FXML
    private Label lblCntNumeroSpedizioniCreate;

    @FXML
    private Label lblCntNumeroSpedizioniCompletate;

    @FXML
    private Label lblNumeroOrdineMaggiorNumeroProdotti;

    @FXML
    private Label lblNumeroOrdineMinorNumeroProdotti;

    @FXML
    private MFXButton btnOrdineMaggiorNumeroProdotti;

    @FXML
    private MFXButton btnOrdineMinorNumeroProdotti;

    public HomeManagerController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
        this.managerOrchestrator = new ManagerOrchestrator(dashboardStage, this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filtroDataInizio.setOnAction(event -> updateData());
        filtroDataFine.setOnAction(filtroDataInizio.getOnAction());

        filtroDataInizio.setValue(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        filtroDataFine.setValue(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
        filtroDataInizio.fireEvent(new ActionEvent());
    }

    public void updateData() {
        LocalDate dataInizio = filtroDataInizio.getValue();
        LocalDate dataFine = filtroDataFine.getValue();

        if (dataFine != null && dataInizio != null && dataInizio.isAfter(dataFine)) {
            filtroDataInizio.setValue(null);
            filtroDataFine.setValue(null);
            managerOrchestrator.wrongFilters();
        } else
            managerOrchestrator.caricaStatistiche(dataInizio, dataFine);
    }

    public void setNumeroTotaleOrdini(int numeroTotaleOrdini) {
        lblCntNumeroTotaleOrdini.setText(String.valueOf(numeroTotaleOrdini));
    }

    public void setNumeroMedioOrdini(float numeroMedioOrdini) {
        lblCntNumeroMedioOrdini.setText(String.format("%.2f", numeroMedioOrdini));
    }

    public void setNumeroSpedizioniCreate(int numeroSpedizioniCreate) {
        lblCntNumeroSpedizioniCreate.setText(String.valueOf(numeroSpedizioniCreate));
    }

    public void setNumeroSpedizioniCompletate(int numeroSpedizioniCompletate) {
        lblCntNumeroSpedizioniCompletate.setText(String.valueOf(numeroSpedizioniCompletate));
    }

    public void setOrdineMaggiorNumeroProdotti(OrdineClienteDTO ordine) {
        if (ordine == null) {
            lblNumeroOrdineMaggiorNumeroProdotti.setText("Nessun ordine");
            btnOrdineMaggiorNumeroProdotti.setVisible(false);
        } else {
            lblNumeroOrdineMaggiorNumeroProdotti.setText(ordine.getNumeroOrdine());
            btnOrdineMaggiorNumeroProdotti.setVisible(true);
            btnOrdineMaggiorNumeroProdotti.setOnMouseClicked(event -> {
                managerOrchestrator.visualizzaOrdine(ordine);
            });
        }
    }

    public void setOrdineMinorNumeroProdotti(OrdineClienteDTO ordine) {
        if (ordine == null) {
            lblNumeroOrdineMinorNumeroProdotti.setText("Nessun ordine");
            btnOrdineMinorNumeroProdotti.setVisible(false);
        } else {
            lblNumeroOrdineMinorNumeroProdotti.setText(ordine.getNumeroOrdine());
            btnOrdineMinorNumeroProdotti.setVisible(true);
            btnOrdineMinorNumeroProdotti.setOnMouseClicked(event -> {
                managerOrchestrator.visualizzaOrdine(ordine);
            });
        }
    }
}
