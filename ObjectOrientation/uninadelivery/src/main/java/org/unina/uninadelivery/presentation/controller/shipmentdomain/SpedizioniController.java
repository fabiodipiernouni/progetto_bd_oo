package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class SpedizioniController implements Initializable {

    private final Stage dashboardStage;
    private final OdlOrchestrator odlOrchestrator;
    @FXML
    protected MFXPaginatedTableView spedizioniGrid;

    @FXML
    protected MFXRadioButton filtroTuttiRadioBox;
    @FXML
    protected MFXRadioButton filtroEmesseDaMe;


    public SpedizioniController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
        this.odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);
        this.odlOrchestrator.setSpedizioniController(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spedizioniGrid.autosizeColumnsOnInitialization();

        When.onChanged(spedizioniGrid.currentPageProperty())
                .then((oldValue, newValue) -> spedizioniGrid.autosizeColumns())
                .listen();
        ToggleGroup group = new ToggleGroup();

        filtroEmesseDaMe.setToggleGroup(group);
        filtroTuttiRadioBox.setToggleGroup(group);
        filtroTuttiRadioBox.setSelected(true);

        filtroTuttiRadioBox.setOnAction(event -> {
            try {
                odlOrchestrator.filtroTutteSpedizioniClicked();
            } catch (SpedizioniException e) {
                //todo: gestire errore
            }
        });

        filtroEmesseDaMe.setOnAction(event -> {
            try {
                odlOrchestrator.filtroSpedizioniEmesseDaMeClicked();
            } catch (SpedizioniException e) {
                //todo: gestire errore
            }
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
                    odlOrchestrator.visualizzaSpedizioneClicked(spedizione);
                } catch (SpedizioniException e) {
                    //todo: gestire errore
                }
            });

            cell.setGraphic(button);

            return cell;
        });

        spedizioniGrid.getTableColumns().addAll(numeroOrdineColumn, trackingNumberColumn, statoColumn, operatoreColumn, actionColumn);
        spedizioniGrid.getFilters().addAll(
                new StringFilter<SpedizioneDTO>("Numero Ordine", spedizione -> spedizione.getOrdineCliente().getNumeroOrdine()),
                new StringFilter<SpedizioneDTO>("Tracking Number", SpedizioneDTO::getTrackingNumber),
                new StringFilter<SpedizioneDTO>("Stato", SpedizioneDTO::getStato),
                new StringFilter<SpedizioneDTO>("Organizzata Da", spedizione -> spedizione.getOrganizzatore().getUsername())
        );

        try {
            odlOrchestrator.paginaSpedizioniPronta();
        } catch (SpedizioniException e) {
            //todo: gestire errore
        }
    }

    public void setListaSpedizioni(List<SpedizioneDTO> listaSpedizioni) {
        spedizioniGrid.getItems().clear();
        spedizioniGrid.setItems(FXCollections.observableArrayList(listaSpedizioni));
    }
}
