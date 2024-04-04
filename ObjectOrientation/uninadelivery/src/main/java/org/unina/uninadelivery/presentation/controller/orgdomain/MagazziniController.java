package org.unina.uninadelivery.presentation.controller.orgdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.presentation.orchestrator.orgdomain.MagazzinoOrchestrator;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class MagazziniController implements Initializable {
    private final Stage dashboardStage;

    @FXML
    private MFXTableView<MagazzinoDTO> magazziniGrid;

    private MagazzinoOrchestrator magazzinoOrchestrator;

    public MagazziniController(Stage dashboardStage) {

        this.dashboardStage = dashboardStage;
        this.magazzinoOrchestrator = new MagazzinoOrchestrator(dashboardStage, this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        magazziniGrid.autosizeColumnsOnInitialization();

    }

    private void setup() {
        MFXTableColumn<MagazzinoDTO> nomeColumn = new MFXTableColumn<>("Nome", false, Comparator.comparing(magazzinoDto -> magazzinoDto.getNome().replace("Magazzino ", "")));
        MFXTableColumn<MagazzinoDTO> responsabileColumn = new MFXTableColumn<>("Responsabile", false, Comparator.comparing(magazzino -> magazzino.getIndirizzo().getCognome() + " " + magazzino.getIndirizzo().getNome()));
        MFXTableColumn<MagazzinoDTO> partitaIVAColumn = new MFXTableColumn<>("Partita IVA", false, null); //comparator a null perché non ha senso ordinare per partita IVA
        MFXTableColumn<MagazzinoDTO> indirizzoColumn = new MFXTableColumn<>("Indirizzo", false, Comparator.comparing(magazzino -> magazzino.getIndirizzo().getIndirizzo_1()  + (magazzino.getIndirizzo().getIndirizzo_2() != null ? " " + magazzino.getIndirizzo().getIndirizzo_2() : "")));
        MFXTableColumn<MagazzinoDTO> capColumn = new MFXTableColumn<>("CAP", false, Comparator.comparing(magazzino -> magazzino.getIndirizzo().getCAP()));
        MFXTableColumn<MagazzinoDTO> comuneColumn = new MFXTableColumn<>("Comune", false, Comparator.comparing(magazzino -> magazzino.getIndirizzo().getComune() ));
        MFXTableColumn<MagazzinoDTO> actionColumn = new MFXTableColumn<>("", false, null);

        nomeColumn.setRowCellFactory(magazzino -> new MFXTableRowCell<>(magazzinoDto -> magazzinoDto.getNome().replace("Magazzino ", "")));
        responsabileColumn.setRowCellFactory(magazzino -> new MFXTableRowCell<>(magazzinoDto -> magazzinoDto.getIndirizzo().getCognome() + " " + magazzinoDto.getIndirizzo().getNome()));
        partitaIVAColumn.setRowCellFactory(magazzino -> new MFXTableRowCell<>(magazzinoDto -> magazzinoDto.getIndirizzo().getCf_pIVA()));
        indirizzoColumn.setRowCellFactory(magazzino -> new MFXTableRowCell<>(magazzinoDto -> magazzinoDto.getIndirizzo().getIndirizzo_1()  + (magazzinoDto.getIndirizzo().getIndirizzo_2() != null ? " " + magazzinoDto.getIndirizzo().getIndirizzo_2() : "")));
        capColumn.setRowCellFactory(magazzino -> new MFXTableRowCell<>(magazzinoDto -> magazzinoDto.getIndirizzo().getCAP()));
        comuneColumn.setRowCellFactory(magazzino -> new MFXTableRowCell<>(magazzinoDto -> magazzinoDto.getIndirizzo().getComune()));
        actionColumn.setRowCellFactory(magazzino -> {
            //crea cella vuota
            MFXTableRowCell<MagazzinoDTO, Void> cell = new MFXTableRowCell<>(null);

            //crea un tasto
            MFXButton button = new MFXButton("Visualizza");
            button.setOnAction(event -> {
                magazzinoOrchestrator.visualizzaMagazzinoClicked(magazzino);
            });

            //imposta il tasto come grafica della cella
            cell.setGraphic(button);

            return cell;
        });

        magazziniGrid.getTableColumns().addAll(nomeColumn, responsabileColumn, partitaIVAColumn, indirizzoColumn, capColumn, comuneColumn, actionColumn);

        magazziniGrid.getFilters().addAll(
                new StringFilter<>("Nome", MagazzinoDTO::getNome),
                new StringFilter<>("Responsabile", magazzino -> magazzino.getIndirizzo().getNome() + " " + magazzino.getIndirizzo().getCognome()),
                new StringFilter<>("Partita IVA", magazzino -> magazzino.getIndirizzo().getCf_pIVA()),
                new StringFilter<>("Indirizzo", magazzino -> magazzino.getIndirizzo().getIndirizzo_1()  + (magazzino.getIndirizzo().getIndirizzo_2() != null ? " " + magazzino.getIndirizzo().getIndirizzo_2() : "")),
                new StringFilter<>("CAP", magazzino -> magazzino.getIndirizzo().getCAP()),
                new StringFilter<>("Comune", magazzino -> magazzino.getIndirizzo().getComune())
        );

        magazzinoOrchestrator.paginaMagazziniPronta();

    }

    public void setListaMagazzini(List<MagazzinoDTO> magazzini) {
        magazziniGrid.getItems().clear();
        magazziniGrid.setItems(FXCollections.observableArrayList(magazzini));

    }
}
