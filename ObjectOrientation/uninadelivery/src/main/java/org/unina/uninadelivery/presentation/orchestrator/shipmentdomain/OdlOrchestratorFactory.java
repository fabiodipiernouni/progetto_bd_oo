package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import javafx.stage.Stage;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.OrdiniPackagingController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.OrdiniSpedizioneController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.SpedizioniController;

public class OdlOrchestratorFactory {
    private static OdlOrchestrator instance;

    public static IGenericOdlOrchestrator getOdlOrchestrator(Stage dashboardStage) {
        if (instance == null) {
            instance = new OdlOrchestrator(dashboardStage);
        }
        return instance;
    }

    public static IOdlOrchestratorOrdiniPackaging getOdlOrchestrator(Stage dashboardStage, OrdiniPackagingController ordiniPackagingController) {
        if (instance == null) {
            instance = new OdlOrchestrator(dashboardStage, ordiniPackagingController);
        }
        return instance;
    }

    public static IOdlOrchestratorSpedizioni getOdlOrchestrator(Stage dashboardStage, SpedizioniController spedizioniController) {
        if (instance == null) {
            instance = new OdlOrchestrator(dashboardStage, spedizioniController);
        }
        return instance;
    }

    public static IOdlOrchestratrOrdiniSpedizione getOdlOrchestrator(Stage dashboardStage, OrdiniSpedizioneController ordiniSpedizioneController) {
        if (instance == null) {
            instance = new OdlOrchestrator(dashboardStage, ordiniSpedizioneController);
        }
        return instance;
    }
}
