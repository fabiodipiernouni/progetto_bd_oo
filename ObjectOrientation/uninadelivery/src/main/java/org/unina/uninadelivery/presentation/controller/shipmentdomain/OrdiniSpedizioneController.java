package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class OrdiniSpedizioneController implements Initializable {
    private final Stage dashboardStage;

    @FXML
    private MFXPaginatedTableView<OrdineDiLavoroSpedizioneDTO> ordiniDiLavoroSpedizioneGrid;

    @FXML
    private MFXRadioButton filtroTuttiRadioBox;

    @FXML
    private MFXRadioButton filtroGruppoCorriereRadioBox;

    @FXML
    private MFXRadioButton filtroPresiInCaricoRadioBox;

    @FXML
    private MFXRadioButton filtroDaPrendereInCaricoRadioBox;

    @FXML
    private MFXRadioButton filtroEmessiDaMeRadioBox;

    private OdlOrchestrator odlOrchestrator;

    private ToggleGroup toggleGroup;

    public OrdiniSpedizioneController(Stage dashboardStage) {

        this.dashboardStage = dashboardStage;
        this.odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);
        this.odlOrchestrator.setOrdiniSpedizioneController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        ordiniDiLavoroSpedizioneGrid.autosizeColumnsOnInitialization();

        When.onChanged(ordiniDiLavoroSpedizioneGrid.currentPageProperty())
                .then((oldValue, newValue) -> ordiniDiLavoroSpedizioneGrid.autosizeColumns())
                .listen();

        toggleGroup = new ToggleGroup();
        filtroTuttiRadioBox.setToggleGroup(toggleGroup);
        filtroGruppoCorriereRadioBox.setToggleGroup(toggleGroup);
        filtroPresiInCaricoRadioBox.setToggleGroup(toggleGroup);
        filtroDaPrendereInCaricoRadioBox.setToggleGroup(toggleGroup);
        filtroEmessiDaMeRadioBox.setToggleGroup(toggleGroup);


        filtroTuttiRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniSpedizioneTuttiClicked();
        });


        filtroGruppoCorriereRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniSpedizioneGruppoCorriereClicked();
        });


        filtroPresiInCaricoRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniSpedizionePresiInCaricoClicked();
        });


        filtroDaPrendereInCaricoRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniSpedizioneDaPrendereInCaricoClicked();
        });

        filtroEmessiDaMeRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniSpedizioneEmessiDaMeClicked();
        });

    }

    private void setup() {

        MFXTableColumn<OrdineDiLavoroSpedizioneDTO> numeroOrdineColumn = new MFXTableColumn<>("Ordine N.", false, Comparator.comparing(OrdineDiLavoroSpedizioneDTO::getId));
        MFXTableColumn<OrdineDiLavoroSpedizioneDTO> dataInizioLavorazioneColumn = new MFXTableColumn<>("Data Inizio", false, Comparator.comparing(ordine -> ordine.getDataInizioLavorazione() != null ? ordine.getDataInizioLavorazione() : (ordine.getDataInizioPianificazione() != null ? ordine.getDataInizioPianificazione() : LocalDate.of(1970, 1, 1))));
        MFXTableColumn<OrdineDiLavoroSpedizioneDTO> dataFineLavorazioneColumn = new MFXTableColumn<>("Data Fine", false, Comparator.comparing(ordine -> ordine.getDataFineLavorazione() != null ? ordine.getDataFineLavorazione() : LocalDate.of(1970, 1, 1)));
        MFXTableColumn<OrdineDiLavoroSpedizioneDTO> statoColumn = new MFXTableColumn<>("Stato", false, Comparator.comparing(OrdineDiLavoroSpedizioneDTO::getStato));
        MFXTableColumn<OrdineDiLavoroSpedizioneDTO> mezzoDiTrasportoColumn = new MFXTableColumn<>("Mezzo", false, Comparator.comparing(ordine -> ordine.getMezzoDiTrasporto() != null ? ordine.getMezzoDiTrasporto().getTarga() : ""));
        MFXTableColumn<OrdineDiLavoroSpedizioneDTO> actionColumn = new MFXTableColumn<>("", false, null);


        numeroOrdineColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(OrdineDiLavoroSpedizioneDTO::getId));
        dataInizioLavorazioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getDataInizioLavorazione() != null ? ordineDto.getDataInizioLavorazione().toString() : (ordineDto.getDataInizioPianificazione() != null ? ordineDto.getDataInizioPianificazione().toString() : "")));
        dataFineLavorazioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getDataFineLavorazione() != null ? ordineDto.getDataFineLavorazione().toString() : ""));
        statoColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(OrdineDiLavoroSpedizioneDTO::getStato));
        mezzoDiTrasportoColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getMezzoDiTrasporto().getTarga()));

        actionColumn.setRowCellFactory(ordine -> {
            //crea cella vuota
            MFXTableRowCell<OrdineDiLavoroSpedizioneDTO, Void> cell = new MFXTableRowCell<>(null);

            MFXButton button = new MFXButton("Visualizza");

            button.setOnAction(event -> {
                odlOrchestrator.visualizzaOrdineSpedizioneClicked(ordine);
            });

            cell.setGraphic(button);

            return cell;
        });

        ordiniDiLavoroSpedizioneGrid.getTableColumns().addAll(
                numeroOrdineColumn,
                dataInizioLavorazioneColumn,
                dataFineLavorazioneColumn,
                statoColumn,
                mezzoDiTrasportoColumn,
                actionColumn
        );

        ordiniDiLavoroSpedizioneGrid.getFilters().addAll(
                new LongFilter<>("Ordine N.", OrdineDiLavoroSpedizioneDTO::getId),
                new StringFilter<>("Stato", OrdineDiLavoroSpedizioneDTO::getStato),
                new StringFilter<>("Mezzo", ordine -> ordine.getMezzoDiTrasporto().getTarga())
        );


        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();
        if(utente.getProfilo().equals("OperatoreCorriere")) {
            MFXTableColumn<OrdineDiLavoroSpedizioneDTO> action2Column = new MFXTableColumn<>("", false, null);
            action2Column.setRowCellFactory(ordine -> {
                //crea cella vuota
                MFXTableRowCell<OrdineDiLavoroSpedizioneDTO, Void> cell = new MFXTableRowCell<>(null);

                MFXButton button;

                if(ordine.getStato().equals("DaAssegnare")) {
                    //crea un tasto
                    button = new MFXButton("Prendi in Carico");
                    button.setOnAction(event -> {
                        odlOrchestrator.prendiInCaricoOrdineSpedizioneClicked(ordine);
                    });

                    //imposta il tasto come grafica della cella
                    cell.setGraphic(button);

                }

                return cell;

            });

            ordiniDiLavoroSpedizioneGrid.getTableColumns().add(action2Column);


            filtroTuttiRadioBox.setVisible(true);
            filtroGruppoCorriereRadioBox.setVisible(true);
            filtroPresiInCaricoRadioBox.setVisible(true);
            filtroDaPrendereInCaricoRadioBox.setVisible(true);

        }
        else
            if(utente.getProfilo().equals("Operatore")) {
                filtroTuttiRadioBox.setVisible(true);
                filtroEmessiDaMeRadioBox.setVisible(true);

                //avvicina i radio box
                filtroEmessiDaMeRadioBox.setLayoutX(filtroGruppoCorriereRadioBox.getLayoutX());

            }


        odlOrchestrator.paginaOrdiniSpedizionePronta();
    }

    public void setListaOrdini(List<OrdineDiLavoroSpedizioneDTO> listaOrdini) {
        ordiniDiLavoroSpedizioneGrid.getItems().clear();
        ordiniDiLavoroSpedizioneGrid.setItems(FXCollections.observableArrayList(listaOrdini));
    }

    public void resettaFiltri() {
        toggleGroup.selectToggle(filtroTuttiRadioBox);
    }
}
