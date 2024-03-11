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
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class OrdiniPackagingController implements Initializable {
    private final Stage dashboardStage;

    @FXML
    private MFXPaginatedTableView<OrdineDiLavoroPackagingDTO> ordiniDiLavoroPackagingGrid;

    @FXML
    private MFXRadioButton filtroTuttiRadioBox;

    @FXML
    private MFXRadioButton filtroGruppoCorriereRadioBox;

    @FXML
    private MFXRadioButton filtroPresiInCaricoRadioBox;

    @FXML
    private MFXRadioButton filtroDaPrendereInCaricoRadioBox;

    @FXML
    private MFXRadioButton filtroImmessiDaMeRadioBox;

    private OdlOrchestrator odlOrchestrator;

    private ToggleGroup toggleGroup;

    public OrdiniPackagingController(Stage dashboardStage) {

        this.dashboardStage = dashboardStage;
        this.odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);
        this.odlOrchestrator.setOrdiniPackagingController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        ordiniDiLavoroPackagingGrid.autosizeColumnsOnInitialization();

        When.onChanged(ordiniDiLavoroPackagingGrid.currentPageProperty())
                .then((oldValue, newValue) -> ordiniDiLavoroPackagingGrid.autosizeColumns())
                .listen();


        toggleGroup = new ToggleGroup();
        filtroTuttiRadioBox.setToggleGroup(toggleGroup);
        filtroGruppoCorriereRadioBox.setToggleGroup(toggleGroup);
        filtroPresiInCaricoRadioBox.setToggleGroup(toggleGroup);
        filtroDaPrendereInCaricoRadioBox.setToggleGroup(toggleGroup);
        filtroImmessiDaMeRadioBox.setToggleGroup(toggleGroup);


        filtroTuttiRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniPackagingTuttiClicked();
        });


        filtroGruppoCorriereRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniPackagingGruppoCorriereClicked();
        });


        filtroPresiInCaricoRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniPackagingPresiInCaricoClicked();
        });


        filtroDaPrendereInCaricoRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniPackagingDaPrendereInCaricoClicked();
        });

        filtroImmessiDaMeRadioBox.setOnAction(event -> {
            odlOrchestrator.filtroOrdiniPackagingImmessiDaMeClicked();
        });


    }

    private void setup() {

        MFXTableColumn<OrdineDiLavoroPackagingDTO> numeroOrdineColumn = new MFXTableColumn<>("Ordine N.", false, Comparator.comparing(OrdineDiLavoroPackagingDTO::getId));

        //MFXTableColumn<OrdineDiLavoroPackagingDTO> dataCreazioneColumn = new MFXTableColumn<>("Data Creazione", false, Comparator.comparing(OrdineDiLavoroPackagingDTO::getDataCreazione));

        //MFXTableColumn<OrdineDiLavoroPackagingDTO> dataInizioPianificazioneColumn = new MFXTableColumn<>("Data Inizio Pianificazione", false, Comparator.comparing(ordine -> ordine.getDataInizioPianificazione() != null ? ordine.getDataInizioPianificazione() : LocalDate.of(1970, 1, 1)));

        MFXTableColumn<OrdineDiLavoroPackagingDTO> dataInizioLavorazioneColumn = new MFXTableColumn<>("Data Inizio", false, Comparator.comparing(ordine -> ordine.getDataInizioLavorazione() != null ? ordine.getDataInizioLavorazione() : (ordine.getDataInizioPianificazione() != null ? ordine.getDataInizioPianificazione() : LocalDate.of(1970, 1, 1))));

        MFXTableColumn<OrdineDiLavoroPackagingDTO> dataFineLavorazioneColumn = new MFXTableColumn<>("Data Fine", false, Comparator.comparing(ordine -> ordine.getDataFineLavorazione() != null ? ordine.getDataFineLavorazione() : LocalDate.of(1970, 1, 1)));

        //MFXTableColumn<OrdineDiLavoroPackagingDTO> gruppoCorriereColumn = new MFXTableColumn<>("Gruppo Corriere", false, Comparator.comparing(ordine -> ordine.getGruppoCorriere() != null ? ordine.getGruppoCorriere().getCodiceCorriere() : ""));
        //MFXTableColumn<OrdineDiLavoroPackagingDTO> operatoreCorriereColumn = new MFXTableColumn<>("Operatore Corriere", false, Comparator.comparing(ordine -> ordine.getOperatoreCorriere() != null ? ordine.getOperatoreCorriere().getUsername() : ""));
        //MFXTableColumn<OrdineDiLavoroPackagingDTO> spedizioneColumn = new MFXTableColumn<>("Spedizione", false, Comparator.comparing(ordine -> ordine.getSpedizione().getTrackingNumber()));

        MFXTableColumn<OrdineDiLavoroPackagingDTO> statoColumn = new MFXTableColumn<>("Stato", false, Comparator.comparing(OrdineDiLavoroPackagingDTO::getStato));

        //MFXTableColumn<OrdineDiLavoroPackagingDTO> noteAggiuntiveOperatoreColumn = new MFXTableColumn<>("Note Aggiuntive Operatore", false, Comparator.comparing(ordine -> ordine.getNoteAggiuntiveOperatore() != null ? ordine.getNoteAggiuntiveOperatore() : ""));
        //MFXTableColumn<OrdineDiLavoroPackagingDTO> noteAggiuntiveClienteColumn = new MFXTableColumn<>("Note Aggiuntive Cliente", false, Comparator.comparing(ordine -> ordine.getNoteAggiuntiveCliente() != null ? ordine.getNoteAggiuntiveCliente() : ""));

        MFXTableColumn<OrdineDiLavoroPackagingDTO> magazzinoColumn = new MFXTableColumn<>("Magazzino", false, Comparator.comparing(ordine -> ordine.getMagazzino().getNome()));

        //MFXTableColumn<OrdineDiLavoroPackagingDTO> indirizzoSpedizioneColumn = new MFXTableColumn<>("Indirizzo Spedizione", false, Comparator.comparing(ordine -> ordine.getIndirizzoSpedizione().getIndirizzo_1() + " " + ordine.getIndirizzoSpedizione().getIndirizzo_2() + " " + ordine.getIndirizzoSpedizione().getCAP() + " " + ordine.getIndirizzoSpedizione().getComune() + " " + ordine.getIndirizzoSpedizione().getCf_pIVA()));

        MFXTableColumn<OrdineDiLavoroPackagingDTO> actionColumn = new MFXTableColumn<>("", false, null);


        numeroOrdineColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(OrdineDiLavoroPackagingDTO::getId));
        //dataCreazioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(OrdineDiLavoroPackagingDTO::getDataCreazione));
        //dataInizioPianificazioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getDataInizioPianificazione() != null ? ordineDto.getDataInizioPianificazione().toString() : ""));

        dataInizioLavorazioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getDataInizioLavorazione() != null ? ordineDto.getDataInizioLavorazione().toString() : (ordineDto.getDataInizioPianificazione() != null ? ordineDto.getDataInizioPianificazione().toString() : "")));

        dataFineLavorazioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getDataFineLavorazione() != null ? ordineDto.getDataFineLavorazione().toString() : ""));

        //gruppoCorriereColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getGruppoCorriere() != null ? ordineDto.getGruppoCorriere().getCodiceCorriere() : ""));
        //operatoreCorriereColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getOperatoreCorriere() != null ? ordineDto.getOperatoreCorriere().getUsername() : ""));
        //spedizioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getSpedizione().getTrackingNumber()));

        statoColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(OrdineDiLavoroPackagingDTO::getStato));

        //noteAggiuntiveOperatoreColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getNoteAggiuntiveOperatore() != null ? ordineDto.getNoteAggiuntiveOperatore() : ""));
        //noteAggiuntiveClienteColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getNoteAggiuntiveCliente() != null ? ordineDto.getNoteAggiuntiveCliente() : ""));

        magazzinoColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getMagazzino().getNome()));

        //indirizzoSpedizioneColumn.setRowCellFactory(ordine -> new MFXTableRowCell<>(ordineDto -> ordineDto.getIndirizzoSpedizione().getIndirizzo_1() + " " + ordineDto.getIndirizzoSpedizione().getIndirizzo_2() + " " + ordineDto.getIndirizzoSpedizione().getCAP() + " " + ordineDto.getIndirizzoSpedizione().getComune() + " " + ordineDto.getIndirizzoSpedizione().getCf_pIVA()));

        actionColumn.setRowCellFactory(ordine -> {
            //crea cella vuota
            MFXTableRowCell<OrdineDiLavoroPackagingDTO, Void> cell = new MFXTableRowCell<>(null);

            MFXButton button = new MFXButton("Visualizza");

            button.setOnAction(event -> {
                odlOrchestrator.visualizzaOrdinePackagingClicked(ordine);
            });

            cell.setGraphic(button);

            return cell;
        });

        ordiniDiLavoroPackagingGrid.getTableColumns().addAll(
                numeroOrdineColumn,
                //dataCreazioneColumn,
                //dataInizioPianificazioneColumn,
                dataInizioLavorazioneColumn,
                dataFineLavorazioneColumn,
                //gruppoCorriereColumn,
                //operatoreCorriereColumn,
                //spedizioneColumn,
                statoColumn,
                //noteAggiuntiveOperatoreColumn,
                //noteAggiuntiveClienteColumn,
                magazzinoColumn,
                //indirizzoSpedizioneColumn,
                actionColumn
        );

        ordiniDiLavoroPackagingGrid.getFilters().addAll(
                new LongFilter<>("Ordine N.", OrdineDiLavoroPackagingDTO::getId),
                //new StringFilter<>("Gruppo Corriere", ordine -> ordine.getGruppoCorriere().getCodiceCorriere()),
                //new StringFilter<>("Operatore Corriere", ordine -> ordine.getOperatoreCorriere().getUsername()),
                //new StringFilter<>("Spedizione", ordine -> ordine.getSpedizione().getTrackingNumber()),
                new StringFilter<>("Stato", OrdineDiLavoroPackagingDTO::getStato),
                //new StringFilter<>("Note Aggiuntive Operatore", ordine -> ordine.getNoteAggiuntiveOperatore() != null ? ordine.getNoteAggiuntiveOperatore() : ""),
                //new StringFilter<>("Note Aggiuntive Cliente", ordine -> ordine.getNoteAggiuntiveCliente() != null ? ordine.getNoteAggiuntiveCliente() : ""),
                new StringFilter<>("Magazzino", ordine -> ordine.getMagazzino().getNome())
                //new StringFilter<>("Indirizzo Spedizione", ordine -> ordine.getIndirizzoSpedizione().getIndirizzo_1() + " " + ordine.getIndirizzoSpedizione().getIndirizzo_2() + " " + ordine.getIndirizzoSpedizione().getCAP() + " " + ordine.getIndirizzoSpedizione().getComune() + " " + ordine.getIndirizzoSpedizione().getCf_pIVA())
        );


        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();
        if(utente.getProfilo().equals("OperatoreCorriere")) {
            MFXTableColumn<OrdineDiLavoroPackagingDTO> action2Column = new MFXTableColumn<>("", false, null);
            action2Column.setRowCellFactory(ordine -> {
                //crea cella vuota
                MFXTableRowCell<OrdineDiLavoroPackagingDTO, Void> cell = new MFXTableRowCell<>(null);

                MFXButton button;

                if(ordine.getStato().equals("DaAssegnare")) {
                    //crea un tasto
                    button = new MFXButton("Prendi in Carico");
                    button.setOnAction(event -> {
                        odlOrchestrator.prendiInCaricoOrdinePackagingClicked(ordine);
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

        }
        else
            if(utente.getProfilo().equals("Operatore")) {
                filtroTuttiRadioBox.setVisible(true);
                filtroImmessiDaMeRadioBox.setVisible(true);

                //avvicina i radio box
                filtroImmessiDaMeRadioBox.setLayoutX(filtroGruppoCorriereRadioBox.getLayoutX());
            }

        odlOrchestrator.paginaOrdiniPackagingPronta();
    }

    public void setListaOrdini(List<OrdineDiLavoroPackagingDTO> listaOrdini) {
        ordiniDiLavoroPackagingGrid.getItems().clear();
        ordiniDiLavoroPackagingGrid.setItems(FXCollections.observableArrayList(listaOrdini));
    }

    /*
    public List<OrdineDiLavoroPackagingDTO> getListaOrdini()  {
        return new LinkedList<>(ordiniDiLavoroPackagingGrid.getItems());
    }
    */

    public void resettaFiltri() {
        toggleGroup.selectToggle(filtroTuttiRadioBox);
    }

}
