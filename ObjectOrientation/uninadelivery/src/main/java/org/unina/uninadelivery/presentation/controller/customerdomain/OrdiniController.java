package org.unina.uninadelivery.presentation.controller.customerdomain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.unina.uninadelivery.presentation.orchestrator.customerdomain.CustomerOrchestrator;

public class OrdiniController {

    private final Stage dashboardStage;

    public OrdiniController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
    }

    @FXML
    public void onVisualizzaOrdine(ActionEvent actionEvent) {
        CustomerOrchestrator orchestrator = new CustomerOrchestrator(dashboardStage);
        /*Node source = (Node) actionEvent.getSource();
        Window theStage = source.getScene().getWindow();*/

        orchestrator.visualizzaOrdineClicked(3);
    }
}
