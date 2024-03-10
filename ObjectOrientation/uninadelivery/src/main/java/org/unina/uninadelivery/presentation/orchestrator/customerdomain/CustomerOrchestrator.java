package org.unina.uninadelivery.presentation.orchestrator.customerdomain;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.customerdomain.CustomerService;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.customerdomain.ClientiController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdineController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdiniController;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerOrchestrator extends Orchestrator {

    private final CustomerService customerService = new CustomerService();
    private final ClientiController clientiController;
    private OrdiniController ordiniController;

    public CustomerOrchestrator(Stage dashboardStage, ClientiController clientiController) {
        super(dashboardStage);
        this.clientiController = clientiController;

    }


    public void paginaClientiPronta() {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {
            List<ClienteDTO> listaCLienti = customerService.getListaClienti(operatoreFilialeDTO.getFiliale());
            clientiController.setListaClienti(listaCLienti);
        }
        catch (ServiceException e) {
            //TODO: gestire errore
        }
    }

    public void visualizzaOrdiniClicked(ClienteDTO cliente) {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {

            List<OrdineClienteDTO> listaOrdiniCliente = customerService.getOrdiniCliente(operatoreFilialeDTO.getFiliale(), cliente);
            DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();

            ordiniController = new OrdiniController(dashboardStage, this, cliente);
            dashboardController.changeView("ORDINI", "/views/customerdomain/ordini-view.fxml", c-> ordiniController);
            ordiniController.setListaOrdiniCliente(listaOrdiniCliente);

        }
        catch (ServiceException e) {
            //TODO: gestire errore
        }

    }


    public void visualizzaOrdineClicked(OrdineClienteDTO ordine) {

        OrdineController ordineController = new OrdineController(dashboardStage, this, ordine);
        DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        dashboardController.changeView("ORDINE", "/views/customerdomain/ordine-view.fxml", c-> ordineController);


    }


    public void visualizzaOrdiniDataClicked(ClienteDTO clienteDTO, LocalDate dataInizio, LocalDate dataFine) {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {

            List<OrdineClienteDTO> listaOrdiniCliente = customerService.getOrdiniCliente(operatoreFilialeDTO.getFiliale(), clienteDTO, dataInizio, dataFine);

            ordiniController.setListaOrdiniCliente(listaOrdiniCliente);

        }
        catch (ServiceException e) {
            //TODO: gestire errore
        }
    }

    public void creaSpedizioneClicked(OrdineClienteDTO ordineCliente) {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {
            customerService.creaSpedizione(ordineCliente, operatoreFilialeDTO);

            //TODO: notificare successo

            //ricarico la lista degli ordini
            List<OrdineClienteDTO> listaOrdiniCliente = customerService.getOrdiniCliente(operatoreFilialeDTO.getFiliale(), ordineCliente.getCliente());
            ordiniController.setListaOrdiniCliente(listaOrdiniCliente);

        }
        catch (ServiceException e) {
            //TODO: gestire errore
            System.out.println(e.getMessage());
        }

    }

    public void visualizzaSpedizioneClicked(OrdineClienteDTO ordineCliente) {
        /*try{
            //SpedizioneDTO spedizione = customerService.getSpedizione(ordineCliente);


        }
        catch (ServiceException e) {
            //TODO: gestire errore
        }*/

    }
}
