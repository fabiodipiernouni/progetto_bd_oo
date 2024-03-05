package org.unina.uninadelivery33.presentation.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.unina.uninadelivery33.presentation.helper.Session;
import org.unina.uninadelivery33.presentation.orchestrator.appdomain.LoginOrchestrator;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class UninaApplication extends Application {
    @Override
    public void stop() {
        //todo: richiamare service per chiudere la connessione

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