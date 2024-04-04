package org.unina.uninadelivery.presentation.orchestrator.appdomain;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.customerdomain.CustomerService;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.presentation.exception.HomeException;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

public class HomeOrchestrator extends Orchestrator {

    final CustomerService customerService;
    private final ShipmentService shipmentService;

    /**
     * Costruttore della classe Orchestrator, è protetta rendendo la classe non instanziabile ma derivabile
     *
     * @param dashboardStage
     */
    public HomeOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
        customerService = new CustomerService();
        shipmentService = new ShipmentService();
    }

    public Task<Integer> homeOpFilialeSetupOrdiniDaLavorare(FilialeDTO filiale) throws HomeException {
        try {
            return customerService.getCountOrdiniDaLavorareTask(filiale);
        } catch (ServiceException e) {
            throw new HomeException("Errore durante il conteggio degli ordini da lavorare. " + e.getMessage());
        }
    }

    public void manageError(String title, HomeException e) {
        dashboardController.showDialog("error", title, e.getMessage());
    }

    public Task<Integer> homeOpFilialeSetupSpedizioniDaLavorare(OperatoreFilialeDTO operatoreFilialeDTO) throws HomeException {
        try {
            return shipmentService.getCountSpedizioniDaLavorare(operatoreFilialeDTO);
        } catch (ServiceException e) {
            throw new HomeException("Errore durante il conteggio degli ordini da lavorare. " + e.getMessage());
        }
    }

    public Task<Integer> homeOpFilialeSetupOrdiniDiLavoroPackagingConclusiAttesaTrasporto(FilialeDTO filiale) throws HomeException {
        try {
            return shipmentService.getCountOrdiniDiLavoroPackagingConclusiAttesaTrasporto(filiale);
        } catch (ServiceException e) {
            throw new HomeException("Errore durante il conteggio degli ordini da lavorare. " + e.getMessage());
        }
    }

    public Task<Integer> homeOpFilialeSetupOrdiniDiLavoroSpedizioneDaTerminare(FilialeDTO filiale) throws HomeException {
        try {
            return shipmentService.getCountOrdiniDiLavoroSpedizioneDaTerminare(filiale);
        } catch (ServiceException e) {
            throw new HomeException("Errore durante il conteggio degli ordini da lavorare. " + e.getMessage());
        }
    }

    public Task<Integer> homeOpCorriereSetupOrdiniDiLavoroPackagingDaPrendereInCarico(FilialeDTO filiale) throws HomeException {
        try {
            return shipmentService.getCountOrdiniDiLavoroPackagingDaPrendereInCarico(filiale);
        } catch (ServiceException e) {
            throw new HomeException("Errore durante il conteggio degli ordini da lavorare. " + e.getMessage());
        }
    }

    public Task<Integer> homeOpCorriereSetupOrdiniDiLavoroPackagingDaTerminare(FilialeDTO filiale) throws HomeException {
        try {
            return shipmentService.getCountOrdiniDiLavoroPackagingDaTerminare(filiale);
        } catch (ServiceException e) {
            throw new HomeException("Errore durante il conteggio degli ordini da lavorare. " + e.getMessage());
        }
    }

    public Task<Integer> homeOpCorriereSetupOrdiniDiLavoroTrasportoDaPrendereInCarico(FilialeDTO filiale) throws HomeException {
        try {
            return shipmentService.getCountOrdiniDiLavoroTrasportoDaPrendereInCarico(filiale);
        } catch (ServiceException e) {
            throw new HomeException("Errore durante il conteggio degli ordini da lavorare. " + e.getMessage());
        }
    }

    public Task<Integer> homeOpCorriereSetupOrdiniDiLavoroSpedizioneDaTerminare(FilialeDTO filiale) throws HomeException {
        try {
            return shipmentService.getCountOrdiniDiLavoroSpedizioneDaTerminare(filiale);
        } catch (ServiceException e) {
            throw new HomeException("Errore durante il conteggio degli ordini da lavorare");
        }
    }
}
