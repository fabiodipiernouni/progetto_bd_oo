package org.unina.uninadelivery.presentation.controller.customerdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.customerdomain.CustomerOrchestrator;

import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class OrdiniController implements Initializable {

    private final Stage dashboardStage;

    private CustomerOrchestrator customerOrchestrator;

    private ClienteDTO clienteDTO;

    @FXML
    private Label clienteRagioneSocialeLabel;

    @FXML
    private Label clientePartitaIVACodiceFiscaleLabel;

    @FXML
    private Label clienteEmailLabel;

    @FXML
    private MFXDatePicker dataInizioPicker;

    @FXML
    private MFXDatePicker dataFinePicker;

    @FXML
    private MFXPaginatedTableView<OrdineClienteDTO> ordiniClienteGrid;

    public OrdiniController(Stage dashboardStage, CustomerOrchestrator customerOrchestrator, ClienteDTO clienteDTO) {
        this.dashboardStage = dashboardStage;
        this.customerOrchestrator = customerOrchestrator;
        this.clienteDTO = clienteDTO;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(clienteDTO.getRagioneSociale() != null) {
            clienteRagioneSocialeLabel.setText(clienteDTO.getRagioneSociale());
            clientePartitaIVACodiceFiscaleLabel.setText(clienteDTO.getPartitaIVA());
        }
        else {
            clienteRagioneSocialeLabel.setText(clienteDTO.getNome() + " " + clienteDTO.getCognome());
            clientePartitaIVACodiceFiscaleLabel.setText(clienteDTO.getCodiceFiscale());
        }

        clienteEmailLabel.setText(clienteDTO.getEmail());


        setup();

        ordiniClienteGrid.autosizeColumnsOnInitialization();

        When.onChanged(ordiniClienteGrid.currentPageProperty())
                .then((oldValue, newValue) -> ordiniClienteGrid.autosizeColumns())
                .listen();


    }

    private void setup() {

        MFXTableColumn<OrdineClienteDTO> dataOrdineColumn = new MFXTableColumn<>("Data Ordine", false, Comparator.comparing(OrdineClienteDTO::getDataOrdine));
        MFXTableColumn<OrdineClienteDTO> statoColumn = new MFXTableColumn<>("Stato", false, Comparator.comparing(OrdineClienteDTO::getStato));
        MFXTableColumn<OrdineClienteDTO> numeroOrdineColumn = new MFXTableColumn<>("Numero Ordine", false, Comparator.comparing(OrdineClienteDTO::getNumeroOrdine));
        MFXTableColumn<OrdineClienteDTO> importoTotaleColumn = new MFXTableColumn<>("Importo Totale", false, Comparator.comparing(OrdineClienteDTO::getImportoTotale));
        MFXTableColumn<OrdineClienteDTO> actionColumn = new MFXTableColumn<>("", false, null);
        MFXTableColumn<OrdineClienteDTO> action2Column = new MFXTableColumn<>("", false, null);

        dataOrdineColumn.setRowCellFactory(ordineCliente -> new MFXTableRowCell<>(OrdineClienteDTO::getDataOrdine));
        statoColumn.setRowCellFactory(ordineCliente -> new MFXTableRowCell<>(OrdineClienteDTO::getStato));
        numeroOrdineColumn.setRowCellFactory(ordineCliente -> new MFXTableRowCell<>(OrdineClienteDTO::getNumeroOrdine));
        importoTotaleColumn.setRowCellFactory(ordineCliente -> new MFXTableRowCell<>(ordineClienteDTO -> ordineClienteDTO.getImportoTotale() + " €"));
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

        if(utente.getProfilo().equals("Operatore")) {
            action2Column.setRowCellFactory(ordineCliente -> {
                //crea cella vuota
                MFXTableRowCell<OrdineClienteDTO, Void> cell = new MFXTableRowCell<>(null);

                MFXButton button;

                if(ordineCliente.getStato().equals("Completato")) {
                    //crea un tasto
                    button = new MFXButton("Crea Spedizione");
                    button.setOnAction(event -> {
                        customerOrchestrator.creaSpedizioneClicked(ordineCliente);
                    });

                }
                else {
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

        }
        else
            if(utente.getProfilo().equals("Manager")) {
                action2Column.setRowCellFactory(ordineCliente -> {
                    //crea cella vuota
                    MFXTableRowCell<OrdineClienteDTO, Void> cell = new MFXTableRowCell<>(null);

                    MFXButton button;

                    if(!ordineCliente.getStato().equals("Completato")) {
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





        ordiniClienteGrid.getTableColumns().addAll(dataOrdineColumn, statoColumn, numeroOrdineColumn, importoTotaleColumn, actionColumn, action2Column);

        ordiniClienteGrid.getFilters().addAll(
                //new StringFilter<>("Data Ordine", ordineCliente -> ordineCliente.getDataOrdine().toString()),
                new StringFilter<>("Stato", OrdineClienteDTO::getStato),
                new StringFilter<>("Numero Ordine", OrdineClienteDTO::getNumeroOrdine)
        );

        dataInizioPicker.setOnAction(event -> {
            LocalDate dataInizio = dataInizioPicker.getValue();
            LocalDate dataFine = dataFinePicker.getValue();

            if(dataFine != null && dataInizio != null && dataInizio.isAfter(dataFine))
                //TODO: convertire in un dialog
                System.out.println("Data inizio deve essere prima di data fine");

            customerOrchestrator.visualizzaOrdiniDataClicked(clienteDTO, dataInizio, dataFine);
        });

        dataFinePicker.setOnAction(dataInizioPicker.getOnAction());

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
