package org.unina.uninadelivery.presentation.orchestrator;

import javafx.stage.Stage;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.helper.Session;

import java.util.Map;

public class Orchestrator {
    protected final Stage dashboardStage;
    protected Map<String, Object> yamlValues;
    protected DashboardController dashboardController;

    protected static Orchestrator orchestrator;

    /**
     * Costruttore della classe Orchestrator, è protetta rendendo la classe non instanziabile ma derivabile
     *
     * @param dashboardStage
     */
    protected Orchestrator(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
        if (dashboardStage.getScene() != null)
            this.dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        //else vuol dire che l'orchestrator è stato creato prima che la dashboard fosse caricata, questo succede con loginOrchestrator

        Session session = Session.getInstance();
        if (session.containsKey("application.yml"))
            yamlValues = (Map<String, Object>) session.getSessionData("application.yml");
    }

    public void taskRunning() {
        this.dashboardController.showLoadingView();
    }

    public void taskCompleted() {
        this.dashboardController.hideLoadingView();
    }
}
