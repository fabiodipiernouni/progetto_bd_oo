package org.unina.uninadelivery.presentation.controller.customerdomain;

import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.FloatFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.customerdomain.DettaglioOrdineDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class OrdineController implements Initializable {
    private final Stage dashboardStage;
    private final OrdineClienteDTO ordineDTO;


    @FXML
    private Label clienteRagioneSocialeLabel;

    @FXML
    private Label clientePartitaIVACodiceFiscaleLabel;

    @FXML
    private Label clienteEmailLabel;

    @FXML
    private Label dataOrdineClienteLabel;

    @FXML
    private Label statoOrdineClienteLabel;

    @FXML
    private Label numeroOrdineClienteLabel;

    @FXML
    private ImageView imgCopyToNumeroOrdineCliente;

    @FXML
    private Label importoTotaleOrdineClienteLabel;

    @FXML
    private MFXTableView<DettaglioOrdineDTO> dettaglioOrdineGrid;

    public OrdineController(Stage dashboardStage, OrdineClienteDTO ordineDTO) {
        this.dashboardStage = dashboardStage;

        this.ordineDTO = ordineDTO;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clienteRagioneSocialeLabel.setText(ordineDTO.getCliente().getIntestazione());
        if(ordineDTO.getCliente().getRagioneSociale() != null) {
            clientePartitaIVACodiceFiscaleLabel.setText(ordineDTO.getCliente().getPartitaIVA());
        }
        else {
            clientePartitaIVACodiceFiscaleLabel.setText(ordineDTO.getCliente().getCodiceFiscale());
        }

        clienteEmailLabel.setText(ordineDTO.getCliente().getEmail());

        dataOrdineClienteLabel.setText(ordineDTO.getDataOrdine().toString());
        statoOrdineClienteLabel.setText(ordineDTO.getStato());
        numeroOrdineClienteLabel.setText(ordineDTO.getNumeroOrdine());
        importoTotaleOrdineClienteLabel.setText(ordineDTO.getImportoTotale() + " €");


        setup();

        dettaglioOrdineGrid.autosizeColumnsOnInitialization();

        imgCopyToNumeroOrdineCliente.setOnMouseClicked(event -> {
            javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(ordineDTO.getNumeroOrdine());
            clipboard.setContent(content);
        });
    }

    private void setup() {

        MFXTableColumn<DettaglioOrdineDTO> codiceEANColumn = new MFXTableColumn<>("Codice EAN", false, Comparator.comparing(dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getCodiceEAN()));
        MFXTableColumn<DettaglioOrdineDTO> nomeColumn = new MFXTableColumn<>("Nome", false, Comparator.comparing(dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getNome()));
        MFXTableColumn<DettaglioOrdineDTO> prezzoColumn = new MFXTableColumn<>("Prezzo", false, Comparator.comparing(dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getPrezzo()));
        MFXTableColumn<DettaglioOrdineDTO> pesoColumn = new MFXTableColumn<>("Peso", false, Comparator.comparing(dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getPeso()));
        MFXTableColumn<DettaglioOrdineDTO> pericolositaColumn = new MFXTableColumn<>("Pericolosità", false, Comparator.comparing(dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getPericolosita()));
        MFXTableColumn<DettaglioOrdineDTO> quantitaColumn = new MFXTableColumn<>("Quantità", false, Comparator.comparing(DettaglioOrdineDTO::getQuantita));
        MFXTableColumn<DettaglioOrdineDTO> importoTotaleColumn = new MFXTableColumn<>("Importo Totale", false, Comparator.comparing(dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getPrezzo() * dettaglioOrdineDTO.getQuantita()));


        codiceEANColumn.setRowCellFactory(dettaglioOrdineDTO -> new MFXTableRowCell<>(dettaglioOrdine -> dettaglioOrdine.getProdotto().getCodiceEAN()));
        nomeColumn.setRowCellFactory(dettaglioOrdineDTO -> new MFXTableRowCell<>(dettaglioOrdine -> dettaglioOrdine.getProdotto().getNome()));
        prezzoColumn.setRowCellFactory(dettaglioOrdineDTO -> new MFXTableRowCell<>(dettaglioOrdine -> dettaglioOrdine.getProdotto().getPrezzo() + " €"));
        pesoColumn.setRowCellFactory(dettaglioOrdineDTO -> new MFXTableRowCell<>(dettaglioOrdine -> dettaglioOrdine.getProdotto().getPeso() + " Kg"));
        pericolositaColumn.setRowCellFactory(dettaglioOrdineDTO -> new MFXTableRowCell<>(dettaglioOrdine -> dettaglioOrdine.getProdotto().getPericolosita()));
        quantitaColumn.setRowCellFactory(dettaglioOrdineDTO -> new MFXTableRowCell<>(DettaglioOrdineDTO::getQuantita));
        importoTotaleColumn.setRowCellFactory(dettaglioOrdineDTO -> new MFXTableRowCell<>(dettaglioOrdine -> String.format("%.2f€", dettaglioOrdine.getProdotto().getPrezzo() * dettaglioOrdine.getQuantita())));

        dettaglioOrdineGrid.getTableColumns().addAll(codiceEANColumn, nomeColumn, prezzoColumn, pesoColumn, pericolositaColumn, quantitaColumn, importoTotaleColumn);

        dettaglioOrdineGrid.getFilters().addAll(
                new StringFilter<>("Codice EAN", dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getCodiceEAN()),
                new StringFilter<>("Nome", dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getNome()),
                new DoubleFilter<>("Prezzo", dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getPrezzo()),
                new FloatFilter<>("Peso", dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getPeso()),
                new StringFilter<>("Pericolosità", dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getPericolosita()),
                new IntegerFilter<>("Quantità", DettaglioOrdineDTO::getQuantita),
                new DoubleFilter<>("Importo Totale", dettaglioOrdineDTO -> dettaglioOrdineDTO.getProdotto().getPrezzo() * dettaglioOrdineDTO.getQuantita())
        );

        dettaglioOrdineGrid.getItems().addAll(ordineDTO.getDettaglio());

    }
}
