package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import javafx.stage.Stage;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentAsyncService;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

public class CorriereOrchestrator extends Orchestrator {

    private static CorriereOrchestrator instance;

    private final ShipmentAsyncService shipmentAsyncService;
    /**
     * Costruttore della classe Orchestrator, è protetta rendendo la classe non instanziabile ma derivabile
     *
     * @param dashboardStage
     */
    protected CorriereOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
        shipmentAsyncService = new ShipmentAsyncService();
    }

    public static CorriereOrchestrator getCorriereOrchestrator(Stage dashboardStage) {
        if(instance == null) {
            instance = new CorriereOrchestrator(dashboardStage);
        }
        return instance;
    }


}
