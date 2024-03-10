package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.CorriereOrchestrator;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdiniPackagingController implements Initializable {
    private final Stage dashboardStage;

    @FXML
    private MFXPaginatedTableView<OrdineDiLavoroPackagingDTO> ordiniDiLavoroPackagingGrid;

    @FXML
    private MFXRadioButton filtroTuttiRadioBox;

    @FXML
    private MFXRadioButton filtroGruppoCorriereadioBox;

    @FXML
    private MFXRadioButton filtroPresiInCaricoRadioBox;

    @FXML
    private MFXRadioButton filtroDaPrendereInCaricoRadioBox;

    private CorriereOrchestrator corriereOrchestrator;

    public OrdiniPackagingController(Stage dashboardStage) {

        this.dashboardStage = dashboardStage;
        this.corriereOrchestrator = CorriereOrchestrator.getCorriereOrchestrator(dashboardStage);
        this.corriereOrchestrator.setOrdiniPackagingController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        ordiniDiLavoroPackagingGrid.autosizeColumnsOnInitialization();

        When.onChanged(ordiniDiLavoroPackagingGrid.currentPageProperty())
                .then((oldValue, newValue) -> ordiniDiLavoroPackagingGrid.autosizeColumns())
                .listen();

    }

    private void setup() {


        corriereOrchestrator.pagineOrdiniPackagingPronta();
    }
}
