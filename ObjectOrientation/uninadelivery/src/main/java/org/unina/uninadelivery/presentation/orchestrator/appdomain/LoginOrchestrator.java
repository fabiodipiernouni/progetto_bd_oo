package org.unina.uninadelivery.presentation.orchestrator.appdomain;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.unina.uninadelivery.bll.appdomain.AuthService;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.presentation.app.UninaApplication;
import org.unina.uninadelivery.presentation.controller.appdomain.LoginController;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.css.themes.MFXThemeManager;
import org.unina.uninadelivery.presentation.css.themes.Themes;
import org.unina.uninadelivery.presentation.helper.Session;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;
import org.yaml.snakeyaml.Yaml;

public class LoginOrchestrator extends Orchestrator implements LoginOrchestration {
    private Stage loginStage;

    private static LoginOrchestrator instance;
    public static LoginOrchestrator getLoginOrchestrator() {
        return instance;
    }

    public static LoginOrchestrator getLoginOrchestrator(Stage dashboardStage) {
        if(instance == null) {
            instance = new LoginOrchestrator(dashboardStage);
        }
        return instance;
    }

    private LoginOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
    }

    public void loginClicked(String username, String password) {
        doLoginClicked(username, password, null);
    }

    public void loginClicked(String username, String password, ChangeListener<UtenteDTO> utenteDtoChanged) {
        doLoginClicked(username, password, utenteDtoChanged);
    }

    private void LoadApplicationYaml() throws IOException {
        Yaml yaml = new Yaml();

        try (InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("application.yml")) {

            yamlValues = yaml.load(inputStream);

            Session session = Session.getInstance();
            session.setSessionData("application.yml", yamlValues);
        }
        catch (IOException e) {
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

        if(applicationConfig == null) Platform.exit(); //todo: gestire meglio, magari con un messaggio di errore

        if(applicationConfig.get("loginEnabled").equals("true"))
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

        scene.setUserData(root);

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

            loginStage.setTitle("UninaDelivery - Login");
            loginStage.setResizable(false);
            loginStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);

            loginStage.setOnShown(windowEvent -> {
                window.getScene().getRoot().setEffect(blur);
            });

            loginStage.setOnHidden(windowEvent -> {
                window.getScene().getRoot().setEffect(null);
            });

            //stage.setAlwaysOnTop(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/views/appdomain/login-view.fxml"));
            Scene loginScene = new Scene(loader.load(), 600, 400);

            loginStage.setScene(loginScene);
            loginStage.showAndWait();
        } catch (IOException e) {
            //todo: gestire meglio eventuali errori
        }
    }

    private void doLoginClicked(String username, String password, ChangeListener<UtenteDTO> utenteDtoChanged)  {
        Session session = Session.getInstance();

        Property<UtenteDTO> utenteDtoProperty = session.getUserDto();

        //Validate
        boolean isValid = true;

        //fill utenteDto in Property
        utenteDtoProperty.setValue(new UtenteDTO());

        AuthService authService = new AuthService();
        try {
            UtenteDTO ut = authService.login(username, password);
            if(ut != null) {
                utenteDtoProperty.setValue(ut);
            }
            else {
                isValid = false;
            }
        }
        catch (PersistenceException e) {
            //todo: gestire
            isValid = false;
        }

        if(utenteDtoChanged != null) {
            utenteDtoProperty.addListener(utenteDtoChanged);
        }

        if(isValid) {
            Session.getInstance().addUserDto(utenteDtoProperty);
            loginStage.close();
            /*URL res = UninaApplication.class.getResource("/presentation/views/dashboard-view.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(res);*/


            /*UninaController mainController = (UninaController) dashboardStage.getScene().getRoot().getUserData();
            mainController.setupUserData();*/
            dashboardController.setupUserData();
        }
        else {
            utenteDtoProperty.setValue(null);
            Session.getInstance().addUserDto(utenteDtoProperty);
        }
    }
}
