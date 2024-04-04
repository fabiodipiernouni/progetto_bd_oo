package org.unina.uninadelivery.presentation.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestrator;

import java.io.IOException;

public class UninaApplication extends Application {
    @Override
    public void stop() {
        LoginOrchestrator loginOrchestrator = LoginOrchestrator.getLoginOrchestrator(null);
        loginOrchestrator.applicationStopped();
    }

    @Override
    public void start(Stage stage) throws IOException {
        LoginOrchestrator loginOrchestrator = LoginOrchestrator.getLoginOrchestrator(stage);
        loginOrchestrator.applicationStarted(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}