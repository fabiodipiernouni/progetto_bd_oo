package org.unina.uninadelivery.presentation.orchestrator.appdomain;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.unina.uninadelivery.bll.appdomain.AppService;
import org.unina.uninadelivery.bll.appdomain.AuthService;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.presentation.app.UninaApplication;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.appdomain.LoginController;
import org.unina.uninadelivery.presentation.css.themes.MFXThemeManager;
import org.unina.uninadelivery.presentation.css.themes.Themes;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;
import org.yaml.snakeyaml.Yaml;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginOrchestrator extends Orchestrator implements LoginOrchestration {
    private Stage loginStage;

    private final AppService appService;
    
    private static LoginOrchestrator instance;

    public static LoginOrchestrator getLoginOrchestrator() {
        return instance;
    }

    public static LoginOrchestrator getLoginOrchestrator(Stage dashboardStage) {
        if (instance == null) {
            instance = new LoginOrchestrator(dashboardStage);
        }
        return instance;
    }

    private LoginOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
        appService = new AppService();
    }


    private void LoadApplicationYaml() throws IOException {
        Yaml yaml = new Yaml();

        try (InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("application.yml")) {

            yamlValues = yaml.load(inputStream);

            Session session = Session.getInstance();
            session.setSessionData("application.yml", yamlValues);
        } catch (IOException e) {
            throw e;
        }
    }


    public void applicationStarted(Stage dashboardStage) throws IOException {

        LoadApplicationYaml();

        Scene scene = buildDashboardScene(dashboardStage);

        //add stage to session
        Session session = Session.getInstance();
        session.setSessionData("dashboardStage", dashboardStage);

        Map<String, Object> applicationConfig;
        try {
            applicationConfig = (Map<String, Object>) yamlValues.get("application");
        } catch (Exception e) {
            applicationConfig = null;
        }

        if (applicationConfig == null) {
            Platform.exit(); //todo: gestire meglio, magari con un messaggio di errore
            System.exit(1);
        }

        if(!appService.IsConnectionAlive())
        {
            Platform.runLater(()-> dashboardController.showDialog("error", "Errore di connessione", "Impossibile connettersi al database. Contattare l'amministratore di sistema.", event -> {
                Platform.exit();
                System.exit(1);
            }));
        }

        if (applicationConfig.get("loginEnabled").equals(true))
            showLoginPopup(scene);
    }

    private Scene buildDashboardScene(Stage dashboardStage) throws IOException {
        URL res = UninaApplication.class.getResource("/org/unina/uninadelivery/presentation/views/dashboard-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(res);
        fxmlLoader.setControllerFactory(c -> new DashboardController(dashboardStage));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        dashboardController = fxmlLoader.getController();

        scene.setUserData(dashboardController);

        dashboardStage.initStyle(StageStyle.TRANSPARENT);
        //dashboardStage.getIcons().add(new Image(Objects.requireNonNull(UninaApplication.class.getResourceAsStream("/presentation/icons/delivery-truck_12731488.png"))));
        dashboardStage.setTitle("UninaDelivery - Unina delivery service");
        dashboardStage.setScene(scene);
        dashboardStage.setMinWidth(800);
        dashboardStage.setMinHeight(600);
        dashboardStage.setWidth(1200);
        dashboardStage.setHeight(750);
        dashboardStage.show();
        return scene;
    }

    public void showLoginPopup(Scene scene) {
        try {
            //Show del popup
            var window = scene.getWindow();

            // Crea l'effetto di sfocatura
            GaussianBlur blur = new GaussianBlur();

            loginStage = new Stage();
            loginStage.setOnCloseRequest(windowEvent -> {
                Platform.exit();
                System.exit(0);
            });

            //loginStage.setTitle("UninaDelivery - Login");
            //loginStage.setResizable(false);
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.initStyle(StageStyle.TRANSPARENT);

            loginStage.setOnShown(windowEvent -> {
                window.getScene().getRoot().setEffect(blur);
            });

            loginStage.setOnHidden(windowEvent -> {
                window.getScene().getRoot().setEffect(null);
            });

            //stage.setAlwaysOnTop(true);

            FXMLLoader loader = new FXMLLoader(UninaApplication.class.getResource("/org/unina/uninadelivery/presentation/views/appdomain/login-view.fxml"));
            Scene loginScene = new Scene(loader.load());

            LoginController loginController = (LoginController) loader.getController();
            loginController.setDashboardStage(dashboardStage);

            MFXThemeManager.addOn(loginScene, Themes.DEFAULT, Themes.LEGACY);

            loginScene.setFill(Color.TRANSPARENT);
            loginStage.setScene(loginScene);
            loginStage.showAndWait();
        } catch (IOException e) {
            //todo: gestire meglio eventuali errori
            e.printStackTrace();
        }
    }

    public Boolean doLoginClicked(
            String username, String password,
            ChangeListener<UtenteDTO> utenteDtoChanged,
            EventHandler<WorkerStateEvent> onRunning,
            EventHandler<WorkerStateEvent> onSucceeded,
            EventHandler<WorkerStateEvent> onFailed
            ) throws LoginException {

        Session session = Session.getInstance();

        Property<UtenteDTO> utenteDtoProperty = session.getUserDto();

        //Validate
        boolean isValid = true;

        //fill utenteDto in Property
//        utenteDtoProperty.setValue(new UtenteDTO());

        AuthService authService = new AuthService();
        Optional<UtenteDTO> ut;
        Task<Optional<UtenteDTO>> loginTask;

        loginTask = authService.login(username, password);

        if(onRunning != null) loginTask.setOnRunning(onRunning);
        if(onSucceeded != null) loginTask.setOnSucceeded(onSucceeded);
        if(onFailed != null) loginTask.setOnFailed(onFailed);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            executorService.submit(loginTask);
            ut = loginTask.get(); //questa istruzione effettua un await del task, l'esecuzione riprende quando il task torna il risultato

            if (ut.isPresent()) {
                utenteDtoProperty.setValue(ut.get());
            } else {
                isValid = false;
            }
        } catch (ExecutionException e) {
            throw new LoginException("Errore esecuzione login.");
        } catch (InterruptedException e) {
            throw new LoginException("Azione interrotta");
        }
        finally {
            executorService.shutdown();
        }

        if (utenteDtoChanged != null) {
            utenteDtoProperty.addListener((ChangeListener<? super UtenteDTO>) null);
            utenteDtoProperty.addListener(utenteDtoChanged);
        }

        if (isValid) {
            Session.getInstance().setUserDto(utenteDtoProperty);
            loginStage.close();
            dashboardController.setupUserData();
        } else {
            utenteDtoProperty.setValue(null);
            Session.getInstance().setUserDto(utenteDtoProperty);
        }

        return isValid;
    }

    public void applicationStopped() {
        AppService appService = new AppService();
        appService.chiusuraApplicativa();
    }
}
