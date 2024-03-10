package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import javafx.stage.Stage;
import lombok.Setter;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.OrdiniPackagingController;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.util.List;

public class CorriereOrchestrator extends Orchestrator {

    private static CorriereOrchestrator instance;

    private final ShipmentService shipmentService;

    @Setter
    private OrdiniPackagingController ordiniPackagingController;

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
        if(instance == null) {
            instance = new CorriereOrchestrator(dashboardStage);
        }
        return instance;
    }


    public void pagineOrdiniPackagingPronta() {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<OrdineDiLavoroPackagingDTO> listaOrdini = shipmentService.getListaClienti(operatoreCorriereDTO.getGruppoCorriere());
            ordiniPackagingController.setListaOrdini(listaOrdini);
            
        }
        catch (ServiceException e) {
            //TODO: gestire errore
        }
    }
}
