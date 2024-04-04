package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class SpedizioniController implements Initializable {

    private final Stage dashboardStage;
    private final UtenteDTO utente;
    private OdlOrchestrator odlOrchestrator;
    private final DashboardController dashboardController;
    @FXML
    protected MFXTableView<SpedizioneDTO> spedizioniGrid;

    @FXML
    protected MFXRadioButton filtroTuttiRadioBox;
    @FXML
    protected MFXRadioButton filtroEmesseDaMe;


    public SpedizioniController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
        this.dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        Session session = Session.getInstance();
        utente = session.getUserDto().getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);
        this.odlOrchestrator.setupSpedizioniController(this);
        spedizioniGrid.autosizeColumnsOnInitialization();

        ToggleGroup group = new ToggleGroup();

        filtroEmesseDaMe.setToggleGroup(group);
        filtroTuttiRadioBox.setToggleGroup(group);
        filtroTuttiRadioBox.setSelected(true);

        filtroTuttiRadioBox.setOnAction(event -> {
            this.updateData();
        });

        filtroEmesseDaMe.setOnAction(event -> {
            this.updateData();
        });

        setupTable();
    }

    private void setupTable() {
        MFXTableColumn<SpedizioneDTO> numeroOrdineColumn = new MFXTableColumn<>("Numero Ordine", false, Comparator.comparing(spedizione -> spedizione.getOrdineCliente().getNumeroOrdine()));
        MFXTableColumn<SpedizioneDTO> trackingNumberColumn = new MFXTableColumn<>("Tracking Number", false, Comparator.comparing(SpedizioneDTO::getTrackingNumber));
        MFXTableColumn<SpedizioneDTO> statoColumn = new MFXTableColumn<>("Stato", false, Comparator.comparing(SpedizioneDTO::getStato));
        MFXTableColumn<SpedizioneDTO> operatoreColumn = new MFXTableColumn<>("Organizzata Da", false, Comparator.comparing(spedizione -> spedizione.getOrganizzatore().getUsername()));
        MFXTableColumn<SpedizioneDTO> actionColumn = new MFXTableColumn<>("", false, null);

        numeroOrdineColumn.setRowCellFactory(cell -> new MFXTableRowCell<SpedizioneDTO, Object>(spedizione -> spedizione.getOrdineCliente().getNumeroOrdine()));
        trackingNumberColumn.setRowCellFactory(cell -> new MFXTableRowCell<SpedizioneDTO, Object>(SpedizioneDTO::getTrackingNumber));
        statoColumn.setRowCellFactory(cell -> new MFXTableRowCell<SpedizioneDTO, Object>(SpedizioneDTO::getStato));
        operatoreColumn.setRowCellFactory(cell -> new MFXTableRowCell<SpedizioneDTO, Object>(spedizione -> spedizione.getOrganizzatore().getUsername()));

        actionColumn.setRowCellFactory(spedizione -> {
            //crea cella vuota
            MFXTableRowCell<SpedizioneDTO, Void> cell = new MFXTableRowCell<>(null);

            MFXButton button = new MFXButton("Visualizza");

            button.setOnAction(event -> {
                try {
                    odlOrchestrator.visualizzaSpedizioneClicked(utente, spedizione);
                } catch (SpedizioniException e) {
                    dashboardController.showDialog("error", "Errore", e.getMessage());
                }
            });

            cell.setGraphic(button);

            return cell;
        });

        spedizioniGrid.getTableColumns().addAll(numeroOrdineColumn, trackingNumberColumn, statoColumn, operatoreColumn, actionColumn);
        spedizioniGrid.getFilters().addAll(
                new StringFilter<>("Numero Ordine", spedizione -> spedizione.getOrdineCliente().getNumeroOrdine()),
                new StringFilter<>("Tracking Number", SpedizioneDTO::getTrackingNumber),
                new StringFilter<>("Stato", SpedizioneDTO::getStato),
                new StringFilter<>("Organizzata Da", spedizione -> spedizione.getOrganizzatore().getUsername())
        );

        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();
        if (utente.getProfilo().equals("Operatore")) {
            filtroTuttiRadioBox.setVisible(true);
            filtroEmesseDaMe.setVisible(true);
        }
    }

    public void updateData() {
        try {
            odlOrchestrator.paginaSpedizioniPronta(filtroEmesseDaMe.isSelected());
        } catch (SpedizioniException e) {
            dashboardController.showDialog("error", "Errore", e.getMessage());
        }
    }

    public void setListaSpedizioni(List<SpedizioneDTO> listaSpedizioni) {
        spedizioniGrid.setItems(FXCollections.observableArrayList(listaSpedizioni));
    }
}
