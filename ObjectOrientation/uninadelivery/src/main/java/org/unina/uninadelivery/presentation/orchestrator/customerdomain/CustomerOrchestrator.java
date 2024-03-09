package org.unina.uninadelivery.presentation.orchestrator.customerdomain;

import javafx.stage.Stage;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.customerdomain.ordineController;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

public class CustomerOrchestrator extends Orchestrator {

    public CustomerOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
    }

    public void visualizzaOrdineClicked(int idOrdine) {
        //get ordine by id
        OrdineClienteDTO ordine = null;

        //open ordine view
        DashboardController controller = (DashboardController) dashboardStage.getScene().getUserData();

        controller.changeView("ORDINE", "/views/customerdomain/ordine-view.fxml", c -> new ordineController(ordine));
    }

}
