package org.unina.uninadelivery.presentation.controller;

import java.util.Map;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoaderBean;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.presentation.controller.orgdomain.HomeManagerController;
import org.unina.uninadelivery.presentation.controller.orgdomain.HomeOpCorriereController;
import org.unina.uninadelivery.presentation.controller.orgdomain.HomeOpFilialeController;
import org.unina.uninadelivery.presentation.helper.MfxToggleButtonsHelper;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestrator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static org.unina.uninadelivery.presentation.helper.resourceLoader.loadURL;

public class DashboardController implements Initializable {

    private final MfxToggleButtonsHelper mfxToggleButtonsHelper;
    private UtenteDTO utente;
    private final Stage dashboardStage;
    private final ToggleGroup toggleGroup;
    @FXML
    public Label utenteLabel;
    public MFXScrollPane scrollPane;
    @FXML
    public AnchorPane rootPane;
    @FXML
    public MFXFontIcon alwaysOnTopIcon;
    @FXML
    public MFXFontIcon minimizeIcon;
    @FXML
    public MFXFontIcon closeIcon;
    @FXML
    public HBox windowHeader;
    @FXML
    public StackPane contentPane;
    private double xOffset;
    private double yOffset;

    public DashboardController(Stage dashboardStage) {
        this.toggleGroup = new ToggleGroup();
        this.dashboardStage = dashboardStage;
        ToggleButtonsUtil.addAlwaysOneSelectedSupport(toggleGroup);
        mfxToggleButtonsHelper = new MfxToggleButtonsHelper(toggleGroup);
    }

    public void setupUserData() {
        Session session = Session.getInstance();
        Property<UtenteDTO> prop = session.getUserDto();
        utente = prop.getValue();
        Scene scene = utenteLabel.getScene();

        //Aggiungo un evento per gestire il momento del logout. Il logout non farà altro che invalidare l'utente corrente
        //a quel punto il sistema chiederà di fare il login
        prop.addListener((observable, oldValue, newValue) -> {
            if(newValue == null || newValue.getUsername().isEmpty()) {
                LoginOrchestrator loginOrchestrator = LoginOrchestrator.getLoginOrchestrator();
                loginOrchestrator.showLoginPopup(scene);
            }
        });

        utenteLabel.setText(utente.getUsername());
    }

    public void onBtnLogout(ActionEvent actionEvent) {
        Session session = Session.getInstance();
        Property<UtenteDTO> user = session.getUserDto();
        user.setValue(new UtenteDTO());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = Session.getInstance();
        Property<UtenteDTO> prop = session.getUserDto();
        utente = new UtenteDTO();// prop.getValue();
        //todo: fixare

        //imposto il comportamento dei pulsanti di chiusura applicazione, minimizza e sempre in primo piano
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
        alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean newVal = !dashboardStage.isAlwaysOnTop();
            alwaysOnTopIcon.pseudoClassStateChanged(PseudoClass.getPseudoClass("always-on-top"), newVal);
            dashboardStage.setAlwaysOnTop(newVal);
        });

        //imposto il comportamento del trascinamento della finestra
        windowHeader.setOnMousePressed(event -> {
            xOffset = dashboardStage.getX() - event.getScreenX();
            yOffset = dashboardStage.getY() - event.getScreenY();
        });
        windowHeader.setOnMouseDragged(event -> {
            dashboardStage.setX(event.getScreenX() + xOffset);
            dashboardStage.setY(event.getScreenY() + yOffset);
        });

        //metodo per il loading delle maschere che popoleranno la pagina nello stackpane
        initialLoader();

        
        ScrollUtils.addSmoothScrolling(scrollPane);

        MFXLoader loader = new MFXLoader();

        //Costruisco le voci di menu sulla base delle funzioni associate all'utente loggato
        buildMenu(loader);
        
        //Aggiungo il pulsante di chiusura applicazione
        buildCloseButton(loader);

    }

    private void buildCloseButton(MFXLoader loader) {
    }

    private void buildMenu(MFXLoader loader) {
        Session session = Session.getInstance();

        Map<String, Object> appyml = (Map<String, Object>)session.getSessionData("application.yml");

        Map<String, Object> menuViews = (Map<String, Object>) ((Map<String, Object>)appyml.get("application")).get("menuViews");

        List<String> funzioni = utente.getFunzioni();

        //menu home
        if(utente.getProfilo().equals("Operatore"))
            loader.addView(MFXLoaderBean.of("HOME", loadURL("views/orgdomain/home-opfiliale-view.fxml"))
                    .setBeanToNodeMapper(() ->  mfxToggleButtonsHelper.createToggle("fa-house-user", "HOME")).setDefaultRoot(true).setControllerFactory(c -> new HomeOpFilialeController(dashboardStage)).get());
        else if (utente.getProfilo().equals("OperatoreCorriere")) {
            loader.addView(MFXLoaderBean.of("HOME", loadURL("views/orgdomain/home-opcorriere-view.fxml"))
                    .setBeanToNodeMapper(() ->  mfxToggleButtonsHelper.createToggle("fa-house-user", "HOME")).setDefaultRoot(true).setControllerFactory(c -> new HomeOpCorriereController(dashboardStage)).get());
        } else if (utente.getProfilo().equals("Manager")) {
            loader.addView(MFXLoaderBean.of("HOME", loadURL("views/orgdomain/home-amministratore-view.fxml"))
                    .setBeanToNodeMapper(() -> mfxToggleButtonsHelper.createToggle("fa-house-user", "HOME")).setDefaultRoot(true).setControllerFactory(c -> new HomeManagerController(dashboardStage)).get());
        }
        else {
            //todo gestire profilo non pervenuto o non riconosciuto
        }
    }

    //Qui carichiamo le view in memoria
    private void initialLoader() {
    }
}