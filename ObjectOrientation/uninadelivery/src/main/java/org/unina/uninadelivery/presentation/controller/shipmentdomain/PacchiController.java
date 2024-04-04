package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.FloatFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.PaccoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.orchestrator.orgdomain.MagazzinoOrchestrator;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class PacchiController implements Initializable {
    private final Stage dashboardStage;
    private final SpedizioneDTO spedizione;
    private final OdlOrchestrator odlOrchestrator;

    @FXML
    private MFXTableView<PaccoDTO> pacchiGrid;

    @FXML
    private Label ordineDiLavoroPackagingLabel;

    @FXML
    private Label indirizzoDiDestinazioneLabel;

    @FXML
    private Label spedizioneLabel;

    @FXML
    private MFXButton magazzinoButton;

    @FXML
    private OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging;

    public PacchiController(Stage dashboardStage, OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging) {
        this.dashboardStage = dashboardStage;
        this.ordineDiLavoroPackaging = ordineDiLavoroPackaging;
        odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);
        this.spedizione = null;
    }

    public PacchiController(Stage dashboardStage, SpedizioneDTO spedizione) {
        this.dashboardStage = dashboardStage;
        this.spedizione = spedizione;
        odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ordineDiLavoroPackaging != null)
            initByOrdineDiLavoroPackaging(location, resources);
        else
            InitBySpedizione(location, resources);
    }

    private void InitBySpedizione(URL location, ResourceBundle resources) {
        IndirizzoDTO indirizzoSpedizione = spedizione.getOrdineCliente().getIndirizzoSpedizione() != null? spedizione.getOrdineCliente().getIndirizzoSpedizione():spedizione.getOrdineCliente().getIndirizzoFatturazione();
        String indirizzo = indirizzoSpedizione.getIndirizzo_1();
        if(indirizzoSpedizione.getIndirizzo_2() != null)
            indirizzo += ", " + indirizzoSpedizione.getIndirizzo_2();
        indirizzo += indirizzoSpedizione.getCAP() + " " + indirizzoSpedizione.getComune();

        indirizzoDiDestinazioneLabel.setText(indirizzo);
        ordineDiLavoroPackagingLabel.setText("-");
        spedizioneLabel.setText(spedizione.getTrackingNumber());
        magazzinoButton.setVisible(false);

        setup();

        pacchiGrid.autosizeColumnsOnInitialization();
    }

    public void initByOrdineDiLavoroPackaging(URL location, ResourceBundle resources) {
        String indirizzo = ordineDiLavoroPackaging.getIndirizzoSpedizione().getIndirizzo_1();
        if(ordineDiLavoroPackaging.getIndirizzoSpedizione().getIndirizzo_2() != null)
            indirizzo += ", " + ordineDiLavoroPackaging.getIndirizzoSpedizione().getIndirizzo_2();
        indirizzo += ordineDiLavoroPackaging.getIndirizzoSpedizione().getCAP() + " " + ordineDiLavoroPackaging.getIndirizzoSpedizione().getComune();

        indirizzoDiDestinazioneLabel.setText(indirizzo);

        ordineDiLavoroPackagingLabel.setText(String.valueOf(ordineDiLavoroPackaging.getId()));
        spedizioneLabel.setText(ordineDiLavoroPackaging.getSpedizione().getTrackingNumber());

        magazzinoButton.setText(ordineDiLavoroPackaging.getMagazzino().getNome().replace("Magazzino ", ""));
        magazzinoButton.setOnMouseClicked(event -> {
            new MagazzinoOrchestrator(dashboardStage).visualizzaMagazzinoClicked(ordineDiLavoroPackaging.getMagazzino());
        });

        setup();

        pacchiGrid.autosizeColumnsOnInitialization();



    }

    private void setup() {
        MFXTableColumn<PaccoDTO> codicePaccoColumn = new MFXTableColumn<>("Codice Pacco", false, Comparator.comparing(PaccoDTO::getCodicePacco));
        MFXTableColumn<PaccoDTO> pesoColumn = new MFXTableColumn<>("Peso", false, Comparator.comparing(PaccoDTO::getPeso));

        codicePaccoColumn.setRowCellFactory(paccoDTO -> new MFXTableRowCell<>(PaccoDTO::getCodicePacco));
        pesoColumn.setRowCellFactory(paccoDTO -> new MFXTableRowCell<>(PaccoDTO::getPeso));

        pacchiGrid.getTableColumns().addAll(codicePaccoColumn, pesoColumn);

        pacchiGrid.getFilters().addAll(
                new StringFilter<>("Codice Pacco", PaccoDTO::getCodicePacco),
                new FloatFilter<>("Peso", PaccoDTO::getPeso)
        );

        if(ordineDiLavoroPackaging != null)
            pacchiGrid.getItems().addAll(ordineDiLavoroPackaging.getPacchi());
        else {
            try {
                odlOrchestrator.pacchiPaginaPronta(this, spedizione);
            } catch (SpedizioniException e) {
                odlOrchestrator.manageError("Errore", "Errore nel caricamento dei pacchi");
            }
        }
    }


    public void setGridPacchi(List<PaccoDTO> pacchi) {
        pacchiGrid.getItems().clear();
        pacchiGrid.setItems(FXCollections.observableArrayList(pacchi));
    }
}
