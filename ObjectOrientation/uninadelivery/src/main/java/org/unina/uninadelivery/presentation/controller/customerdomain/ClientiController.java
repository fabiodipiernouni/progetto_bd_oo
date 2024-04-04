package org.unina.uninadelivery.presentation.controller.customerdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.presentation.orchestrator.customerdomain.CustomerOrchestrator;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ClientiController implements Initializable {
    private final Stage dashboardStage;

    @FXML
    private MFXTableView<ClienteDTO> clientiGrid;

    private CustomerOrchestrator customerOrchestrator;

    public ClientiController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
        this.customerOrchestrator = new CustomerOrchestrator(dashboardStage, this, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setup();

        clientiGrid.autosizeColumnsOnInitialization();
    }

    private void setup() {

        MFXTableColumn<ClienteDTO> nameColumn = new MFXTableColumn<>("Nome", false, Comparator.comparing(cliente -> cliente.getCognome() + " " + cliente.getNome()));
        MFXTableColumn<ClienteDTO> ragioneSocialeColumn = new MFXTableColumn<>("Ragione Sociale", false, Comparator.comparing(cliente -> cliente.getRagioneSociale() != null ? cliente.getRagioneSociale() : "")); //il comparatore non deve ritrovarsi a confrontare dei null
        MFXTableColumn<ClienteDTO> partitaIVAColumn = new MFXTableColumn<>("Partita IVA", false, Comparator.comparing(cliente -> cliente.getPartitaIVA() != null ? cliente.getPartitaIVA() : ""));
        MFXTableColumn<ClienteDTO> codiceFiscaleColumn = new MFXTableColumn<>("Codice Fiscale", false, Comparator.comparing(cliente -> cliente.getCodiceFiscale() != null ? cliente.getCodiceFiscale() : ""));
        MFXTableColumn<ClienteDTO> actionColumn = new MFXTableColumn<>("", false, null); //non voglio che sia sortable

        nameColumn.setRowCellFactory(cliente -> new MFXTableRowCell<>(clienteDto -> clienteDto.getCognome() + " " + clienteDto.getNome()));
        ragioneSocialeColumn.setRowCellFactory(cliente -> new MFXTableRowCell<>(clienteDto -> clienteDto.getRagioneSociale() != null ? clienteDto.getRagioneSociale() : ""));
        partitaIVAColumn.setRowCellFactory(cliente -> new MFXTableRowCell<>(clienteDto -> clienteDto.getPartitaIVA() != null ? clienteDto.getPartitaIVA() : ""));
        codiceFiscaleColumn.setRowCellFactory(cliente -> new MFXTableRowCell<>(clienteDto -> clienteDto.getCodiceFiscale() != null ? clienteDto.getCodiceFiscale() : ""));
        actionColumn.setRowCellFactory(cliente -> {
            //crea cella vuota
            MFXTableRowCell<ClienteDTO, Void> cell = new MFXTableRowCell<>(null);

            //crea un tasto
            MFXButton button = new MFXButton("Visualizza Ordini");
            button.setOnAction(event -> {
                customerOrchestrator.visualizzaOrdiniClicked(cliente);
            });

            //imposta il tasto come grafica della cella
            cell.setGraphic(button);

            return cell;
        });


        clientiGrid.getTableColumns().addAll(nameColumn, ragioneSocialeColumn, partitaIVAColumn, codiceFiscaleColumn, actionColumn);

        clientiGrid.getFilters().addAll(
                new StringFilter<>("Nome", cliente -> cliente.getCognome() + " " + cliente.getNome()),
                new StringFilter<>("Ragione Sociale", ClienteDTO::getRagioneSociale),
                new StringFilter<>("Partita IVA", ClienteDTO::getPartitaIVA),
                new StringFilter<>("Codice Fiscale", ClienteDTO::getCodiceFiscale)
        );
    }

    public void updateData() {
        customerOrchestrator.paginaClientiPronta();
    }

    public void setListaClienti(List<ClienteDTO> clienti) {
        clientiGrid.getItems().clear();
        clientiGrid.setItems(FXCollections.observableArrayList(clienti));

    }
}
