package org.unina.uninadelivery.presentation.controller.customerdomain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.unina.uninadelivery.presentation.orchestrator.customerdomain.CustomerOrchestrator;

public class ordiniController {

    private final Stage dashboardStage;

    public ordiniController(Stage dashboardStage) {
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
