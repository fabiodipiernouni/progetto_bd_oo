package org.unina.uninadelivery.presentation.orchestrator.customerdomain;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.customerdomain.OrdineDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.customerdomain.ordineController;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerOrchestrator extends Orchestrator {

    public CustomerOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
    }

    public void visualizzaOrdineClicked(int idOrdine) {
        //get ordine by id
        OrdineDTO ordine = new OrdineDTO(idOrdine, LocalDate.now());

        //open ordine view
        DashboardController controller = (DashboardController) dashboardStage.getScene().getUserData();

        controller.changeView("ORDINE", "/views/customerdomain/ordine-view.fxml", c -> new ordineController(ordine));
    }

}
