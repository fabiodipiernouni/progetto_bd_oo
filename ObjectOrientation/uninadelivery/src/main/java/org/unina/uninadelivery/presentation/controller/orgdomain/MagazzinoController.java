package org.unina.uninadelivery.presentation.controller.orgdomain;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.presentation.model.orgdomain.MerceStoccataModel;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class MagazzinoController implements Initializable {
    private final Stage dashboardStage;
    private final MagazzinoDTO magazzinoDTO;

    @FXML
    private MFXTableView<MerceStoccataModel> merciStoccateGrid;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblResponsabile;

    @FXML
    private Label lblPartitaIVA;

    @FXML
    private Label lblIndirizzo;

    public MagazzinoController(Stage dashboardStage, MagazzinoDTO magazzinoDTO) {
        this.dashboardStage = dashboardStage;

        this.magazzinoDTO = magazzinoDTO;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblNome.setText(magazzinoDTO.getNome());
        lblResponsabile.setText(magazzinoDTO.getIndirizzo().getCognome() + " " + magazzinoDTO.getIndirizzo().getNome());
        lblPartitaIVA.setText(magazzinoDTO.getIndirizzo().getCf_pIVA());

        String indirizzo = magazzinoDTO.getIndirizzo().getIndirizzo_1();
        if(magazzinoDTO.getIndirizzo().getIndirizzo_2() != null)
            indirizzo += ", " + magazzinoDTO.getIndirizzo().getIndirizzo_2();
        indirizzo += magazzinoDTO.getIndirizzo().getCAP() + " " + magazzinoDTO.getIndirizzo().getComune();

        lblIndirizzo.setText(indirizzo);

        setup();

        merciStoccateGrid.autosizeColumnsOnInitialization();

    }

    private void setup() {
        merciStoccateGrid.setMaxWidth(800);

        MFXTableColumn<MerceStoccataModel> codiceEANColumn = new MFXTableColumn<>("Codice EAN", false, Comparator.comparing(MerceStoccataModel::getCodiceEAN));
        MFXTableColumn<MerceStoccataModel> nomeColumn = new MFXTableColumn<>("Nome", false, Comparator.comparing(MerceStoccataModel::getNome));
        MFXTableColumn<MerceStoccataModel> quantitaRealeColumn = new MFXTableColumn<>("Quantità Reale", false, Comparator.comparing(MerceStoccataModel::getQuantita));
        MFXTableColumn<MerceStoccataModel> quantitaPrenotataColumn = new MFXTableColumn<>("Quantità Prenotata", false, Comparator.comparing(MerceStoccataModel::getQuantitaPrenotata));
        MFXTableColumn<MerceStoccataModel> quantitaDisponibileColumn = new MFXTableColumn<>("Quantità Disponibile", false, Comparator.comparing(MerceStoccataModel::getQuantitaDisponibile));
        MFXTableColumn<MerceStoccataModel> settoreMagazzinoColumn = new MFXTableColumn<>("Settore Magazzino", false, Comparator.comparing(MerceStoccataModel::getSettoreMagazzino));



        codiceEANColumn.setRowCellFactory(merceStoccata -> new MFXTableRowCell<>(MerceStoccataModel::getCodiceEAN));
        nomeColumn.setRowCellFactory(merceStoccata -> new MFXTableRowCell<>(MerceStoccataModel::getNome));
        quantitaRealeColumn.setRowCellFactory(merceStoccata -> new MFXTableRowCell<>(MerceStoccataModel::getQuantita));
        quantitaPrenotataColumn.setRowCellFactory(merceStoccata -> new MFXTableRowCell<>(MerceStoccataModel::getQuantitaPrenotata));
        quantitaDisponibileColumn.setRowCellFactory(merceStoccata -> new MFXTableRowCell<>(MerceStoccataModel::getQuantitaDisponibile));
        settoreMagazzinoColumn.setRowCellFactory(merceStoccata -> new MFXTableRowCell<>(MerceStoccataModel::getSettoreMagazzinoAsString));

        merciStoccateGrid.getTableColumns().addAll(codiceEANColumn, nomeColumn, quantitaRealeColumn, quantitaPrenotataColumn, quantitaDisponibileColumn, settoreMagazzinoColumn);

        merciStoccateGrid.getFilters().addAll(
                new StringFilter<>("Codice EAN", MerceStoccataModel::getCodiceEAN),
                new StringFilter<>("Nome", MerceStoccataModel::getNome),
                new IntegerFilter<>("Quantità Reale", MerceStoccataModel::getQuantita),
                new IntegerFilter<>("Quantità Prenotata", MerceStoccataModel::getQuantitaPrenotata),
                new IntegerFilter<>("Quantità Disponibile", MerceStoccataModel::getQuantitaDisponibile),
                new StringFilter<>("Settore Magazzino", MerceStoccataModel::getSettoreMagazzinoAsString)
        );

    }

    public void setMerciStoccate(List<MerceStoccataModel> merciStoccate) {
        merciStoccateGrid.setItems(FXCollections.observableArrayList(merciStoccate));
    }
}
