package org.unina.uninadelivery.presentation.controller.customerdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.customerdomain.CustomerOrchestrator;

import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class OrdiniMainViewController implements Initializable {
    private final Stage dashboardStage;
    private CustomerOrchestrator customerOrchestrator;
    @FXML
    protected MFXTableView<OrdineClienteDTO> ordiniClienteGrid;
    @FXML
    protected MFXDatePicker dataInizioPicker;
    @FXML
    protected MFXDatePicker dataFinePicker;

    public OrdiniMainViewController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerOrchestrator = new CustomerOrchestrator(dashboardStage, null, this);

        setup();

        ordiniClienteGrid.autosizeColumnsOnInitialization();

    }

    public void updateData() {
        customerOrchestrator.paginaOrdiniMainPronta(dataInizioPicker.getValue(), dataFinePicker.getValue());
    }

    private void setup() {
        dataInizioPicker.setValue(LocalDate.of(LocalDate.now().getYear(), 1, 1));
        dataFinePicker.setValue(null);

        MFXTableColumn<OrdineClienteDTO> clienteColumn = new MFXTableColumn<>("Cliente", false, Comparator.comparing(OrdineClienteDTO::getIntestazioneCliente));
        MFXTableColumn<OrdineClienteDTO> dataOrdineColumn = new MFXTableColumn<>("Data Ordine", false, Comparator.comparing(OrdineClienteDTO::getDataOrdine));
        MFXTableColumn<OrdineClienteDTO> statoColumn = new MFXTableColumn<>("Stato", false, Comparator.comparing(OrdineClienteDTO::getStato));
        MFXTableColumn<OrdineClienteDTO> numeroOrdineColumn = new MFXTableColumn<>("Numero Ordine", false, Comparator.comparing(OrdineClienteDTO::getNumeroOrdine));
        //MFXTableColumn<OrdineClienteDTO> importoTotaleColumn = new MFXTableColumn<>("Importo Totale", false, Comparator.comparing(OrdineClienteDTO::getImportoTotale));
        MFXTableColumn<OrdineClienteDTO> actionColumn = new MFXTableColumn<>("", false, null);
        MFXTableColumn<OrdineClienteDTO> action2Column = new MFXTableColumn<>("", false, null);

        clienteColumn.setRowCellFactory(ordineCliente -> new MFXTableRowCell<>(OrdineClienteDTO::getIntestazioneCliente));
        dataOrdineColumn.setRowCellFactory(ordineCliente -> new MFXTableRowCell<>(OrdineClienteDTO::getDataOrdine));
        statoColumn.setRowCellFactory(ordineCliente -> new MFXTableRowCell<>(OrdineClienteDTO::getStato));
        numeroOrdineColumn.setRowCellFactory(ordineCliente -> new MFXTableRowCell<>(OrdineClienteDTO::getNumeroOrdine));
        //importoTotaleColumn.setRowCellFactory(ordineCliente -> new MFXTableRowCell<>(ordineClienteDTO -> ordineClienteDTO.getImportoTotale() + " €"));
        actionColumn.setRowCellFactory(ordineCliente -> {
            //crea cella vuota
            MFXTableRowCell<OrdineClienteDTO, Void> cell = new MFXTableRowCell<>(null);

            //crea un tasto
            MFXButton button = new MFXButton("Visualizza Ordine");
            button.setOnAction(event -> {
                customerOrchestrator.visualizzaOrdineClicked(ordineCliente);
            });

            //imposta il tasto come grafica della cella
            cell.setGraphic(button);

            return cell;
        });

        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        if (utente.getProfilo().equals("Operatore")) {
            action2Column.setRowCellFactory(ordineCliente -> {
                //crea cella vuota
                MFXTableRowCell<OrdineClienteDTO, Void> cell = new MFXTableRowCell<>(null);

                MFXButton button;

                if (ordineCliente.getStato().equals("Completato")) {
                    //crea un tasto
                    button = new MFXButton("Crea Spedizione");
                    button.setOnAction(event -> {
                        customerOrchestrator.creaSpedizioneClicked(ordineCliente);
                    });

                } else {
                    //crea un tasto
                    button = new MFXButton("Visualizza Spedizione");
                    button.setOnAction(event -> {
                        customerOrchestrator.visualizzaSpedizioneClicked(ordineCliente);
                    });
                }

                //imposta il tasto come grafica della cella
                cell.setGraphic(button);

                return cell;
            });

        } else if (utente.getProfilo().equals("Manager")) {
            action2Column.setRowCellFactory(ordineCliente -> {
                //crea cella vuota
                MFXTableRowCell<OrdineClienteDTO, Void> cell = new MFXTableRowCell<>(null);

                MFXButton button;

                if (!ordineCliente.getStato().equals("Completato")) {
                    //crea un tasto
                    button = new MFXButton("Visualizza Spedizione");
                    button.setOnAction(event -> {
                        customerOrchestrator.visualizzaSpedizioneClicked(ordineCliente);
                    });

                    //imposta il tasto come grafica della cella
                    cell.setGraphic(button);
                }

                return cell;
            });
        }

        ordiniClienteGrid.getTableColumns().addAll(clienteColumn, dataOrdineColumn, statoColumn, numeroOrdineColumn, /*importoTotaleColumn,*/ actionColumn, action2Column);

        ordiniClienteGrid.getFilters().addAll(
                //new StringFilter<>("Data Ordine", ordineCliente -> ordineCliente.getDataOrdine().toString()),
                new StringFilter<>("Stato", OrdineClienteDTO::getStato),
                new StringFilter<>("Numero Ordine", OrdineClienteDTO::getNumeroOrdine)
        );

        dataInizioPicker.setOnAction(event -> {
            LocalDate dataInizio = dataInizioPicker.getValue();
            LocalDate dataFine = dataFinePicker.getValue();

            if (dataFine != null && dataInizio != null && dataInizio.isAfter(dataFine)) {
                DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
                dashboardController.showDialog("error", "Errore", "Data inizio deve essere prima di data fine");
            }
            else
                customerOrchestrator.visualizzaOrdiniDataClicked(null, dataInizio, dataFine);
        });

        dataFinePicker.setOnAction(dataInizioPicker.getOnAction());

        customerOrchestrator.paginaOrdiniMainPronta(dataInizioPicker.getValue(), null);
    }

    public void setListaOrdiniCliente(List<OrdineClienteDTO> listaOrdiniCliente) {
        ordiniClienteGrid.getItems().clear();
        ordiniClienteGrid.setItems(FXCollections.observableArrayList(listaOrdiniCliente));
    }

    public void resettaFiltri() {
        dataInizioPicker.setValue(null);
        dataFinePicker.setValue(null);
    }

}
