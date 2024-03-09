package org.unina.uninadelivery.presentation.orchestrator.customerdomain;

import javafx.scene.Node;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.customerdomain.CustomerService;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdineController;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

public class CustomerOrchestrator extends Orchestrator {

    public CustomerOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
    }

    public void visualizzaOrdineClicked(int idOrdine) {
        //get ordine by id
        CustomerService customerService = new CustomerService();

        final OrdineClienteDTO ordine;

        try {
            ordine = customerService.getOrdineCliente(idOrdine).orElse(null);

            if(ordine != null) {
                //open ordine view
                DashboardController controller = (DashboardController) ((Node)dashboardStage.getScene().getUserData());
                controller.changeView("ORDINE", "/views/customerdomain/ordine-view.fxml", c -> new OrdineController(ordine));
            }
        } catch (ServiceException e) {
            //todo: gestire errore
        }

    }

}
