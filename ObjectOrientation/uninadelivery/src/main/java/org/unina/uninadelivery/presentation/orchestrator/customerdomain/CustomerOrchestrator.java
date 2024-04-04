package org.unina.uninadelivery.presentation.orchestrator.customerdomain;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.customerdomain.CustomerService;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.controller.customerdomain.ClientiController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdineController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdiniController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdiniMainViewController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.SpedizioneController;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.customerdomain.SpedizioneModel;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerOrchestrator extends Orchestrator {

    private final CustomerService customerService;
    private final ShipmentService shipmentService;
    private final ClientiController clientiController;
    private final OrdiniMainViewController ordiniMainViewController;
    private OrdiniController ordiniController;

    public CustomerOrchestrator(Stage dashboardStage, ClientiController clientiController, OrdiniMainViewController ordiniMainViewController) {
        super(dashboardStage);
        this.clientiController = clientiController;
        this.ordiniMainViewController = ordiniMainViewController;

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

            if(listaCLienti == null)
                listaCLienti = Collections.emptyList();

            clientiController.setListaClienti(listaCLienti);

        } catch (ServiceException e) {
            e.printStackTrace();
            dashboardController.showDialog("error", "Visualizzazione Clienti", e.getMessage());
        }
    }

    public void paginaOrdiniMainPronta(LocalDate dataInizio, LocalDate dataFine) {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();
        Task<List<OrdineClienteDTO>> ordiniTask = null;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            if(utenteDTO.getProfilo().equals("Operatore")) {
                ordiniTask = customerService.getOrdiniCliente(dataInizio, dataFine, ((OperatoreFilialeDTO) utenteDTO).getFiliale());
            }
            else if(utenteDTO.getProfilo().equals("Manager")) {
                ordiniTask = customerService.getOrdiniCliente(dataInizio, dataFine);
            }
            else {
                return;
            }

            ordiniTask.setOnRunning(e -> Platform.runLater(() -> taskRunning()));

            ordiniTask.setOnSucceeded(e -> Platform.runLater(() -> {
                try {
                    ordiniMainViewController.setListaOrdiniCliente(((Task<List<OrdineClienteDTO>>) e.getSource()).getValue());
                }
                catch (Exception ex) {
                    dashboardController.showDialog("error", "Visualizzazione Ordini", "Errore nella visualizzazione degli ordini");
                }

                taskCompleted();
            }));
            ordiniTask.setOnFailed(e -> Platform.runLater(() -> {
                dashboardController.showDialog("error", "Visualizzazione Ordini", "Errore nel reperire gli ordini");
                taskCompleted();
            }));

            executorService.submit(ordiniTask);
        }catch (ServiceException e) {
            e.printStackTrace();
            dashboardController.showDialog("error", "Visualizzazione Clienti", e.getMessage());
        }
        finally {
            executorService.shutdown();
        }
    }

    public void visualizzaOrdiniClicked(ClienteDTO cliente) {
        Session session = Session.getInstance();

        UtenteDTO utenteDTO = session.getUserDto().getValue();
        Task<List<OrdineClienteDTO>> ordiniTask = null;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            if (utenteDTO.getProfilo().equals("Operatore")) {
                ordiniTask = customerService.getOrdiniCliente(((OperatoreFilialeDTO) utenteDTO).getFiliale(), cliente);
            }
            else if (utenteDTO.getProfilo().equals("Manager")) {
                ordiniTask = customerService.getOrdiniCliente(cliente);
            }

            ordiniTask.setOnRunning(e -> Platform.runLater(() -> taskRunning()));

            ordiniTask.setOnSucceeded(e -> Platform.runLater(() -> {
                try {
                    List<OrdineClienteDTO> listaOrdiniCliente = ((Task<List<OrdineClienteDTO>>) e.getSource()).getValue();
                    ordiniController = new OrdiniController(dashboardStage, this, cliente);
                    dashboardController.changeView("ORDINI", "/views/customerdomain/ordini-view.fxml", c -> ordiniController);
                    ordiniController.setListaOrdiniCliente(listaOrdiniCliente);
                }
                catch (Exception ex) {
                    taskCompleted();
                    dashboardController.showDialog("error", "Visualizzazione Ordini", "Errore nella visualizzazione degli ordini");
                    return;
                }

                taskCompleted();
            }));

            ordiniTask.setOnFailed(e -> Platform.runLater(() -> {
                taskCompleted();
                dashboardController.showDialog("error", "Visualizzazione Ordini", "Errore nel reperire gli ordini");
            }));

            executorService.submit(ordiniTask);
        } catch (ServiceException e) {
            dashboardController.showDialog("error", "Visualizzazione Ordini", e.getMessage());
        }
        finally {
            executorService.shutdown();
        }
    }

    public void visualizzaOrdineClicked(OrdineClienteDTO ordine) {

        OrdineController ordineController = new OrdineController(dashboardStage, ordine);
        //DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        dashboardController.changeView("ORDINE", "/views/customerdomain/ordine-view.fxml", c -> ordineController);
    }

    public void visualizzaOrdiniDataClicked(ClienteDTO clienteDTO, LocalDate dataInizio, LocalDate dataFine) {
        Session session = Session.getInstance();

        UtenteDTO utenteDTO = session.getUserDto().getValue();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            Task<List<OrdineClienteDTO>> ordiniTask = null;

            if(utenteDTO.getProfilo().equals("Operatore"))
                ordiniTask = customerService.getOrdiniCliente( ((OperatoreFilialeDTO) utenteDTO).getFiliale(), clienteDTO, dataInizio, dataFine);
            else
                if(utenteDTO.getProfilo().equals("Manager"))
                    ordiniTask = customerService.getOrdiniCliente(clienteDTO, dataInizio, dataFine);


            ordiniTask.setOnRunning(e -> Platform.runLater(() -> taskRunning()));

            ordiniTask.setOnSucceeded(e -> Platform.runLater(() -> {
                try {
                    List<OrdineClienteDTO> listaOrdiniCliente = ((Task<List<OrdineClienteDTO>>) e.getSource()).getValue();
                    if(ordiniController != null) {
                        ordiniController.setListaOrdiniCliente(listaOrdiniCliente);
                    }
                    else {
                        ordiniMainViewController.setListaOrdiniCliente(listaOrdiniCliente);
                    }
                }
                catch (Exception ex) {
                    taskCompleted();
                    dashboardController.showDialog("error", "Visualizzazione Ordini", "Errore nella visualizzazione degli ordini");
                    return;
                }

                taskCompleted();
            }));

            ordiniTask.setOnFailed(e -> Platform.runLater(() -> {
                taskCompleted();
                dashboardController.showDialog("error", "Visualizzazione Ordini", "Errore nel reperire gli ordini");
            }));

            executorService.submit(ordiniTask);
        } catch (ServiceException e) {
            e.printStackTrace();
            dashboardController.showDialog("errore", "Visualizzazione ordini", e.getMessage());
        }
        finally {
            executorService.shutdown();
        }
    }

    public void creaSpedizioneClicked(OrdineClienteDTO ordineCliente) {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();
        Task<List<OrdineClienteDTO>> ordiniTask = null;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            customerService.creaSpedizione(ordineCliente, operatoreFilialeDTO);

            //ricarico la lista degli ordini
            ordiniTask = customerService.getOrdiniCliente(operatoreFilialeDTO.getFiliale(), ordineCliente.getCliente());

            ordiniTask.setOnRunning(e -> Platform.runLater(() -> taskRunning()));

            ordiniTask.setOnSucceeded(e -> Platform.runLater(() -> {
                try {
                    List<OrdineClienteDTO> listaOrdiniCliente = ((Task<List<OrdineClienteDTO>>) e.getSource()).getValue();

                    if (ordiniController != null) {
                        ordiniController.setListaOrdiniCliente(listaOrdiniCliente);
                        ordiniController.resettaFiltri();
                    } else {
                        ordiniMainViewController.setListaOrdiniCliente(listaOrdiniCliente);
                        ordiniMainViewController.resettaFiltri();
                    }

                    dashboardController.showDialog("Info", "Creazione Spedizione", "Spedizione creata con successo");
                }
                catch (Exception ex) {
                    taskCompleted();
                    dashboardController.showDialog("error", "Visualizzazione Ordini", "Errore nella visualizzazione degli ordini");
                    return;
                }

                taskCompleted();
            }));

            ordiniTask.setOnFailed(e -> Platform.runLater(() -> {
                taskCompleted();
                dashboardController.showDialog("error", "Visualizzazione Ordini", "Errore nel reperire gli ordini");
            }));

            executorService.submit(ordiniTask);
        } catch (ServiceException e) {
            e.printStackTrace();
            dashboardController.showDialog("errore", "Crea Spedizione", e.getMessage());
        }
        finally {
            executorService.shutdown();
        }
    }

    public void visualizzaSpedizioneClicked(OrdineClienteDTO ordineCliente) {
        try {
            SpedizioneDTO spedizione = shipmentService.getSpedizione(ordineCliente);
            Session session = Session.getInstance();
            UtenteDTO utente = session.getUserDto().getValue();

            if (spedizione == null)
                dashboardController.showDialog("Error", "Visualizzazione Spedizione", "Spedizione non trovata");
            else {
                String cliente = spedizione.getOrdineCliente().getCliente().getIntestazione();
                FilialeDTO filiale = utente.getProfilo().equals("Manager") ? null: (utente.getProfilo().equals("Operatore") ? ((OperatoreFilialeDTO) utente).getFiliale(): ((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());

                SpedizioneController spedizioneController = new SpedizioneController(new SpedizioneModel(
                        String.valueOf(spedizione.getId()),
                        cliente,
                        spedizione.getStato(),
                        spedizione.getDataCreazione(),
                        spedizione.getDataInizioLavorazione(),
                        spedizione.getDataFineLavorazione(),
                        spedizione.getOrganizzatore().getUsername(),
                        spedizione.getTrackingNumber(),
                        shipmentService.getCountOrdiniDiLavoroPackagingBySpedizione(filiale, spedizione),
                        shipmentService.getCountOrdiniDiLavoroPackagingDaCompletareBySpedizione(filiale, spedizione),
                        shipmentService.getCountPacchiGeneratiBySpedizione(filiale, spedizione),
                        shipmentService.getCountPacchiDaSpedireBySpedizione(filiale, spedizione),
                        shipmentService.getCountOrdiniDiLavoroTrasportoBySpedizione(filiale, spedizione),
                        shipmentService.getCountOrdiniDiLavoroTrasportoDaCompletareBySpedizione(filiale, spedizione),
                        spedizione.getOrdineCliente(),
                        spedizione,
                        filiale
                ));

                dashboardController.changeView("SPEDIZIONE", "/views/shipmentdomain/spedizione-view.fxml",
                        c -> spedizioneController);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            dashboardController.showDialog("error", "Visualizzazione Spedizione", e.getMessage());
        }
    }
}
