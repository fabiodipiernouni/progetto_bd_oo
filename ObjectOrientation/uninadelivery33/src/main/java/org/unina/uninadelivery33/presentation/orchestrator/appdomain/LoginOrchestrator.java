package org.unina.uninadelivery33.presentation.orchestrator.appdomain;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.unina.uninadelivery33.bll.appdomain.AuthService;
import org.unina.uninadelivery33.bll.exception.ServiceException;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery33.presentation.app.UninaApplication;
import org.unina.uninadelivery33.presentation.controller.LoginController;
import org.unina.uninadelivery33.presentation.controller.UninaController;
import org.unina.uninadelivery33.presentation.helper.Session;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;


public class LoginOrchestrator implements LoginOrchestration {
    private Stage loginStage;
    private final Stage dashboardStage;
    private UninaController dashboardController;
    private LoginController loginController;

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
        this.dashboardStage = dashboardStage;
    }

    public void loginClicked(String username, String password) {
        doLoginClicked(username, password, null);
    }

    public void loginClicked(String username, String password, ChangeListener<UtenteDTO> utenteDtoChanged) {
        doLoginClicked(username, password, utenteDtoChanged);
    }


    public void applicationStarted() throws IOException {
        URL res = UninaApplication.class.getResource("/presentation/views/unina-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(res);
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 800, 600);

        dashboardController = fxmlLoader.getController();

        scene.setUserData(parent);

        dashboardStage.getIcons().add(new Image(Objects.requireNonNull(UninaApplication.class.getResourceAsStream("/presentation/icons/delivery-truck_12731488.png"))));
        dashboardStage.setTitle("UninaDelivery - Unina delivery service");
        dashboardStage.setScene(scene);
        dashboardStage.show();

        //add stage to session
        Session session = Session.getInstance();
        session.addSessionData("dashboardStage", dashboardStage);

        showLoginPopup(scene);
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

            loginController = loader.getController();

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
            Optional<UtenteDTO> utente = authService.login(username, password);
            if(utente.isEmpty()) {
                utenteDtoProperty.setValue(null); //TODO: null o Optional?
            }
            else {
                isValid = false;
            }
        }
        catch (ServiceException e) {
            //todo: gestire
            isValid = false;
        }

        if(utenteDtoChanged != null) {
            utenteDtoProperty.addListener(utenteDtoChanged);
        }

        if(isValid) {
            Session.getInstance().addUserDto(utenteDtoProperty);
            loginStage.close();
            /*URL res = UninaApplication.class.getResource("/presentation/views/unina-view.fxml");
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
