package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import lombok.Setter;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.*;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.*;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.customerdomain.SpedizioneModel;
import org.unina.uninadelivery.presentation.model.shipmentdomain.OrdinePackagingModel;
import org.unina.uninadelivery.presentation.model.shipmentdomain.OrdineSpedizioneModel;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OdlOrchestrator extends Orchestrator {

    private static OdlOrchestrator instance;

    private final ShipmentService shipmentService;

    @Setter
    private OrdiniPackagingController ordiniPackagingController;
    @Setter
    private SpedizioniController spedizioniController;

    @Setter
    private OrdiniSpedizioneController ordiniSpedizioneController;

    /**
     * Costruttore della classe Orchestrator, è protetta rendendo la classe non instanziabile ma derivabile
     *
     * @param dashboardStage
     */
    protected OdlOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
        shipmentService = new ShipmentService();
    }

    public static OdlOrchestrator getOdlOrchestrator(Stage dashboardStage) {
        if (instance == null) {
            instance = new OdlOrchestrator(dashboardStage);
        }
        return instance;
    }


    /**** ORDINI DI LAVORO PACKAGING ****/

    public void paginaOrdiniPackagingPronta() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();

        Task<List<OrdineDiLavoroPackagingDTO>> listaOrdiniTask;

        try {
            switch (utenteDTO.getProfilo()) {
                case "OperatoreCorriere":
                    listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreCorriereDTO) utenteDTO).getGruppoCorriere().getFiliale());

                    break;
                case "Operatore":
                    listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreFilialeDTO) utenteDTO).getFiliale());

                    break;
                case "Manager":
                    listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging();
                    break;
                default:
                    throw new SpedizioniException("Pagina richiamata con profilo non gestito.");
            }

            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di packaging");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroPackagingDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniPackagingController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void filtroOrdiniPackagingTuttiClicked() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroPackagingDTO>> listaOrdiniTask;

            if (utente.getProfilo().equals("OperatoreCorriere"))
                listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());
            else if (utente.getProfilo().equals("Operatore"))
                listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreFilialeDTO) utente).getFiliale());
            else
                throw new SpedizioniException("Pagina richiamata con profilo non gestito.");

            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));

            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di packaging");
                });
            });

            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroPackagingDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniPackagingController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void filtroOrdiniPackagingGruppoCorriereClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroPackagingDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO.getGruppoCorriere());

            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di packaging");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroPackagingDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniPackagingController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }


    public void filtroOrdiniPackagingPresiInCaricoClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroPackagingDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO);
            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di packaging");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroPackagingDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniPackagingController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void filtroOrdiniPackagingDaPrendereInCaricoClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroPackagingDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO.getGruppoCorriere().getFiliale(), "DaAssegnare");
            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di packaging");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroPackagingDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniPackagingController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void filtroOrdiniPackagingEmessiDaMeClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroPackagingDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreFilialeDTO);

            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di packaging");
                });
            });

            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroPackagingDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniPackagingController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void visualizzaOrdinePackagingClicked(OrdineDiLavoroPackagingDTO ordineLavoroPackaging) throws SpedizioniException {
        IndirizzoDTO indirizzo = ordineLavoroPackaging.getSpedizione().getOrdineCliente().getIndirizzoSpedizione() == null ? ordineLavoroPackaging.getSpedizione().getOrdineCliente().getIndirizzoFatturazione() : ordineLavoroPackaging.getSpedizione().getOrdineCliente().getIndirizzoSpedizione();
        OrdinePackagingModel model = new OrdinePackagingModel(
                ordineLavoroPackaging,
                ordineLavoroPackaging.getId(),
                ordineLavoroPackaging.getDataCreazione(),
                ordineLavoroPackaging.getSpedizione().getOrdineCliente().getCliente().getIntestazione(),
                ordineLavoroPackaging.getDataInizioLavorazione(),
                ordineLavoroPackaging.getDataFineLavorazione(),
                ordineLavoroPackaging.getGruppoCorriere() != null ? ordineLavoroPackaging.getGruppoCorriere().getNome() : "",
                ordineLavoroPackaging.getSpedizione().getOrganizzatore().getUsername(),
                ordineLavoroPackaging.getFiliale().getNome(),
                indirizzo.getIndirizzo_1(),
                indirizzo.getIndirizzo_2() == null ? "" : indirizzo.getIndirizzo_2(),
                ordineLavoroPackaging.getStato(),
                ordineLavoroPackaging.getNoteAggiuntiveCliente(),
                ordineLavoroPackaging.getNoteAggiuntiveOperatore(),
                ordineLavoroPackaging.getMagazzino(),
                ordineLavoroPackaging.getSpedizione()
        );

        OrdinePackagingController ordinePackagingController = new OrdinePackagingController(model);
        DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        dashboardController.changeView("ORDINE PACKAGING", "/views/shipmentdomain/ordine-packaging-view.fxml",
                c -> ordinePackagingController);
    }

    public void visualizzaOrdineSpedizioneClicked(OrdineDiLavoroSpedizioneDTO ordine) {
        OrdineSpedizioneModel model = new OrdineSpedizioneModel(
                ordine.getId(),
                ordine.getDataCreazione(),
                ordine.getSpedizione().getOrdineCliente().getCliente().getIntestazione(),
                ordine.getDataInizioLavorazione(),
                ordine.getDataFineLavorazione(),
                ordine.getGruppoCorriere() != null ? ordine.getGruppoCorriere().getNome() : "Non assegnato",
                ordine.getFiliale().getNome(),
                ordine.getSpedizione().getOrdineCliente().getIndirizzoSpedizione() != null ? ordine.getSpedizione().getOrdineCliente().getIndirizzoSpedizione().getIndirizzo_1() : ordine.getSpedizione().getOrdineCliente().getIndirizzoFatturazione().getIndirizzo_1(),
                ordine.getSpedizione().getOrdineCliente().getIndirizzoSpedizione() != null ? ordine.getSpedizione().getOrdineCliente().getIndirizzoSpedizione().getIndirizzo_2() : ordine.getSpedizione().getOrdineCliente().getIndirizzoFatturazione().getIndirizzo_2(),
                ordine.getStato(),
                "",
                ordine.getNoteAggiuntiveOperatore(),
                ordine.getSpedizione(),
                ordine.getFiliale(),
                ordine.getGruppoCorriere()
        );

        OrdineSpedizioneController ordineSpedizioneController = new OrdineSpedizioneController(model);
        DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        dashboardController.changeView("ORDINE SPEDIZIONE", "/views/shipmentdomain/ordine-spedizione-view.fxml",
                c -> ordineSpedizioneController);
    }

    public void prendiInCaricoOrdinePackagingClicked(OrdineDiLavoroPackagingDTO ordine) throws SpedizioniException {

        Session session = Session.getInstance();
        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        ordine.setGruppoCorriere(operatoreCorriereDTO.getGruppoCorriere());
        ordine.setOperatoreCorriere(operatoreCorriereDTO);

        try {
            Task<Void> updateTask = shipmentService.getUpdateOrdinePackagingTask(ordine);

            updateTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            updateTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel prendere in carico l'ordine di lavoro di packaging");
                });
            });
            updateTask.setOnSucceeded(event -> {
                //aggiorno la lista degli ordini
                Task<List<OrdineDiLavoroPackagingDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
                listaOrdiniTask.setOnFailed(event2 -> {
                    Platform.runLater(() -> manageError("Errore", "Ordine preso in carico, ma è in errore il recupero degli ordini di lavoro di packaging."));
                });
                listaOrdiniTask.setOnSucceeded(event2 -> {
                    Platform.runLater(() -> {
                        List<OrdineDiLavoroPackagingDTO> listaOrdini = listaOrdiniTask.getValue();
                        ordiniPackagingController.setListaOrdini(listaOrdini);
                        ordiniPackagingController.resettaFiltri();
                        taskCompleted();
                        //Se tutto va bene dò un messaggio di aggiornamento effettuato con successo all'utente
                        dashboardController.showDialog("info", "Operazione completata", "Ordine preso in carico con successo");
                    });
                });
                ExecutorService executorService2 = Executors.newSingleThreadExecutor();
                executorService2.submit(listaOrdiniTask);
                executorService2.shutdown();
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(updateTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel prendere in carico l'ordine di lavoro di packaging");
        }
    }

    /**** ORDINI DI LAVORO SPEDIZIONE ****/

    public void paginaOrdiniSpedizionePronta() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();

        Task<List<OrdineDiLavoroSpedizioneDTO>> listaOrdiniTask;

        try {
            switch (utenteDTO.getProfilo()) {
                case "OperatoreCorriere":
                    listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione(((OperatoreCorriereDTO) utenteDTO).getGruppoCorriere().getFiliale());

                    break;
                case "Operatore":
                    listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione(((OperatoreFilialeDTO) utenteDTO).getFiliale());

                    break;
                case "Manager":
                    listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione();
                    break;
                default:
                    throw new SpedizioniException("Pagina richiamata con profilo non gestito.");
            }

            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di spedizione");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroSpedizioneDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniSpedizioneController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizioneTuttiClicked() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroSpedizioneDTO>> listaOrdiniTask;

            if (utente.getProfilo().equals("OperatoreCorriere"))
                listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione(((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());
            else if (utente.getProfilo().equals("Operatore"))
                listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione(((OperatoreFilialeDTO) utente).getFiliale());
            else
                throw new SpedizioniException("Pagina richiamata con profilo non gestito.");

            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di spedizione");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroSpedizioneDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniSpedizioneController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizioneGruppoCorriereClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroSpedizioneDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO.getGruppoCorriere());
            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di spedizione");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroSpedizioneDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniSpedizioneController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizionePresiInCaricoClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroSpedizioneDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO);
            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di spedizione");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroSpedizioneDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniSpedizioneController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizioneDaPrendereInCaricoClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroSpedizioneDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO.getGruppoCorriere().getFiliale(), "DaAssegnare");
            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di spedizione");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroSpedizioneDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniSpedizioneController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizioneEmessiDaMeClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {
            Task<List<OrdineDiLavoroSpedizioneDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreFilialeDTO);
            listaOrdiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            listaOrdiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di spedizione");
                });
            });
            listaOrdiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroSpedizioneDTO> listaOrdini = listaOrdiniTask.getValue();
                    ordiniSpedizioneController.setListaOrdini(listaOrdini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaOrdiniTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void prendiInCaricoOrdineSpedizioneClicked(OrdineDiLavoroSpedizioneDTO ordine) throws SpedizioniException {
        Session session = Session.getInstance();
        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        ordine.setGruppoCorriere(operatoreCorriereDTO.getGruppoCorriere());
        ordine.setOperatoreCorriere(operatoreCorriereDTO);

        try {
            //Prima preparo il task di aggiornamento dell'ordine
            Task<Void> updateTask = shipmentService.getUpdateOrdineSpedizioneTask(ordine);
            updateTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            updateTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel prendere in carico l'ordine di lavoro di spedizione");
                });
            });
            //Solo in caso di successo proseguo con il task di recupero degli ordini di lavoro di spedizione
            updateTask.setOnSucceeded(event -> {
                //aggiorno la lista degli ordini
                Task<List<OrdineDiLavoroSpedizioneDTO>> listaOrdiniTask = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
                listaOrdiniTask.setOnFailed(event2 -> {
                    Platform.runLater(() -> {
                        manageError("Errore", "Ordine preso in carico, ma è in errore il recupero degli ordini di lavoro di spedizione.");
                        taskCompleted();
                    });
                });
                listaOrdiniTask.setOnSucceeded(event2 -> {
                    Platform.runLater(() -> {
                        List<OrdineDiLavoroSpedizioneDTO> listaOrdini = listaOrdiniTask.getValue();
                        ordiniSpedizioneController.setListaOrdini(listaOrdini);
                        ordiniSpedizioneController.resettaFiltri();
                        taskCompleted();
                        //Se tutto va bene dò un messaggio di aggiornamento effettuato con successo all'utente
                        dashboardController.showDialog("info", "Operazione completata", "Ordine preso in carico con successo");
                    });
                });
                ExecutorService executorService2 = Executors.newSingleThreadExecutor();
                executorService2.submit(listaOrdiniTask);
                executorService2.shutdown();
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(updateTask);
            executorService.shutdown();
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel prendere in carico l'ordine di lavoro di spedizione");
        }
    }

    public void visualizzaSpedizioneClicked(UtenteDTO utente, SpedizioneDTO spedizione) throws SpedizioniException {
        try {
            FilialeDTO filiale = utente.getProfilo().equals("Manager") ? null : (utente.getProfilo().equals("Operatore") ? ((OperatoreFilialeDTO) utente).getFiliale() : ((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());

            Task<Integer> numeroOrdiniPackaging = shipmentService.getCountOrdiniDiLavoroPackagingBySpedizione(filiale, spedizione);
            Task<Integer> numeroOrdiniPackagingDaCompletare = shipmentService.getCountOrdiniDiLavoroPackagingDaCompletareBySpedizione(filiale, spedizione);
            Task<Integer> numeroPacchiGenerati = shipmentService.getCountPacchiGeneratiBySpedizione(filiale, spedizione);
            Task<Integer> numeroPacchiDaSpedire = shipmentService.getCountPacchiDaSpedireBySpedizione(filiale, spedizione);
            Task<Integer> numeroOrdiniTrasporto = shipmentService.getCountOrdiniDiLavoroTrasportoBySpedizione(filiale, spedizione);
            Task<Integer> numeroOrdiniTrasportoDaCompletare = shipmentService.getCountOrdiniDiLavoroTrasportoDaCompletareBySpedizione(filiale, spedizione);

            SpedizioneModel spedizioneModel = new SpedizioneModel(
                    String.valueOf(spedizione.getId()),
                    spedizione.getOrdineCliente().getCliente().getIntestazione(),
                    spedizione.getStato(),
                    spedizione.getDataCreazione(),
                    spedizione.getDataInizioLavorazione(),
                    spedizione.getDataFineLavorazione(),
                    spedizione.getOrganizzatore().getUsername(),
                    spedizione.getTrackingNumber(),
                    numeroOrdiniPackaging,
                    numeroOrdiniPackagingDaCompletare,
                    numeroPacchiGenerati,
                    numeroPacchiDaSpedire,
                    numeroOrdiniTrasporto,
                    numeroOrdiniTrasportoDaCompletare,
                    spedizione.getOrdineCliente(),
                    spedizione,
                    filiale
            );

            SpedizioneController spedizioneController = new SpedizioneController(spedizioneModel);
            DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
            dashboardController.changeView("SPEDIZIONE", "/views/shipmentdomain/spedizione-view.fxml", c -> spedizioneController);
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero della spedizione");
        }
    }

    public void paginaSpedizioniPronta(boolean justMe) throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();

        Task<List<SpedizioneDTO>> listaSpedizioniTask;

        try {
            switch (utenteDTO.getProfilo()) {
                case "OperatoreCorriere":
                    listaSpedizioniTask = shipmentService.getListaSpedizioni(((OperatoreCorriereDTO) utenteDTO).getGruppoCorriere().getFiliale());
                    break;
                case "Operatore":
                    if (justMe)
                        listaSpedizioniTask = shipmentService.getListaSpedizioni(((OperatoreFilialeDTO) utenteDTO));
                    else
                        listaSpedizioniTask = shipmentService.getListaSpedizioni(((OperatoreFilialeDTO) utenteDTO).getFiliale());
                    break;
                default:
                    listaSpedizioniTask = shipmentService.getListaSpedizioni();
            }

            listaSpedizioniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));

            listaSpedizioniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero delle spedizioni");
                });
            });

            listaSpedizioniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<SpedizioneDTO> listaSpedizioni = listaSpedizioniTask.getValue();
                    spedizioniController.setListaSpedizioni(listaSpedizioni);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(listaSpedizioniTask);
            executorService.shutdown();
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero delle spedizioni");
        }
    }

    public void setupSpedizioniController(SpedizioniController pSpedizioniController) {
        this.spedizioniController = pSpedizioniController;
    }

    public void assegnaOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO, OperatoreCorriereDTO utente) throws SpedizioniException {
        try {
            OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO = shipmentService.getOrdineDiLavoroPackaging(magazzinoDTO, spedizioneDTO);
            ordineDiLavoroPackagingDTO.setGruppoCorriere(utente.getGruppoCorriere());
            ordineDiLavoroPackagingDTO.setOperatoreCorriere(utente);
            shipmentService.getUpdateOrdinePackaging(ordineDiLavoroPackagingDTO);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nell'assegnazione dell'ordine di lavoro di packaging");
        }
    }

    public void assegnaOdlTrasportoClicked(SpedizioneDTO spedizione, FilialeDTO filiale, OperatoreCorriereDTO utente) throws SpedizioniException {
        try {
            OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizioneDTO = shipmentService.getOrdineDiLavoroTrasporto(spedizione, filiale);
            ordineDiLavoroSpedizioneDTO.setGruppoCorriere(utente.getGruppoCorriere());
            ordineDiLavoroSpedizioneDTO.setOperatoreCorriere(utente);
            shipmentService.updateOrdineSpedizione(ordineDiLavoroSpedizioneDTO);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nell'assegnazione dell'ordine di lavoro di trasporto");
        }
    }

    public void iniziaLavorazioneOdlTrasportoClicked(SpedizioneDTO spedizione, FilialeDTO filiale, MezzoDiTrasportoDTO mezzo) throws SpedizioniException {
        try {
            OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizioneDTO = shipmentService.getOrdineDiLavoroTrasporto(spedizione, filiale);
            ordineDiLavoroSpedizioneDTO.setDataInizioLavorazione(LocalDate.now());
            ordineDiLavoroSpedizioneDTO.setMezzoDiTrasporto(mezzo);
            shipmentService.updateOrdineSpedizione(ordineDiLavoroSpedizioneDTO);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nell'assegnazione dell'ordine di lavoro di trasporto");
        }
    }

    public void iniziaLavorazioneOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO) throws SpedizioniException {
        try {
            OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO = shipmentService.getOrdineDiLavoroPackaging(magazzinoDTO, spedizioneDTO);
            ordineDiLavoroPackagingDTO.setDataInizioLavorazione(LocalDate.now());
            shipmentService.getUpdateOrdinePackaging(ordineDiLavoroPackagingDTO);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nell'assegnazione dell'ordine di lavoro di packaging");
        }
    }

    public void concludiLavorazioneOdlTrasportoClicked(OrdineSpedizioneModel model, String text) throws SpedizioniException {
        try {
            OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizioneDTO = shipmentService.getOrdineDiLavoroTrasporto(model.getSpedizione(), model.getFiliale());
            ordineDiLavoroSpedizioneDTO.setDataFineLavorazione(LocalDate.now());
            ordineDiLavoroSpedizioneDTO.setNoteAggiuntiveOperatore(text);
            shipmentService.updateOrdineSpedizione(ordineDiLavoroSpedizioneDTO);
            shipmentService.checkSeTuttiConclusiEAggiornaSpedizione(ordineDiLavoroSpedizioneDTO.getSpedizione());
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nella conclusione dell'ordine di lavoro di trasporto");
        }
    }

    public void concludiLavorazioneOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO, String noteCorriere) throws SpedizioniException {
        try {
            OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO = shipmentService.getOrdineDiLavoroPackaging(magazzinoDTO, spedizioneDTO);

            Task<Void> task = shipmentService.setOdlPackagingCompletato(ordineDiLavoroPackagingDTO, noteCorriere);
            task.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            task.setOnSucceeded(event -> {
                //refresh
                try {
                    OrdineDiLavoroPackagingDTO refreshed = shipmentService.getOrdineDiLavoroPackaging(magazzinoDTO, spedizioneDTO);
                    shipmentService.generaPacchi(refreshed);
                } catch (ServiceException e) {
                    manageError("Errore generazione pacchi", e.getMessage());
                } finally {
                    taskCompleted();
                }
            });
            task.setOnFailed(event -> {
                taskCompleted();
                manageError("Errore", "Errore nella conclusione dell'ordine di lavoro di packaging");
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(task);
            executorService.shutdown();
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nella conclusione dell'ordine di lavoro di packaging");
        }
    }

    public Task<Void> generaOdlPackagingClicked(OrdineClienteDTO ordineCliente) throws SpedizioniException {
        try {
            SpedizioneDTO spedizione = shipmentService.getSpedizione(ordineCliente);
            Task<Void> task = shipmentService.creaOdlPackaging(spedizione);
            return task;
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nella generazione degli ordine di lavoro di packaging.");
        }
    }

    public void visualizzaOrdiniTrasportoClicked(SpedizioneDTO spedizione, FilialeDTO filiale) throws SpedizioniException {
        try {
            Session session = Session.getInstance();
            UtenteDTO utente = session.getUserDto().getValue();
            OrdineDiLavoroSpedizioneDTO ordine = shipmentService.getOrdineDiLavoroTrasporto(spedizione, filiale);

            visualizzaOrdineSpedizioneClicked(ordine);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di trasporto");
        }
    }


    public void visualizzaOrdiniPackagingClicked(OrdineClienteDTO ordineCliente) throws SpedizioniException {
        try {
            Session session = Session.getInstance();
            UtenteDTO utente = session.getUserDto().getValue();
            Task<List<OrdineDiLavoroPackagingDTO>> ordiniTask = shipmentService.getListaOrdiniDiLavoroPackaging(utente, ordineCliente);

            ordiniTask.setOnRunning(event -> Platform.runLater(() -> taskRunning()));
            ordiniTask.setOnFailed(event -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero degli ordini di lavoro di packaging");
                });
            });
            ordiniTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroPackagingDTO> ordini = ordiniTask.getValue();
                    dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
                    OrdiniPackagingController odlpack = new OrdiniPackagingController(dashboardStage);
                    dashboardController.changeView("ORDINI PACKAGING", "/views/shipmentdomain/ordini-packaging-view.fxml", c -> odlpack);
                    odlpack.setListaOrdini(ordini);
                    taskCompleted();
                });
            });

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(ordiniTask);
            executorService.shutdown();
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void visualizzaPacchiClicked(OrdineDiLavoroPackagingDTO ordinePackagingDTO) throws SpedizioniException {
        dashboardController.changeView("PACCHI", "/views/shipmentdomain/pacchi-view.fxml", c -> new PacchiController(dashboardStage, ordinePackagingDTO));
    }

    public void visualizzaPacchiClicked(SpedizioneDTO spedizione) throws SpedizioniException {
        try {
            SpedizioneDTO spedizioneFilled = shipmentService.refreshSpedizione(spedizione);
            dashboardController.changeView("PACCHI", "/views/shipmentdomain/pacchi-view.fxml", c -> new PacchiController(dashboardStage, spedizioneFilled));
        } catch (ServiceException e) {
            manageError("Errore", e.getMessage());
        }
    }

    public Task<Void> generaOdlTrasportoClicked(OrdineClienteDTO ordineCliente) throws SpedizioniException {
        try {
            SpedizioneDTO spedizione = shipmentService.getSpedizione(ordineCliente);
            Task<Void> task = shipmentService.creaOdlTrasporto(spedizione);
            return task;
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nella generazione degli ordine di lavoro di trasporto.");
        }
    }

    public void ordinePackagingRicaricami(OrdinePackagingModel ordinePackagingModel) throws SpedizioniException {
        OrdineDiLavoroPackagingDTO ordine;

        try {
            ordine = shipmentService.getOrdineDiLavoroPackaging(ordinePackagingModel.getOrdine().getMagazzino(), ordinePackagingModel.getOrdine().getSpedizione());
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero dell'ordine di lavoro di packaging");
        }

        visualizzaOrdinePackagingClicked(ordine);
    }

    public void ordineSpedizioneRicaricami(OrdineSpedizioneModel ordineSpedizioneModel) throws SpedizioniException {
        OrdineDiLavoroSpedizioneDTO ordine = null;
        try {
            ordine = shipmentService.getOrdineDiLavoroTrasporto(ordineSpedizioneModel.getSpedizione(), ordineSpedizioneModel.getFiliale());
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero dell'ordine di lavoro di spedizione");
        }

        visualizzaOrdineSpedizioneClicked(ordine);
    }


    public void manageError(String title, String message) {
        Platform.runLater(() -> dashboardController.showDialog("error", title, message));
    }

    public void pacchiPaginaPronta(PacchiController controller, SpedizioneDTO spedizione) throws SpedizioniException {
        try {
            Session session = Session.getInstance();
            UtenteDTO utente = session.getUserDto().getValue();
            FilialeDTO filiale = utente.getProfilo().equals("Manager") ? null : (utente.getProfilo().equals("Operatore") ? ((OperatoreFilialeDTO) utente).getFiliale() : ((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());

            List<PaccoDTO> pacchi = shipmentService.getListaPacchi(filiale, spedizione);
            controller.setGridPacchi(pacchi);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero dei pacchi");
        }
    }

    public void ordinePackagingPronta(OrdinePackagingController ordinePackagingController, OrdineDiLavoroPackagingDTO ordine) throws SpedizioniException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            Session session = Session.getInstance();
            UtenteDTO utente = session.getUserDto().getValue();
            FilialeDTO filiale = utente.getProfilo().equals("Manager") ? null : (utente.getProfilo().equals("Operatore") ? ((OperatoreFilialeDTO) utente).getFiliale() : ((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());

            Task<List<OrdineDiLavoroPackagingDetailDTO>> task = shipmentService.getOrdineDiLavoroPackagingDetailsTask(filiale, ordine);
            task.setOnRunning(e -> Platform.runLater(() -> taskRunning()));
            task.setOnSucceeded(e -> {
                Platform.runLater(() -> {
                    List<OrdineDiLavoroPackagingDetailDTO> details = task.getValue();
                    ordinePackagingController.setDettGridItems(details);
                    taskCompleted();
                });
            });

            task.setOnFailed(e -> {
                Platform.runLater(() -> {
                    taskCompleted();
                    manageError("Errore", "Errore nel recupero dei dettagli dell'ordine di lavoro di packaging");
                });
            });

            executorService.submit(task);
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero dei dettagli dell'ordine di lavoro di packaging");
        } finally {
            executorService.shutdown();
        }
    }

    public void ordineSpedizionePaginaPronta(OrdineSpedizioneController controller, FilialeDTO filiale, SpedizioneDTO spedizione) throws SpedizioniException {
        try {
            List<PaccoDTO> pacchi = shipmentService.getListaPacchi(filiale, spedizione);
            controller.setGridPacchi(pacchi);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero dei pacchi");
        }
    }

    public List<MezzoDiTrasportoDTO> getMezziDiTrasportoLiberiOggi(GruppoCorriereDTO gruppoCorriere) throws SpedizioniException {
        try {
            return shipmentService.getMezziDiTrasportoLiberi(gruppoCorriere, LocalDate.now());
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero dei mezzi di trasporto liberi");
        }
    }

}
