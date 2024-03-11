package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import javafx.stage.Stage;
import lombok.Setter;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.OrdiniPackagingController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.SpedizioneController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.SpedizioniController;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.customerdomain.SpedizioneModel;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.util.List;

public class CorriereOrchestrator extends Orchestrator {

    private static CorriereOrchestrator instance;

    private final ShipmentService shipmentService;

    @Setter
    private OrdiniPackagingController ordiniPackagingController;
    private SpedizioniController spedizioniController;

    //@Setter
    //private SpedizioniController spedizioniController;

    /**
     * Costruttore della classe Orchestrator, è protetta rendendo la classe non instanziabile ma derivabile
     *
     * @param dashboardStage
     */
    protected CorriereOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
        shipmentService = new ShipmentService();
    }

    public static CorriereOrchestrator getCorriereOrchestrator(Stage dashboardStage) {
        if (instance == null) {
            instance = new CorriereOrchestrator(dashboardStage);
        }
        return instance;
    }


    public void paginaOrdiniPackagingPronta() {
        Session session = Session.getInstance();
        UtenteDTO utenteDTO = session.getUserDto().getValue();

        List<OrdineDiLavoroPackagingDTO> listaOrdini;

        try {
            switch (utenteDTO.getProfilo()) {
                case "OperatoreCorriere":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreCorriereDTO) utenteDTO).getGruppoCorriere().getFiliale());

                    break;
                case "Operatore":
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(((OperatoreFilialeDTO) utenteDTO).getFiliale());

                    break;
                default:
                    listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging();
            }

            ordiniPackagingController.setListaOrdini(listaOrdini);
        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroTuttiClicked() {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaOrdiniDiLavoroPackaging(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
            ordiniPackagingController.setListaOrdini(listaOrdini);


        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void filtroGruppoCorriereClicked() {
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

    public void filtroPresiInCaricoClicked() {
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

    public void filtroDaPrendereInCaricoClicked() {
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

    public void visualizzaOrdinePackagingClicked(OrdineDiLavoroPackagingDTO ordine) {
    }

    public void prendiInCaricoOrdineDiLavoroPackagingClicked(OrdineDiLavoroPackagingDTO ordine) {
    }

    public void filtroTuttiSpedizioniClicked() throws SpedizioniException {
        //todo
    }

    public void filtroSpedizioniEmesseDaMeClicked() throws SpedizioniException {
        //todo
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

}
