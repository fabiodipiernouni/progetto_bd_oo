package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.*;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.customerdomain.SpedizioneModel;
import org.unina.uninadelivery.presentation.model.shipmentdomain.OrdinePackagingModel;
import org.unina.uninadelivery.presentation.model.shipmentdomain.OrdineSpedizioneModel;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

class OdlOrchestrator extends Orchestrator implements IGenericOdlOrchestrator, IOdlOrchestratorOrdiniPackaging, IOdlOrchestratorSpedizioni, IOdlOrchestratrOrdiniSpedizione {

    private final ShipmentService shipmentService;

    private OrdiniPackagingController ordiniPackagingController;
    private SpedizioniController spedizioniController;
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

    protected OdlOrchestrator(Stage dashboardStage, OrdiniPackagingController ordiniPackagingController) {
        super(dashboardStage);
        shipmentService = new ShipmentService();
        this.ordiniPackagingController = ordiniPackagingController;
    }

    protected OdlOrchestrator(Stage dashboardStage, SpedizioniController spedizioniController) {
        super(dashboardStage);
        shipmentService = new ShipmentService();
        this.spedizioniController = spedizioniController;
    }

    public OdlOrchestrator(Stage dashboardStage, OrdiniSpedizioneController ordiniSpedizioneController) {
        super(dashboardStage);
        shipmentService = new ShipmentService();
        this.ordiniSpedizioneController = ordiniSpedizioneController;
    }

    /**** ORDINI DI LAVORO PACKAGING ****/

    public void paginaOrdiniPackagingPronta() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();

        List<OrdineDiLavoroPackagingDTO> listaOrdini = Collections.emptyList();

        try {
            switch (utenteDTO.getProfilo()) {
                case "OperatoreCorriere":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreCorriereDTO) utenteDTO).getGruppoCorriere().getFiliale());

                    break;
                case "Operatore":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreFilialeDTO) utenteDTO).getFiliale());

                    break;
                case "Manager":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging();
            }

            ordiniPackagingController.setListaOrdini(listaOrdini);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void filtroOrdiniPackagingTuttiClicked() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = Collections.emptyList();

            if (utente.getProfilo().equals("OperatoreCorriere"))
                listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());
            else if (utente.getProfilo().equals("Operatore"))
                listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreFilialeDTO) utente).getFiliale());


            ordiniPackagingController.setListaOrdini(listaOrdini);


        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void filtroOrdiniPackagingGruppoCorriereClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO.getGruppoCorriere());
            ordiniPackagingController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }


    public void filtroOrdiniPackagingPresiInCaricoClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO);
            ordiniPackagingController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void filtroOrdiniPackagingDaPrendereInCaricoClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO.getGruppoCorriere().getFiliale(), "DaAssegnare");
            ordiniPackagingController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void filtroOrdiniPackagingEmessiDaMeClicked() throws SpedizioniException {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreFilialeDTO);
            ordiniPackagingController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }

    public void visualizzaOrdinePackagingClicked(OrdineDiLavoroPackagingDTO ordine) {
        OrdinePackagingModel model = new OrdinePackagingModel(
                ordine.getId(),
                ordine.getDataCreazione(),
                ordine.getSpedizione().getOrdineCliente().getCliente().getRagioneSociale(),
                ordine.getDataInizioLavorazione(),
                ordine.getDataFineLavorazione(),
                ordine.getGruppoCorriere().getNome(),
                ordine.getGruppoCorriere().getFiliale().getNome(),
                ordine.getSpedizione().getOrdineCliente().getIndirizzoSpedizione().getIndirizzo_1(),
                ordine.getSpedizione().getOrdineCliente().getIndirizzoSpedizione().getIndirizzo_2(),
                ordine.getStato(),
                ordine.getNoteAggiuntiveCliente(),
                ordine.getNoteAggiuntiveOperatore(),
                ordine.getMagazzino(),
                ordine.getSpedizione()
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
                ordine.getSpedizione().getOrdineCliente().getCliente().getRagioneSociale(),
                ordine.getDataInizioLavorazione(),
                ordine.getDataFineLavorazione(),
                ordine.getGruppoCorriere().getNome(),
                ordine.getGruppoCorriere().getFiliale().getNome(),
                ordine.getSpedizione().getOrdineCliente().getIndirizzoSpedizione().getIndirizzo_1(),
                ordine.getSpedizione().getOrdineCliente().getIndirizzoSpedizione().getIndirizzo_2(),
                ordine.getStato(),
                "",
                ordine.getNoteAggiuntiveOperatore(),
                ordine.getSpedizione()
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
            shipmentService.updateOrdinePackaging(ordine);

            //TODO: NOTIFICA L'Utente del successo

            //aggiorno la lista degli ordini
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
            ordiniPackagingController.setListaOrdini(listaOrdini);
            ordiniPackagingController.resettaFiltri();

        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel prendere in carico l'ordine di lavoro di packaging");
        }
    }

    /**** ORDINI DI LAVORO SPEDIZIONE ****/

    public void paginaOrdiniSpedizionePronta() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();

        List<OrdineDiLavoroSpedizioneDTO> listaOrdini = Collections.emptyList();

        try {
            switch (utenteDTO.getProfilo()) {
                case "OperatoreCorriere":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(((OperatoreCorriereDTO) utenteDTO).getGruppoCorriere().getFiliale());

                    break;
                case "Operatore":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(((OperatoreFilialeDTO) utenteDTO).getFiliale());

                    break;
                case "Manager":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione();
            }

            ordiniSpedizioneController.setListaOrdini(listaOrdini);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizioneTuttiClicked() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = Collections.emptyList();

            if (utente.getProfilo().equals("OperatoreCorriere"))
                listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());
            else if (utente.getProfilo().equals("Operatore"))
                listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(((OperatoreFilialeDTO) utente).getFiliale());

            ordiniSpedizioneController.setListaOrdini(listaOrdini);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizioneGruppoCorriereClicked() throws SpedizioniException{
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO.getGruppoCorriere());
            ordiniSpedizioneController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizionePresiInCaricoClicked() throws SpedizioniException{
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO);
            ordiniSpedizioneController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizioneDaPrendereInCaricoClicked() throws SpedizioniException{
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO.getGruppoCorriere().getFiliale(), "DaAssegnare");
            ordiniSpedizioneController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void filtroOrdiniSpedizioneEmessiDaMeClicked() throws SpedizioniException{
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreFilialeDTO);
            ordiniSpedizioneController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di spedizione");
        }
    }

    public void prendiInCaricoOrdineSpedizioneClicked(OrdineDiLavoroSpedizioneDTO ordine) throws SpedizioniException{
        Session session = Session.getInstance();
        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        ordine.setGruppoCorriere(operatoreCorriereDTO.getGruppoCorriere());
        ordine.setOperatoreCorriere(operatoreCorriereDTO);

        try {
            shipmentService.updateOrdineSpedizione(ordine);

            //TODO: NOTIFICA L'Utente del successo

            //aggiorno la lista degli ordini
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
            ordiniSpedizioneController.setListaOrdini(listaOrdini);
            ordiniSpedizioneController.resettaFiltri();
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel prendere in carico l'ordine di lavoro di spedizione");
        }
    }

    public void visualizzaSpedizioneClicked(SpedizioneDTO spedizione) throws SpedizioniException {
        try {
            int numeroOrdiniPackaging = shipmentService.getCountOrdiniDiLavoroPackagingBySpedizione(spedizione);
            int numeroOrdiniPackagingDaCompletare = shipmentService.getCountOrdiniDiLavoroPackagingDaCompletareBySpedizione(spedizione);
            int numeroPacchiGenerati = shipmentService.getCountPacchiGeneratiBySpedizione(spedizione);
            int numeroPacchiDaSpedire = shipmentService.getCountPacchiDaSpedireBySpedizione(spedizione);
            int numeroOrdiniTrasporto = shipmentService.getCountOrdiniDiLavoroTrasportoBySpedizione(spedizione);
            int numeroOrdiniTrasportoDaCompletare = shipmentService.getCountOrdiniDiLavoroTrasportoDaCompletareBySpedizione(spedizione);

            SpedizioneModel spedizioneModel = new SpedizioneModel(
                    String.valueOf(spedizione.getId()),
                    spedizione.getOrdineCliente().getCliente().getRagioneSociale(),
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
                    spedizione.getOrdineCliente()
            );

            SpedizioneController spedizioneController = new SpedizioneController(spedizioneModel);
            DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
            dashboardController.changeView("ORDINE", "/views/shipmentdomain/spedizione-view.fxml", c -> spedizioneController);
        } catch (Exception e) {
            throw new SpedizioniException("Errore nel recupero della spedizione");
        }
    }

    public void paginaSpedizioniPronta() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();

        List<SpedizioneDTO> listaSpedizioni;

        try {
            switch (utenteDTO.getProfilo()) {
                case "OperatoreCorriere":
                    listaSpedizioni = shipmentService.getListaSpedizioni(((OperatoreCorriereDTO) utenteDTO).getGruppoCorriere().getFiliale());

                    break;
                case "Operatore":
                    listaSpedizioni = shipmentService.getListaSpedizioni(((OperatoreFilialeDTO) utenteDTO).getFiliale());

                    break;
                default:
                    listaSpedizioni = shipmentService.getListaSpedizioni();
            }


            spedizioniController.setListaSpedizioni(listaSpedizioni);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero delle spedizioni");
        }
    }

    public void filtroTutteSpedizioniClicked() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        try {
            List<SpedizioneDTO> spedizioni = Collections.emptyList();

            if (utente.getProfilo().equals("OperatoreCorriere"))
                spedizioni = shipmentService.getListaSpedizioni(((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());
            else if (utente.getProfilo().equals("Operatore"))
                spedizioni = shipmentService.getListaSpedizioni(((OperatoreFilialeDTO) utente).getFiliale());
            else
                spedizioni = shipmentService.getListaSpedizioni(null);

            spedizioniController.setListaSpedizioni(spedizioni);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero delle spedizioni");
        }
    }

    public void filtroSpedizioniEmesseDaMeClicked() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        try {
            List<SpedizioneDTO> spedizioni = shipmentService.getListaSpedizioniPerOrganizzatore((OperatoreFilialeDTO) utente);
            spedizioniController.setListaSpedizioni(spedizioni);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new SpedizioniException("Errore nel recupero delle spedizioni");
        }
    }

    public void assegnaOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO, OperatoreCorriereDTO utente) throws SpedizioniException {
        try {
            OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO = shipmentService.getOrdineDiLavoroPackaging(magazzinoDTO, spedizioneDTO);
            ordineDiLavoroPackagingDTO.setGruppoCorriere(utente.getGruppoCorriere());
            ordineDiLavoroPackagingDTO.setOperatoreCorriere(utente);
            shipmentService.updateOrdinePackaging(ordineDiLavoroPackagingDTO);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nell'assegnazione dell'ordine di lavoro di packaging");
        }
    }

    public void iniziaLavorazioneOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO) throws SpedizioniException {
        try {
            OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO = shipmentService.getOrdineDiLavoroPackaging(magazzinoDTO, spedizioneDTO);
            ordineDiLavoroPackagingDTO.setDataInizioLavorazione(LocalDate.now());
            shipmentService.updateOrdinePackaging(ordineDiLavoroPackagingDTO);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nell'assegnazione dell'ordine di lavoro di packaging");
        }
    }

    public void concludiLavorazioneOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO, String noteCorriere) throws SpedizioniException {
        try {
            OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO = shipmentService.getOrdineDiLavoroPackaging(magazzinoDTO, spedizioneDTO);
            ordineDiLavoroPackagingDTO.setDataFineLavorazione(LocalDate.now());
            ordineDiLavoroPackagingDTO.setNoteAggiuntiveOperatore(noteCorriere);
            shipmentService.updateOrdinePackaging(ordineDiLavoroPackagingDTO);
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

    public void visualizzaOrdiniPackagingClicked(OrdineClienteDTO ordineCliente) throws SpedizioniException {
        try {
            List<OrdineDiLavoroPackagingDTO> ordini = shipmentService.getListaOrdiniDiLavoroPackaging(ordineCliente);
            ordiniPackagingController.setListaOrdini(ordini);
            dashboardController.changeView("ORDINI PACKAGING", "/views/shipmentdomain/ordini-packaging-view.fxml", c -> ordiniPackagingController);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero degli ordini di lavoro di packaging");
        }
    }
}
