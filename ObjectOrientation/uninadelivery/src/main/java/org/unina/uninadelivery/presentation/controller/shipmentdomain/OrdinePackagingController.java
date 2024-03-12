package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.shipmentdomain.OrdinePackagingModel;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.IGenericOdlOrchestrator;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestratorFactory;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrdinePackagingController implements Initializable {
    private final OrdinePackagingModel ordinePackagingModel;
    @FXML
    protected MFXButton actionButton;
    @FXML
    protected Label actionLabel;
    @FXML
    protected Label lblNumeroSpedizione;
    @FXML
    protected Label lblRagSoc;
    @FXML
    protected Label lblStato;
    @FXML
    protected Label lblOrganizzatore;
    @FXML
    protected Label lblDataCreazione;
    @FXML
    protected Label lblDataInizioLav;
    @FXML
    protected Label lblDataFineLav;
    @FXML
    protected Label lblTrackingNum;
    @FXML
    protected ImageView imgCopyToTracking;
    @FXML
    protected MFXPaginatedTableView dettGrid;

    private MFXGenericDialog popupNoteContent;
    private MFXStageDialog popupNoteDialog;

    public OrdinePackagingController(OrdinePackagingModel ordinePackagingModel) {
        this.ordinePackagingModel = ordinePackagingModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();
        Stage dashboardStage = (Stage) session.getSessionData("dashboardStage");
        DashboardController dashboardController = (DashboardController) dashboardStage.getUserData();

        //Prepariamoci a gestire il feedback da dare all'utente in caso di esito positivo o negativo
        ChangeListener<? super Boolean> giveFeedback = (observable, oldValue, newValue) -> {
            if (newValue) {
                dashboardController.showDialog("info", "Operazione completata", "L'operazione è stata completata con successo");
            } else {
                dashboardController.showDialog("error", "Errore", "L'operazione non è andata a buon fine.");
            }
        };

        actionLabel.setVisible(false);
        actionButton.setVisible(false);

        //La pagina mostra un ordine che può assumere gli stati "DaAssegnare", "Assegnato", "In lavorazione", "Lavorato"
        //In base allo stato, l'utente corriere può compiere diverse azioni
        switch (ordinePackagingModel.getStato()) {
            case "DaAssegnare":
                if (utente.getProfilo().equals("OperatoreCorriere")) {
                    actionLabel.setVisible(true);
                    actionButton.setVisible(true);
                    actionLabel.setText("L'ordine non è assegnato a nessun corriere");
                    actionButton.setText("Assegna a me");
                    actionButton.setOnAction(event -> {
                        IGenericOdlOrchestrator orchestrator = OdlOrchestratorFactory.getOdlOrchestrator(dashboardStage);
                        Task<Boolean> res = new Task<Boolean>() {
                            @Override
                            protected Boolean call() throws Exception {
                                try {
                                    orchestrator.assegnaOdlPackagingClicked(
                                            ordinePackagingModel.getMagazzino(),
                                            ordinePackagingModel.getSpedizione(),
                                            (OperatoreCorriereDTO) utente);
                                    return true;
                                } catch (SpedizioniException ex) {
                                    return false;
                                }
                            }
                        };
                    });
                }
                break;
            case "Assegnato":
                if (utente.getProfilo().equals("OperatoreCorriere")) {
                    actionLabel.setVisible(true);
                    actionButton.setVisible(true);
                    actionLabel.setText("L'ordine è assegnato, iniziare la lavorazione?");
                    actionButton.setText("Inizia lavorazione");
                    actionButton.setOnAction(event -> {
                        IGenericOdlOrchestrator orchestrator = OdlOrchestratorFactory.getOdlOrchestrator(dashboardStage);
                        Task<Boolean> res = new Task<Boolean>() {
                            @Override
                            protected Boolean call() throws Exception {
                                try {
                                    orchestrator.iniziaLavorazioneOdlPackagingClicked(ordinePackagingModel.getMagazzino(), ordinePackagingModel.getSpedizione());
                                    return true;
                                } catch (SpedizioniException ex) {
                                    return false;
                                }
                            }
                        };

                        res.valueProperty().addListener(giveFeedback);
                        ExecutorService executorService = Executors.newSingleThreadExecutor(); // newFixedThreadPool(2);
                        executorService.submit(res);
                    });
                }
                break;
            case "In lavorazione":
                if (utente.getProfilo().equals("OperatoreCorriere")) {
                    actionLabel.setVisible(true);
                    actionButton.setVisible(true);
                    actionLabel.setText("L'ordine è in lavorazione, concludere la lavorazione?");
                    actionButton.setText("Concludi lavorazione");
                    actionButton.setOnAction(event -> {
                        ShowGetNotePopup("Concludi lavorazione", "Hai note da segnalare?",
                                "Inserisci le note qui...", event1 -> {
                                    MFXButton procede = (MFXButton) event1.getSource();
                                    MFXTextField noteTextField = (MFXTextField) procede.getScene().lookup("#noteTextField");

                                    IGenericOdlOrchestrator orchestrator = OdlOrchestratorFactory.getOdlOrchestrator(dashboardStage);
                                    Task<Boolean> res = new Task<>() {
                                        @Override
                                        protected Boolean call() throws Exception {
                                            try {
                                                orchestrator.concludiLavorazioneOdlPackagingClicked(ordinePackagingModel.getMagazzino(), ordinePackagingModel.getSpedizione(), noteTextField.getText());
                                                return true;
                                            } catch (SpedizioniException ex) {
                                                return false;
                                            }
                                        }
                                    };

                                    res.valueProperty().addListener(giveFeedback);
                                    ExecutorService executorService = Executors.newSingleThreadExecutor(); // newFixedThreadPool(2);
                                    executorService.submit(res);
                                })
                                .showDialog();
                    });
                }
                break;
            default:
                actionLabel.setVisible(false);
                actionButton.setVisible(false);
        }
    }

    private MFXStageDialog ShowGetNotePopup(String title, String domanda, String promptText, EventHandler<MouseEvent> procedeAction) {

        // Crea la label
        Label label = new Label(domanda);

        // Crea il campo di testo
        MFXTextField textField = new MFXTextField();
        textField.setId("noteTextField");
        textField.setPromptText(promptText);
        textField.setTextLimit(512);

        // Crea il pulsante
        //MFXButton proceedButton = new MFXButton("Procedi");
        VBox dialogContent = new VBox(label, textField);//, proceedButton);
        dialogContent.setSpacing(15);
        popupNoteContent = MFXGenericDialogBuilder.build()
                .setContent(dialogContent)
                .get();

        Stage stage = (Stage) lblTrackingNum.getScene().getWindow();

        popupNoteDialog = MFXGenericDialogBuilder.build(popupNoteContent)
                .toStageDialogBuilder()
                .initOwner(stage)
                .initModality(Modality.APPLICATION_MODAL)
                .setDraggable(true)
                .setTitle(title)
                .setScrimPriority(ScrimPriority.WINDOW)
                .setScrimOwner(true)
                .get();

        popupNoteContent.addActions(
                Map.entry(new MFXButton("Procedi"), procedeAction),
                Map.entry(new MFXButton("Annulla"), event -> popupNoteDialog.close())
        );

        popupNoteContent.setMaxSize(400, 200);

        // Mostra il dialog
        return popupNoteDialog;
    }

}
