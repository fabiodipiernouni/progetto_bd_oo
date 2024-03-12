package org.unina.uninadelivery.presentation.orchestrator.customerdomain;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.customerdomain.CustomerService;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.customerdomain.ClientiController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdineController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdiniController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.SpedizioneController;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.customerdomain.SpedizioneModel;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CustomerOrchestrator extends Orchestrator {

    private final CustomerService customerService;
    private final ShipmentService shipmentService;
    private final ClientiController clientiController;
    private OrdiniController ordiniController;

    public CustomerOrchestrator(Stage dashboardStage, ClientiController clientiController) {
        super(dashboardStage);
        this.clientiController = clientiController;

        customerService = new CustomerService();
        shipmentService = new ShipmentService();
    }


    public void paginaClientiPronta() {
        Session session = Session.getInstance();

        UtenteDTO utenteDTO = session.getUserDto().getValue();

        try {
            List<ClienteDTO> listaCLienti = Collections.emptyList();
            if (utenteDTO.getProfilo().equals("Operatore"))
                listaCLienti = customerService.getListaClienti(((OperatoreFilialeDTO) utenteDTO).getFiliale());
            else if (utenteDTO.getProfilo().equals("Manager"))
                listaCLienti = customerService.getListaClienti();

            clientiController.setListaClienti(listaCLienti);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void visualizzaOrdiniClicked(ClienteDTO cliente) {
        Session session = Session.getInstance();

        UtenteDTO utenteDTO = session.getUserDto().getValue();

        try {

            List<OrdineClienteDTO> listaOrdiniCliente = Collections.emptyList();

            if (utenteDTO.getProfilo().equals("Operatore"))
                listaOrdiniCliente = customerService.getOrdiniCliente(((OperatoreFilialeDTO) utenteDTO).getFiliale(), cliente);
            else if (utenteDTO.getProfilo().equals("Manager"))
                listaOrdiniCliente = customerService.getOrdiniCliente(cliente);

            DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();

            ordiniController = new OrdiniController(dashboardStage, this, cliente);
            dashboardController.changeView("ORDINI", "/views/customerdomain/ordini-view.fxml", c -> ordiniController);
            ordiniController.setListaOrdiniCliente(listaOrdiniCliente);

        } catch (ServiceException e) {
            //TODO: gestire errore
        }

    }


    public void visualizzaOrdineClicked(OrdineClienteDTO ordine) {

        OrdineController ordineController = new OrdineController(dashboardStage, this, ordine);
        DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        dashboardController.changeView("ORDINE", "/views/customerdomain/ordine-view.fxml", c -> ordineController);


    }


    public void visualizzaOrdiniDataClicked(ClienteDTO clienteDTO, LocalDate dataInizio, LocalDate dataFine) {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {

            List<OrdineClienteDTO> listaOrdiniCliente = customerService.getOrdiniCliente(operatoreFilialeDTO.getFiliale(), clienteDTO, dataInizio, dataFine);

            ordiniController.setListaOrdiniCliente(listaOrdiniCliente);

        } catch (ServiceException e) {
            //TODO: gestire errore
        }
    }

    public void creaSpedizioneClicked(OrdineClienteDTO ordineCliente) {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {
            customerService.creaSpedizione(ordineCliente, operatoreFilialeDTO);

            //ricarico la lista degli ordini
            List<OrdineClienteDTO> listaOrdiniCliente = customerService.getOrdiniCliente(operatoreFilialeDTO.getFiliale(), ordineCliente.getCliente());
            ordiniController.setListaOrdiniCliente(listaOrdiniCliente);
            ordiniController.resettaFiltri();

            Platform.runLater(() ->
                    dashboardController.showDialog("Info", "Creazione Spedizione", "Spedizione creata con successo")
            );
        } catch (ServiceException e) {
            //TODO: gestire errore
            System.out.println(e.getMessage());
        }

    }

    public void visualizzaSpedizioneClicked(OrdineClienteDTO ordineCliente) {
        try{
            SpedizioneDTO spedizione = shipmentService.getSpedizione(ordineCliente);

            if(spedizione == null)
                dashboardController.showDialog("Errore", "Visualizzazione Spedizione", "Spedizione non trovata");
            else {
                String cliente = spedizione.getOrdineCliente().getCliente().getRagioneSociale() != null ? spedizione.getOrdineCliente().getCliente().getRagioneSociale():spedizione.getOrdineCliente().getCliente().getNome() + " " + spedizione.getOrdineCliente().getCliente().getCognome();
                SpedizioneController spedizioneController = new SpedizioneController(new SpedizioneModel(
                        String.valueOf(spedizione.getId()),
                        cliente,
                        spedizione.getStato(),
                        spedizione.getDataCreazione(),
                        spedizione.getDataInizioLavorazione(),
                        spedizione.getDataFineLavorazione(),
                        spedizione.getOrganizzatore().getUsername(),
                        spedizione.getTrackingNumber(),
                        shipmentService.getCountOrdiniDiLavoroPackagingBySpedizione(spedizione),
                        shipmentService.getCountOrdiniDiLavoroPackagingDaCompletareBySpedizione(spedizione),
                        shipmentService.getCountPacchiGeneratiBySpedizione(spedizione),
                        shipmentService.getCountPacchiDaSpedireBySpedizione(spedizione),
                        shipmentService.getCountOrdiniDiLavoroTrasportoBySpedizione(spedizione),
                        shipmentService.getCountOrdiniDiLavoroTrasportoDaCompletareBySpedizione(spedizione)
                ));
                DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
                dashboardController.changeView("SPEDIZIONE", "/views/shipmentdomain/spedizione-view.fxml",
                        c-> spedizioneController);
            }
        }
        catch (ServiceException e) {
            dashboardController.showDialog("errore", "Visualizzazione Spedizione", "Errore nel reperire la spedizione");
        }


    }
}
