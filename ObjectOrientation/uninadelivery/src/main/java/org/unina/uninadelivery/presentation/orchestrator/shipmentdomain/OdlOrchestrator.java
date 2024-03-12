package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import javafx.stage.Stage;
import lombok.Setter;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.OrdiniPackagingController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.OrdiniSpedizioneController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.SpedizioneController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.SpedizioniController;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.customerdomain.SpedizioneModel;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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
        if(instance == null) {
            instance = new OdlOrchestrator(dashboardStage);
        }
        return instance;
    }


    /**** ORDINI DI LAVORO PACKAGING ****/

    public void paginaOrdiniPackagingPronta() {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();

        List<OrdineDiLavoroPackagingDTO> listaOrdini = Collections.emptyList();

        try {
            switch (utenteDTO.getProfilo()) {
                case "OperatoreCorriere":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging( ((OperatoreCorriereDTO) utenteDTO).getGruppoCorriere().getFiliale());

                    break;
                case "Operatore":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging( ((OperatoreFilialeDTO) utenteDTO).getFiliale());

                    break;
                case "Manager":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging();
            }

            ordiniPackagingController.setListaOrdini(listaOrdini);
        }
        catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroOrdiniPackagingTuttiClicked() {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = Collections.emptyList();

            if(utente.getProfilo().equals("OperatoreCorriere"))
                listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging( ((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());
            else
            if(utente.getProfilo().equals("Operatore"))
                listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging( ((OperatoreFilialeDTO) utente).getFiliale());


            ordiniPackagingController.setListaOrdini(listaOrdini);


        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroOrdiniPackagingGruppoCorriereClicked() {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO.getGruppoCorriere());
            ordiniPackagingController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }


    public void filtroOrdiniPackagingPresiInCaricoClicked() {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO);
            ordiniPackagingController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroOrdiniPackagingDaPrendereInCaricoClicked() {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO.getGruppoCorriere().getFiliale(), "DaAssegnare");
            ordiniPackagingController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroOrdiniPackagingEmessiDaMeClicked() {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreFilialeDTO);
            ordiniPackagingController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void visualizzaOrdinePackagingClicked(OrdineDiLavoroPackagingDTO ordine) {
        //TODO
    }

    public void prendiInCaricoOrdinePackagingClicked(OrdineDiLavoroPackagingDTO ordine) {

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
            //TODO: gestire errore
            e.printStackTrace();

        }
    }

    /**** ORDINI DI LAVORO SPEDIZIONE ****/

    public void paginaOrdiniSpedizionePronta() {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();

        List<OrdineDiLavoroSpedizioneDTO> listaOrdini = Collections.emptyList();

        try {
            switch (utenteDTO.getProfilo()) {
                case "OperatoreCorriere":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione( ((OperatoreCorriereDTO) utenteDTO).getGruppoCorriere().getFiliale());

                    break;
                case "Operatore":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione( ((OperatoreFilialeDTO) utenteDTO).getFiliale());

                    break;
                case "Manager":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione();
            }

            ordiniSpedizioneController.setListaOrdini(listaOrdini);
        }
        catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroOrdiniSpedizioneTuttiClicked() {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = Collections.emptyList();

            if(utente.getProfilo().equals("OperatoreCorriere"))
                listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione( ((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());
            else
            if(utente.getProfilo().equals("Operatore"))
                listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione( ((OperatoreFilialeDTO) utente).getFiliale());


            ordiniSpedizioneController.setListaOrdini(listaOrdini);


        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroOrdiniSpedizioneGruppoCorriereClicked() {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO.getGruppoCorriere());
            ordiniSpedizioneController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroOrdiniSpedizionePresiInCaricoClicked() {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO);
            ordiniSpedizioneController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroOrdiniSpedizioneDaPrendereInCaricoClicked() {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreCorriereDTO.getGruppoCorriere().getFiliale(), "DaAssegnare");
            ordiniSpedizioneController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroOrdiniSpedizioneEmessiDaMeClicked() {
        Session session = Session.getInstance();

        OperatoreFilialeDTO operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroSpedizioneDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroSpedizione(operatoreFilialeDTO);
            ordiniSpedizioneController.setListaOrdini(listaOrdini);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void visualizzaOrdineSpedizioneClicked(OrdineDiLavoroSpedizioneDTO ordine) {
        //TODO
    }

    public void prendiInCaricoOrdineSpedizioneClicked(OrdineDiLavoroSpedizioneDTO ordine) {
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
            //TODO: gestire errore
            e.printStackTrace();

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
                    numeroOrdiniTrasportoDaCompletare
            );

            SpedizioneController spedizioneController = new SpedizioneController(spedizioneModel);
            DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
            dashboardController.changeView("ORDINE", "/views/shipmentdomain/spedizione-view.fxml", c-> spedizioneController);
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

    public void setSpedizioniController(SpedizioniController spedizioniController) {
        this.spedizioniController = spedizioniController;
    }

    public void filtroTutteSpedizioniClicked() throws SpedizioniException {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        try {
            List<SpedizioneDTO> spedizioni = Collections.emptyList();

            if(utente.getProfilo().equals("OperatoreCorriere"))
                spedizioni = shipmentService.getListaSpedizioni(((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());
            else if(utente.getProfilo().equals("Operatore"))
                spedizioni = shipmentService.getListaSpedizioni( ((OperatoreFilialeDTO) utente).getFiliale());
            else
                spedizioni = shipmentService.getListaSpedizioni(null);

            spedizioniController.setListaSpedizioni(spedizioni);
        } catch (ServiceException e) {
            throw new SpedizioniException("Errore nel recupero delle spedizioni");
        }
    }

    public void filtroSpedizioniEmesseDaMeClicked() throws SpedizioniException{
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
        }
        catch (ServiceException e) {
            throw new SpedizioniException("Errore nell'assegnazione dell'ordine di lavoro di packaging");
        }
    }

    public void iniziaLavorazioneOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO) throws SpedizioniException {
        try {
            OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO = shipmentService.getOrdineDiLavoroPackaging(magazzinoDTO, spedizioneDTO);
            ordineDiLavoroPackagingDTO.setDataInizioLavorazione(LocalDate.now());
            shipmentService.updateOrdinePackaging(ordineDiLavoroPackagingDTO);
        }
        catch (ServiceException e) {
            throw new SpedizioniException("Errore nell'assegnazione dell'ordine di lavoro di packaging");
        }
    }

    public void concludiLavorazioneOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO, String noteCorriere) throws SpedizioniException
    {
        try {
            OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO = shipmentService.getOrdineDiLavoroPackaging(magazzinoDTO, spedizioneDTO);
            ordineDiLavoroPackagingDTO.setDataFineLavorazione(LocalDate.now());
            ordineDiLavoroPackagingDTO.setNoteAggiuntiveOperatore(noteCorriere);
            shipmentService.updateOrdinePackaging(ordineDiLavoroPackagingDTO);
        }
        catch (ServiceException e) {
            throw new SpedizioniException("Errore nella conclusione dell'ordine di lavoro di packaging");
        }
    }
}
