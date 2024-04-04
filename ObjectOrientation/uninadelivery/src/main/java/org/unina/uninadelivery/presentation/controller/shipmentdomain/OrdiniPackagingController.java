package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class OrdiniPackagingController implements Initializable {
    private final Stage dashboardStage;
    private final DashboardController dashboardController;

    @FXML
    private MFXTableView<OrdineDiLavoroPackagingDTO> ordiniDiLavoroPackagingGrid;

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

    public OrdiniPackagingController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
        this.dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);
        this.odlOrchestrator.setOrdiniPackagingController(this);
        setup();

        ordiniDiLavoroPackagingGrid.autosizeColumnsOnInitialization();





        toggleGroup = new ToggleGroup();
        filtroTuttiRadioBox.setToggleGroup(toggleGroup);
        filtroGruppoCorriereRadioBox.setToggleGroup(toggleGroup);
        filtroPresiInCaricoRadioBox.setToggleGroup(toggleGroup);
        filtroDaPrendereInCaricoRadioBox.setToggleGroup(toggleGroup);
        filtroEmessiDaMeRadioBox.setToggleGroup(toggleGroup);

        filtroTuttiRadioBox.setOnAction(event -> {
            try {
                odlOrchestrator.filtroOrdiniPackagingTuttiClicked();
            } catch (SpedizioniException e) {
                dashboardController.showDialog("error", "Errore nel caricamento", e.getMessage());
            }
        });

        filtroGruppoCorriereRadioBox.setOnAction(event -> {
            try {
                odlOrchestrator.filtroOrdiniPackagingGruppoCorriereClicked();
            } catch (SpedizioniException e) {
                dashboardController.showDialog("error", "Errore nel caricamento", e.getMessage());
            }
        });

        filtroPresiInCaricoRadioBox.setOnAction(event -> {
            try {
                odlOrchestrator.filtroOrdiniPackagingPresiInCaricoClicked();
            } catch (SpedizioniException e) {
                dashboardController.showDialog("error", "Errore nel caricamento", e.getMessage());
            }
        });

        filtroDaPrendereInCaricoRadioBox.setOnAction(event -> {
            try {
                odlOrchestrator.filtroOrdiniPackagingDaPrendereInCaricoClicked();
            } catch (SpedizioniException e) {
                dashboardController.showDialog("error", "Errore nel caricamento", e.getMessage());
            }
        });

        filtroEmessiDaMeRadioBox.setOnAction(event -> {
            try {
                odlOrchestrator.filtroOrdiniPackagingEmessiDaMeClicked();
            } catch (SpedizioniException e) {
                dashboardController.showDialog("error", "Errore nel caricamento", e.getMessage());
            }
        });
    }

    private void setup() {
        MFXTableColumn<OrdineDiLavoroPackagingDTO> numeroOrdineColumn = new MFXTableColumn<>("Ordine N.", false, Comparator.comparing(OrdineDiLavoroPackagingDTO::getId));
        MFXTableColumn<OrdineDiLavoroPackagingDTO> dataInizioLavorazioneColumn = new MFXTableColumn<>("Data Inizio", false, Comparator.comparing(ordine -> ordine.getDataInizioLavorazione() != null ? ordine.getDataInizioLavorazione() : (ordine.getDataInizioPianificazione() != null ? ordine.getDataInizioPianificazione() : LocalDate.of(1970, 1, 1))));
        MFXTableColumn<OrdineDiLavoroPackagingDTO> dataFineLavorazioneColumn = new MFXTableColumn<>("Data Fine", false, Comparator.comparing(ordine -> ordine.getDataFineLavorazione() != null ? ordine.getDataFineLavorazione() : LocalDate.of(1970, 1, 1)));
        MFXTableColumn<OrdineDiLavoroPackagingDTO> statoColumn = new MFXTableColumn<>("Stato", false, Comparator.comparing(OrdineDiLavoroPackagingDTO::getStato));
        MFXTableColumn<OrdineDiLavoroPackagingDTO> magazzinoColumn = new MFXTableColumn<>("Magazzino", false, Comparator.comparing(ordine -> ordine.getMagazzino().getNome().replace("Magazzino ", "")));
        MFXTableColumn<OrdineDiLavoroPackagingDTO> actionColumn = new MFXTableColumn<>("", false, null);

        numeroOrdineColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(OrdineDiLavoroPackagingDTO::getId));
        dataInizioLavorazioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getDataInizioLavorazione() != null ? ordineDto.getDataInizioLavorazione().toString() : (ordineDto.getDataInizioPianificazione() != null ? ordineDto.getDataInizioPianificazione().toString() : "")));
        dataFineLavorazioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getDataFineLavorazione() != null ? ordineDto.getDataFineLavorazione().toString() : ""));
        statoColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(OrdineDiLavoroPackagingDTO::getStato));
        magazzinoColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getMagazzino().getNome().replace("Magazzino ", "")));

        actionColumn.setRowCellFactory(ordine -> {
            //crea cella vuota
            MFXTableRowCell<OrdineDiLavoroPackagingDTO, Void> cell = new MFXTableRowCell<>(null);

            MFXButton button = new MFXButton("Visualizza");

            button.setOnAction(event -> {
                try {
                    odlOrchestrator.visualizzaOrdinePackagingClicked(ordine);
                } catch (SpedizioniException e) {
                    Platform.runLater(() -> dashboardController.showDialog("error", "Errore", e.getMessage()));
                }
            });

            cell.setGraphic(button);

            return cell;
        });

        ordiniDiLavoroPackagingGrid.getTableColumns().addAll(
                numeroOrdineColumn,
                dataInizioLavorazioneColumn,
                dataFineLavorazioneColumn,
                statoColumn,
                magazzinoColumn,
                actionColumn
        );

        ordiniDiLavoroPackagingGrid.getFilters().addAll(
                new LongFilter<>("Ordine N.", OrdineDiLavoroPackagingDTO::getId),
                new StringFilter<>("Stato", OrdineDiLavoroPackagingDTO::getStato),
                new StringFilter<>("Magazzino", ordine -> ordine.getMagazzino().getNome().replace("Magazzino ", ""))
        );


        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();
        if (utente.getProfilo().equals("OperatoreCorriere")) {
            MFXTableColumn<OrdineDiLavoroPackagingDTO> action2Column = new MFXTableColumn<>("", false, null);
            action2Column.setRowCellFactory(ordine -> {
                //crea cella vuota
                MFXTableRowCell<OrdineDiLavoroPackagingDTO, Void> cell = new MFXTableRowCell<>(null);

                MFXButton button;

                if (ordine.getStato().equals("DaAssegnare")) {
                    //crea un tasto
                    button = new MFXButton("Prendi in Carico");
                    button.setOnAction(event -> {
                        try {
                            odlOrchestrator.prendiInCaricoOrdinePackagingClicked(ordine);
                        } catch (SpedizioniException e) {
                            dashboardController.showDialog("error", "Errore nel prendere in carico", e.getMessage());
                        }
                    });

                    //imposta il tasto come grafica della cella
                    cell.setGraphic(button);
                }

                return cell;
            });

            ordiniDiLavoroPackagingGrid.getTableColumns().add(action2Column);

            filtroTuttiRadioBox.setVisible(true);
            filtroGruppoCorriereRadioBox.setVisible(true);
            filtroPresiInCaricoRadioBox.setVisible(true);
            filtroDaPrendereInCaricoRadioBox.setVisible(true);
        } else if (utente.getProfilo().equals("Operatore")) {
            filtroTuttiRadioBox.setVisible(true);
            filtroEmessiDaMeRadioBox.setVisible(true);

            //avvicina i radio box
            filtroEmessiDaMeRadioBox.setLayoutX(filtroGruppoCorriereRadioBox.getLayoutX());
        }
    }

    public void setListaOrdini(List<OrdineDiLavoroPackagingDTO> listaOrdini) {
        this.ordiniDiLavoroPackagingGrid.getItems().clear();
        this.ordiniDiLavoroPackagingGrid.setItems(FXCollections.observableArrayList(listaOrdini));

    }

    public void resettaFiltri() {
        toggleGroup.selectToggle(filtroTuttiRadioBox);
    }

    public void updateData() {
        try {
            odlOrchestrator.paginaOrdiniPackagingPronta();
        } catch (SpedizioniException e) {
            dashboardController.showDialog("error", "Errore", e.getMessage());
        }
    }
}
