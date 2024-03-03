package org.unina.uninadelivery33.presentation.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.unina.uninadelivery33.presentation.orchestrator.appdomain.LoginOrchestrator;

import java.io.IOException;

public class UninaApplication extends Application {
    @Override
    public void stop() {
        //todo: richiamare service per chiudere la connessione
    }

    @Override
    public void start(Stage stage) throws IOException {
        LoginOrchestrator loginOrchestrator = LoginOrchestrator.getLoginOrchestrator(stage);
        loginOrchestrator.applicationStarted();
    }

    public static void main(String[] args) {
        launch(args);
    }
}