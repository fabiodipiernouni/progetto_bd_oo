package org.unina.uninadelivery.presentation.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTooltip;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
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
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.presentation.controller.customerdomain.ClientiController;
import org.unina.uninadelivery.presentation.controller.orgdomain.GestioneMagazziniController;
import org.unina.uninadelivery.presentation.controller.orgdomain.HomeManagerController;
import org.unina.uninadelivery.presentation.controller.orgdomain.HomeOpCorriereController;
import org.unina.uninadelivery.presentation.controller.orgdomain.HomeOpFilialeController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.OrdiniPackagingController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.OrdiniSpedizioneController;
import org.unina.uninadelivery.presentation.controller.shipmentdomain.SpedizioniController;
import org.unina.uninadelivery.presentation.helper.MfxToggleButtonsHelper;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.LoginOrchestrator;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.unina.uninadelivery.presentation.helper.ResourceLoader.loadURL;

public class DashboardController implements Initializable {

    private final MfxToggleButtonsHelper mfxToggleButtonsHelper;
    @FXML
    public Label profiloLabel;
    @FXML
    private VBox navBar;
    @FXML
    private HBox formHeader;
    @FXML
    private Label lblCorriere;
    @FXML
    private Label lblFilialeTitle;
    @FXML
    private Label lblFiliale;
    @FXML
    private Label lblCorriereTitle;
    @FXML
    private Label lblStatusBar;
    private UtenteDTO utente;
    private final Stage dashboardStage;
    private final ToggleGroup toggleGroup;
    @FXML
    private Label utenteLabel;
    @FXML
    private MFXScrollPane scrollPane;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXFontIcon alwaysOnTopIcon;
    @FXML
    private MFXFontIcon minimizeIcon;
    @FXML
    private MFXFontIcon closeIcon;
    @FXML
    private HBox windowHeader;
    @FXML
    private StackPane contentPane;
    private double xOffset;
    private double yOffset;

    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;


    public DashboardController(Stage dashboardStage) {
        this.toggleGroup = new ToggleGroup();
        this.dashboardStage = dashboardStage;
        ToggleButtonsUtil.addAlwaysOneSelectedSupport(toggleGroup);
        mfxToggleButtonsHelper = new MfxToggleButtonsHelper(toggleGroup);


        //aggiungiamo i dialogs
        Platform.runLater(() -> {
            this.dialogContent = MFXGenericDialogBuilder.build()
                    .makeScrollable(true)
                    .get();

            this.dialog = MFXGenericDialogBuilder.build(dialogContent)
                    .toStageDialogBuilder()
                    .initOwner(dashboardStage)
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setOwnerNode(rootPane)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            dialogContent.addActions(
                    Map.entry(new MFXButton("Ok"), event -> dialog.close())
            );

            dialogContent.setMaxSize(400, 200);
        });

    }

    public void showDialog(String type, String headerText, String message) {

        MFXFontIcon icon = null;
        String styleClass = null;

        dialogContent.setContentText(message);
        dialogContent.setHeaderText(headerText);
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );


        switch (type) {
            case "info":
                icon = new MFXFontIcon("fas-circle-exclamation", 18);
                styleClass = "mfx-info-dialog";

                break;
            case "error":
                icon = new MFXFontIcon("fas-circle-xmark", 18);
                styleClass = "mfx-error-dialog";
                break;
            case "warn":
                icon = new MFXFontIcon("fas-circle-exclamation", 18);
                styleClass = "mfx-warn-dialog";

                break;
        }

        dialogContent.setHeaderIcon(icon);
        dialogContent.getStyleClass().add(styleClass);

        dialog.showDialog();
    }

    public void setupUserData() {
        Session session = Session.getInstance();
        Property<UtenteDTO> prop = session.getUserDto();
        utente = prop.getValue();
        Scene scene = utenteLabel.getScene();

        System.out.println(utente.getProfilo());

        switch(utente.getProfilo()) {
            case "Operatore":
                lblFilialeTitle.setText("Filiale:");
                lblFiliale.setText( ((OperatoreFilialeDTO) utente ).getFiliale().getNome());

                lblFiliale.setVisible(true);
                lblFilialeTitle.setVisible(true);
                lblCorriere.setVisible(false);
                lblCorriereTitle.setVisible(false);
                break;
            case "OperatoreCorriere":
                GruppoCorriereDTO corriereDTO = ((OperatoreCorriereDTO) utente).getGruppoCorriere();
                lblCorriereTitle.setText("Corriere:");
                lblCorriere.setText(corriereDTO.getNome() + "(" + corriereDTO.getCodiceCorriere() + ")");
                lblFilialeTitle.setText("Filiale:");
                lblFiliale.setText(corriereDTO.getFiliale().getNome());

                lblCorriere.setVisible(true);
                lblFiliale.setVisible(true);
                lblFilialeTitle.setVisible(true);
                lblCorriereTitle.setVisible(true);
                break;
            case "Manager":
                lblFilialeTitle.setText("Org:");
                lblFiliale.setText("Unina Delivery ITA");

                lblFiliale.setVisible(true);
                lblFilialeTitle.setVisible(true);
                lblCorriereTitle.setVisible(false);
                lblCorriere.setVisible(false);
        }

        utenteLabel.setText(utente.getUsername());
        profiloLabel.setText(utente.getProfilo());

        try {
            //Aggiungo un evento per gestire il momento del logout. Il logout non farà altro che invalidare l'utente corrente
            //a quel punto il sistema chiederà di fare il login
            prop.addListener((observable, oldValue, newValue) -> {
                if(newValue == null || newValue.getUsername().isEmpty()) {
                    LoginOrchestrator loginOrchestrator = LoginOrchestrator.getLoginOrchestrator();
                    loginOrchestrator.showLoginPopup(scene);
                }
            });
        } catch (Exception e) {
            //do nothing
        }

        MFXLoader loader = new MFXLoader();

        //Costruisco le voci di menu sulla base delle funzioni associate all'utente loggato
        buildMenu(loader);

        //imposto il comportamento del pannello di scorrimento
        ScrollUtils.addSmoothScrolling(scrollPane);
    }

    public void onBtnLogout(ActionEvent actionEvent) {
        Session session = Session.getInstance();
        Property<UtenteDTO> user = session.getUserDto();
        user.setValue(new UtenteDTO());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //imposto il comportamento dei pulsanti di chiusura applicazione, minimizza e sempre in primo piano
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Platform.exit();
            System.exit(0);
        });
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
        alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean newVal = !dashboardStage.isAlwaysOnTop();
            alwaysOnTopIcon.pseudoClassStateChanged(PseudoClass.getPseudoClass("always-on-top"), newVal);
            dashboardStage.setAlwaysOnTop(newVal);
        });

        MFXTooltip.of(closeIcon, "Chiudi").install();
        MFXTooltip.of(minimizeIcon, "Minimizza").install();
        MFXTooltip.of(alwaysOnTopIcon, "Sempre in primo piano").install();

        //imposto il comportamento del trascinamento della finestra
        windowHeader.setOnMousePressed(event -> {
            xOffset = dashboardStage.getX() - event.getScreenX();
            yOffset = dashboardStage.getY() - event.getScreenY();
        });
        windowHeader.setOnMouseDragged(event -> {
            dashboardStage.setX(event.getScreenX() + xOffset);
            dashboardStage.setY(event.getScreenY() + yOffset);
        });
   }


    private void buildMenu(MFXLoader loader) {
        Session session = Session.getInstance();

        Map<String, Object> appyml = (Map<String, Object>)session.getSessionData("application.yml");

        Map<String, Object> menuViews = (Map<String, Object>) ((Map<String, Object>)appyml.get("application")).get("menuViews");

        List<String> funzioni = utente.getFunzioni();

        //menu home
        if(utente.getProfilo().equals("Operatore"))
            loader.addView(MFXLoaderBean.of("HOME", loadURL("/views/orgdomain/home-opfiliale-view.fxml"))
                    .setBeanToNodeMapper(() ->  mfxToggleButtonsHelper.createToggle("fas-house-user", "HOME")).setDefaultRoot(true).setControllerFactory(c -> new HomeOpFilialeController(dashboardStage)).get());
        else if (utente.getProfilo().equals("OperatoreCorriere")) {
            loader.addView(MFXLoaderBean.of("HOME", loadURL("/views/orgdomain/home-opcorriere-view.fxml"))
                    .setBeanToNodeMapper(() ->  mfxToggleButtonsHelper.createToggle("fas-house-user", "HOME")).setDefaultRoot(true).setControllerFactory(c -> new HomeOpCorriereController(dashboardStage)).get());
        } else if (utente.getProfilo().equals("Manager")) {
            loader.addView(MFXLoaderBean.of("HOME", loadURL("/views/orgdomain/home-amministratore-view.fxml"))
                    .setBeanToNodeMapper(() -> mfxToggleButtonsHelper.createToggle("fas-house-user", "HOME")).setDefaultRoot(true).setControllerFactory(c -> new HomeManagerController(dashboardStage)).get());
        }
        else {
            //todo gestire profilo non pervenuto o non riconosciuto
        }

        //todo: momentaneo, gestire con le funzioni
        if(utente.getProfilo().equals("Operatore") || utente.getProfilo().equals("Manager")) {
            //Gestione clienti
            loader.addView(MFXLoaderBean.of("CLIENTI", loadURL("/views/customerdomain/clienti-view.fxml"))
                    .setBeanToNodeMapper(() -> mfxToggleButtonsHelper.createToggle("fas-user-tie", "GESTIONE CLIENTI")).setControllerFactory(c -> new ClientiController(dashboardStage)).get());

            //Spedizioni
            loader.addView(MFXLoaderBean.of("SPEDIZIONI", loadURL("/views/shipmentdomain/spedizioni-view.fxml"))
                    .setBeanToNodeMapper(() -> mfxToggleButtonsHelper.createToggle("fas-envelope-open-text", "GESTIONE SPEDIZIONI")).setControllerFactory(c -> new SpedizioniController(dashboardStage)).get());
        }

        if(utente.getProfilo().equals("Manager") || utente.getProfilo().equals("OperatoreCorriere") || utente.getProfilo().equals("Operatore")) {
            //Gestione ordini di packaging
            loader.addView(MFXLoaderBean.of("ORDINI PACKAGING", loadURL("/views/shipmentdomain/ordini-packaging-view.fxml"))
                    .setBeanToNodeMapper(() -> mfxToggleButtonsHelper.createToggle("fas-boxes-packing", "ORDINI DI PACKAGING")).setControllerFactory(c -> new OrdiniPackagingController(dashboardStage)).get());

            //Gestione ordini di packaging
            loader.addView(MFXLoaderBean.of("ORDINI SPEDIZIONE", loadURL("/views/shipmentdomain/ordini-spedizione-view.fxml"))
                    .setBeanToNodeMapper(() -> mfxToggleButtonsHelper.createToggle("fas-truck", "ORDINI DI TRASPORTO")).setControllerFactory(c -> new OrdiniSpedizioneController(dashboardStage)).get());
        }

        if(utente.getProfilo().equals("OperatoreCorriere")) {
            //Gestione Magazzini
            loader.addView(MFXLoaderBean.of("MAGAZZINI", loadURL("/views/orgdomain/magazzini-view.fxml"))
                    .setBeanToNodeMapper(() -> mfxToggleButtonsHelper.createToggle("fas-warehouse", "GESTIONE MAGAZZINI")).setControllerFactory(c -> new GestioneMagazziniController(dashboardStage)).get());
        }

        //about
        loader.addView(MFXLoaderBean.of("ABOUT", loadURL("/views/appdomain/about-view.fxml"))
                .setBeanToNodeMapper(() ->  mfxToggleButtonsHelper.createToggle("fas-info", "ABOUT")).get());

        //attivo tutto
        loader.setOnLoadedAction(beans -> {
            List<ToggleButton> nodes = beans.stream()
                    .map(bean -> {
                        ToggleButton toggle = (ToggleButton) bean.getBeanToNodeMapper().get();
                        toggle.setOnAction(event -> {
                            contentPane.getChildren().setAll(bean.getRoot());
                            lblStatusBar.setText("Visualizzazione corrente: " + bean.getViewName().toLowerCase());
                        });
                        if (bean.isDefaultView()) {
                            contentPane.getChildren().setAll(bean.getRoot());
                            toggle.setSelected(true);
                            lblStatusBar.setText("Visualizzazione corrente: " + bean.getViewName().toLowerCase());
                        }
                        return toggle;
                    })
                    .toList();

            //logout
            MFXLoaderBean logoutBean = MFXLoaderBean.of("LOGOUT", null)
                    .setBeanToNodeMapper(() ->  mfxToggleButtonsHelper.createToggle("fas-door-open", "LOGOUT")).get();

            ToggleButton logoutBtn = (ToggleButton) logoutBean.getBeanToNodeMapper().get();
            logoutBtn.setOnAction(event -> session.getUserDto().setValue(new UtenteDTO())); //Lo svuotamento dell'utente farà scattare il logout

            navBar.getChildren().setAll(nodes);
            navBar.getChildren().add(logoutBtn);
        });
        loader.start();
    }

    public void changeView(String viewName, String viewUrl, Callback<Class<?>, Object> controllerFactory){
        //Diciamo al loader cosa fare a load terminato (cioè cambiare la view)
        MFXLoader loader = new MFXLoader(beans -> contentPane.getChildren().setAll(beans.getFirst().getRoot()));

        //creo un bean dalla view caricata
        MFXLoaderBean bean = MFXLoaderBean.of(viewName, loadURL(viewUrl)).get();

        //imposto il controller factory
        bean.setControllerFactory(controllerFactory); //per il passaggio dei parametri

        loader.addView(bean);
        loader.start();

        lblStatusBar.setText("Visualizzazione corrente: " + viewName.toLowerCase());
    }
}