package org.unina.uninadelivery.presentation.controller.customerdomain;

import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.presentation.orchestrator.customerdomain.CustomerOrchestrator;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ClientiController implements Initializable {
    private final Stage dashboardStage;

    @FXML
    public MFXPaginatedTableView<ClienteDTO> clientiGrid;

    public ClientiController(Stage dashboardStage) {

        this.dashboardStage = dashboardStage;
    }

    @FXML
    public void onVisualizzaOrdine(ActionEvent actionEvent) {
        CustomerOrchestrator orchestrator = new CustomerOrchestrator(dashboardStage);
        /*Node source = (Node) actionEvent.getSource();
        Window theStage = source.getScene().getWindow();*/

        orchestrator.visualizzaOrdineClicked(3);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupPaginated();

        clientiGrid.autosizeColumnsOnInitialization();

        When.onChanged(clientiGrid.currentPageProperty())
                .then((oldValue, newValue) -> clientiGrid.autosizeColumns())
                .listen();
    }

    private void setupPaginated() {

        MFXTableColumn<ClienteDTO> nameColumn = new MFXTableColumn<>("Nome", false, Comparator.comparing(cliente -> cliente.getCognome() + " " + cliente.getNome()));
        MFXTableColumn<ClienteDTO> ragSocColumn = new MFXTableColumn<>("Ragione Sociale", false, Comparator.comparing(ClienteDTO::getRagioneSociale));
        MFXTableColumn<ClienteDTO> pivaColumn = new MFXTableColumn<>("Partita IVA", false, Comparator.comparing(ClienteDTO::getPartitaIVA));
        MFXTableColumn<ClienteDTO> cfColumn = new MFXTableColumn<>("Codice Fiscale", false, Comparator.comparing(ClienteDTO::getCodiceFiscale));
        MFXTableColumn<ClienteDTO> actionColumn = new MFXTableColumn<>("Azioni", false, Comparator.comparing(ClienteDTO::getId));

        nameColumn.setRowCellFactory(cliente -> new MFXTableRowCell<>(clienteDto -> clienteDto.getCognome() + " " + clienteDto.getNome()));
        ragSocColumn.setRowCellFactory(cliente -> new MFXTableRowCell<>(ClienteDTO::getRagioneSociale) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        pivaColumn.setRowCellFactory(cliente -> new MFXTableRowCell<>(ClienteDTO::getPartitaIVA));
        cfColumn.setRowCellFactory(cliente -> new MFXTableRowCell<>(ClienteDTO::getCodiceFiscale));

        clientiGrid.getTableColumns().addAll(nameColumn, ragSocColumn, pivaColumn, cfColumn, actionColumn);
        clientiGrid.getFilters().addAll(
                new StringFilter<>("Nome", cliente -> cliente.getCognome() + " " + cliente.getNome()),
                new StringFilter<>("Ragione Sociale", ClienteDTO::getRagioneSociale),
                new StringFilter<>("Partita IVA", ClienteDTO::getPartitaIVA),
                new StringFilter<>("Codice Fiscale", ClienteDTO::getCodiceFiscale)
        );

        List<ClienteDTO> l = new ArrayList<ClienteDTO>();
        l.add(new ClienteDTO(1, "Umberto", "Rossi", "ragsoc Umbi", "dsd@sds.it", null, "4839483948"));
        l.add(new ClienteDTO(2, "Silvio", "Rossi", "ragsoc Silvio", "silvio@sds.it", null, "3343121212"));

        clientiGrid.setItems(FXCollections.observableArrayList(l));
    }
}
