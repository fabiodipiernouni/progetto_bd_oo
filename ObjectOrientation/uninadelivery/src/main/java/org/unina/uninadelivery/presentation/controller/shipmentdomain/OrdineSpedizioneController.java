package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.filter.FloatFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.PaccoDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.shipmentdomain.OrdineSpedizioneModel;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;

public class OrdineSpedizioneController implements Initializable {
    private final OrdineSpedizioneModel ordineSpedizioneModel;
    private final OdlOrchestrator odlOrchestrator;
    private final Stage dashboardStage;
    private final UtenteDTO utente;
    private ChangeListener<? super Boolean> giveFeedback;

    @FXML
    protected AnchorPane rootAnchorPane;
    @FXML
    protected MFXTableView<PaccoDTO> dettGrid;
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
    private DashboardController dashboardController;
    private Task<Boolean> operatoreAssegnaAMeTask;

    private MFXGenericDialog popupNoteContent;
    private MFXStageDialog popupNoteDialog;

    private MFXGenericDialog popupMezziContent;
    private MFXStageDialog popupMezziDialog;


    public OrdineSpedizioneController(OrdineSpedizioneModel ordineSpedizioneModel) {
        this.ordineSpedizioneModel = ordineSpedizioneModel;

        //questi sono dati immutabili, quindi li prendo subito
        Session session = Session.getInstance();
        dashboardStage = (Stage) session.getSessionData("dashboardStage");
        utente = session.getUserDto().getValue();
        odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareTasks();

        lblNumeroSpedizione.setText(String.valueOf(ordineSpedizioneModel.getCodiceOrdineSpedizione()));
        lblRagSoc.setText(ordineSpedizioneModel.getCliente());
        lblStato.setText(ordineSpedizioneModel.getStato());
        lblOrganizzatore.setText(ordineSpedizioneModel.getGruppoCorriere());
        lblDataCreazione.setText(ordineSpedizioneModel.getDataCreazione().toString());
        lblDataInizioLav.setText(ordineSpedizioneModel.getDataInizioLavorazione() != null ? ordineSpedizioneModel.getDataInizioLavorazione().toString() : "Non ancora iniziaa");
        lblDataFineLav.setText(ordineSpedizioneModel.getDataFineLavorazione() != null ? ordineSpedizioneModel.getDataFineLavorazione().toString() : "Non ancora finita");
        lblTrackingNum.setText(ordineSpedizioneModel.getSpedizione().getTrackingNumber());

        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();
        Stage dashboardStage = (Stage) session.getSessionData("dashboardStage");
        dashboardController = (DashboardController) dashboardStage.getScene().getUserData();

        actionLabel.setVisible(false);
        actionButton.setVisible(false);

        switch (ordineSpedizioneModel.getStato()) {
            case "DaAssegnare": {
                if (utente.getProfilo().equals("OperatoreCorriere")) {
                    actionLabel.setVisible(true);
                    actionButton.setVisible(true);
                    actionLabel.setText("L'ordine non è assegnato a nessun corriere");
                    actionButton.setText("Assegna a me");
                    actionButton.setOnAction(event -> {
                        ExecutorService executorService = Executors.newSingleThreadExecutor(); // newFixedThreadPool(2);
                        executorService.submit(operatoreAssegnaAMeTask);
                        executorService.shutdown();
                    });
                } else {
                    actionLabel.setVisible(true);
                    actionLabel.setText("L'ordine non è assegnato a nessun corriere.");
                }
            }
            break;
            case "Assegnato": {
                if (utente.getProfilo().equals("OperatoreCorriere")) {
                    actionLabel.setVisible(true);
                    actionButton.setVisible(true);
                    actionLabel.setText("L'ordine è assegnato, iniziare la lavorazione?");
                    actionButton.setText("Inizia lavorazione");
                    actionButton.setOnAction(event -> {
                        popupMezziDialog.setOwnerNode(rootAnchorPane);
                        popupMezziDialog.showDialog();
                    });
                } else {
                    actionLabel.setVisible(true);
                    actionLabel.setText("L'ordine è assegnato, l'operatore del corriere dovrà cominciare a lavorare l'ordine.");
                }
            }
            break;
            case "InLavorazione": {
                if (utente.getProfilo().equals("OperatoreCorriere")) {
                    actionLabel.setVisible(true);
                    actionButton.setVisible(true);
                    actionLabel.setText("L'ordine è in lavorazione, concludere la lavorazione?");
                    actionButton.setText("Concludi lavorazione");
                    actionButton.setOnAction(event -> {
                        Platform.runLater(() -> {
                            popupNoteDialog.setOwnerNode(rootAnchorPane);
                            popupNoteDialog.showDialog();
                        });
                    });
                } else {
                    actionLabel.setVisible(true);
                    actionLabel.setText("L'ordine è in lavorazione, solo l'operatore del corriere che ha in carico l'ordine può concluderlo.");
                }
            }
            break;
            case "Lavorato": {
                actionLabel.setVisible(true);
                actionButton.setVisible(false);
                actionLabel.setText("L'ordine è stato lavorato.");
            }
            break;
            default: {
                actionLabel.setVisible(false);
                actionButton.setVisible(false);
            }
        }

        Platform.runLater(() -> {
            ConfigureNotePopup("Concludi lavorazione", "Hai note da segnalare?",
                    "Inserisci le note qui...",
                    event1 -> {
                        MFXButton procede = (MFXButton) event1.getSource();
                        TextArea noteTextField = (TextArea) procede.getScene().lookup("#noteTextField");
                        Task<Boolean> operatoreConcludiLavTask = new Task<>() {
                            @Override
                            protected Boolean call() {
                                try {
                                    odlOrchestrator.concludiLavorazioneOdlTrasportoClicked(ordineSpedizioneModel, noteTextField.getText());
                                    return true;
                                } catch (SpedizioniException ex) {
                                    return false;
                                }
                            }
                        };

                        operatoreConcludiLavTask.valueProperty().addListener(giveFeedback);

                        ExecutorService executorService = Executors.newSingleThreadExecutor(); // newFixedThreadPool(2);
                        executorService.submit(operatoreConcludiLavTask);
                        executorService.shutdown();
                    });
        });

        if (ordineSpedizioneModel.getStato().equals("Assegnato") && utente.getProfilo().equals("OperatoreCorriere")) {
            Platform.runLater(() -> {
                try {
                    List<MezzoDiTrasportoDTO> mezziLiberi = odlOrchestrator.getMezziDiTrasportoLiberiOggi(ordineSpedizioneModel.getGruppoCorriereDTO());

                    ConfigureMezziPopup("Seleziona mezzo", "Seleziona il mezzo di trasporto da utilizzare", "Seleziona il mezzo...",
                            FXCollections.observableList(mezziLiberi),
                            procedeEventHandler -> {
                                MFXButton procede = (MFXButton) procedeEventHandler.getSource();
                                MFXFilterComboBox<MezzoDiTrasportoDTO> cmbMezziField = (MFXFilterComboBox<MezzoDiTrasportoDTO>) procede.getScene().lookup("#cmbMezziField");
                                Task<Boolean> operatoreIniziaLavPackagingTask = new Task<>() {
                                    @Override
                                    protected Boolean call() {
                                        try {
                                            odlOrchestrator.iniziaLavorazioneOdlTrasportoClicked(ordineSpedizioneModel.getSpedizione(), ordineSpedizioneModel.getFiliale(), cmbMezziField.getSelectionModel().getSelectedItem());
                                            return true;
                                        } catch (SpedizioniException ex) {
                                            return false;
                                        }
                                    }
                                };

                                operatoreIniziaLavPackagingTask.valueProperty().addListener(giveFeedback);

                                ExecutorService executorService = Executors.newSingleThreadExecutor(); // newFixedThreadPool(2);
                                executorService.submit(operatoreIniziaLavPackagingTask);
                                executorService.shutdown();
                            });
                } catch (SpedizioniException e) {
                    odlOrchestrator.manageError("Errore", "Errore nel caricamento dei mezzi di trasporto liberi oggi");
                    return;
                }
            });
        }

        setupGrid();

        dettGrid.autosizeColumnsOnInitialization();




        try {
            odlOrchestrator.ordineSpedizionePaginaPronta(this, ordineSpedizioneModel.getFiliale(), ordineSpedizioneModel.getSpedizione());
        } catch (SpedizioniException e) {
            odlOrchestrator.manageError("Errore", "Errore nel caricamento dei pacchi");
        }
    }

    public void setGridPacchi(List<PaccoDTO> pacchi) {
        dettGrid.getItems().clear();
        dettGrid.setItems(FXCollections.observableArrayList(pacchi));

    }

    private void setupGrid() {
        MFXTableColumn<PaccoDTO> codicePaccoColumn = new MFXTableColumn<>("Codice Pacco", false, Comparator.comparing(PaccoDTO::getCodicePacco));
        MFXTableColumn<PaccoDTO> pesoColumn = new MFXTableColumn<>("Peso", false, Comparator.comparing(PaccoDTO::getPeso));

        codicePaccoColumn.setRowCellFactory(paccoDTO -> new MFXTableRowCell<>(PaccoDTO::getCodicePacco));
        pesoColumn.setRowCellFactory(paccoDTO -> new MFXTableRowCell<>(PaccoDTO::getPeso));

        dettGrid.getTableColumns().addAll(codicePaccoColumn, pesoColumn);

        dettGrid.getFilters().addAll(
                new StringFilter<>("Codice Pacco", PaccoDTO::getCodicePacco),
                new FloatFilter<>("Peso", PaccoDTO::getPeso)
        );
    }

    private void prepareTasks() {
        dashboardController = (DashboardController) dashboardStage.getScene().getUserData();

        //Prepariamoci a gestire il feedback da dare all'utente in caso di esito positivo o negativo.
        //Il seguente è un event handler generico che sarà poi assegnato più avanti ai vari listner
        giveFeedback = (observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue) { //se newValue è true vuol dire che è andato tutto bene (quale che sia il task)
                    dashboardController.showDialog("info", "Operazione completata", "L'operazione è stata completata con successo");
                    //ricarico la pagina
                    try {
                        odlOrchestrator.ordineSpedizioneRicaricami(ordineSpedizioneModel);
                    } catch (SpedizioniException e) {
                        e.printStackTrace();
                        //non voglio in questo caso notificare qualcosa all'utente
                    }
                } else {
                    dashboardController.showDialog("error", "Errore", "L'operazione non è andata a buon fine.");
                }
            });
        };

        operatoreAssegnaAMeTask = new Task<>() {
            @Override
            protected Boolean call() {
                try {
                    odlOrchestrator.assegnaOdlTrasportoClicked(ordineSpedizioneModel.getSpedizione(), ordineSpedizioneModel.getFiliale(), (OperatoreCorriereDTO) utente);
                    return true;
                } catch (SpedizioniException ex) {
                    return false;
                }
            }
        };

        operatoreAssegnaAMeTask.valueProperty().addListener(giveFeedback);
    }

    private MFXStageDialog ConfigureNotePopup(String title, String domanda, String promptText, EventHandler<MouseEvent> procedeAction) {

        // Crea la label
        Label label = new Label(domanda);
        label.setWrapText(true);
        label.setPrefWidth(250);

        // Crea il campo di testo
        TextArea textField = new TextArea();
        textField.setId("noteTextField");
        textField.setPromptText(promptText);

        //Mi serve impostare un limite a 512 caratteri
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 512) {
                    textField.setText(oldValue);
                }
            }
        });

        textField.setPrefWidth(300);
        textField.getStyleClass().add("textAreaCustom");

        // Crea il pulsante
        //MFXButton proceedButton = new MFXButton("Procedi");
        VBox dialogContent = new VBox(label, textField);//, proceedButton);
        dialogContent.setSpacing(15);
        popupNoteContent = MFXGenericDialogBuilder.build()
                .setContent(dialogContent)
                .get();

        popupNoteDialog = MFXGenericDialogBuilder.build(popupNoteContent)
                .toStageDialogBuilder()
                .initOwner(rootAnchorPane.getScene().getWindow())
                .initModality(Modality.APPLICATION_MODAL)
                .setDraggable(true)
                .setTitle(title)
                .setScrimPriority(ScrimPriority.WINDOW)
                .setScrimOwner(true)
                .get();

        //Definisco i pulsanti della dialog
        MFXButton btnProcedi = new MFXButton("Procedi");
        btnProcedi.setPrefWidth(80.0);
        MFXButton btnAnnulla = new MFXButton("Annulla");
        btnAnnulla.setPrefWidth(80.0);

        // Mi creo un handler perché voglio richiamare procedeAction ma prima voglio chiudere la dialog
        EventHandler<MouseEvent> closeProcedeAction = event -> {
            popupNoteDialog.close();
            Platform.runLater(() -> procedeAction.handle(event));
        };

        popupNoteContent.addActions(
                Map.entry(btnAnnulla, event -> popupNoteDialog.close()),
                Map.entry(btnProcedi, closeProcedeAction)
        );

        popupNoteContent.setMaxSize(400, 200);

        // Mostra il dialog
        return popupNoteDialog;
    }


    private MFXStageDialog ConfigureMezziPopup(String title, String domanda, String promptText, ObservableList<MezzoDiTrasportoDTO> mezziObsList, EventHandler<MouseEvent> procedeAction) {

        // Crea la label
        Label label = new Label(domanda);
        label.setWrapText(true);
        label.setPrefWidth(250);

        // Crea il campo di testo
        MFXFilterComboBox<MezzoDiTrasportoDTO> cmbMezziField = new MFXFilterComboBox<>();
        cmbMezziField.setId("cmbMezziField");
        cmbMezziField.setPromptText(promptText);

        StringConverter<MezzoDiTrasportoDTO> converter = FunctionalStringConverter.to(mezzo -> (mezzo == null) ? "" : mezzo.getTarga() + " " + mezzo.getTipo());
        Function<String, Predicate<MezzoDiTrasportoDTO>> filterFunction = s -> mezzo -> StringUtils.containsIgnoreCase(converter.toString(mezzo), s);

        cmbMezziField.setItems(mezziObsList);
        cmbMezziField.setConverter(converter);
        cmbMezziField.setFilterFunction(filterFunction);
        cmbMezziField.setResetOnPopupHidden(false);

        cmbMezziField.setPrefWidth(300);
        cmbMezziField.getStyleClass().add("textAreaCustom");

        // Crea il pulsante
        //MFXButton proceedButton = new MFXButton("Procedi");
        VBox dialogContent = new VBox(label, cmbMezziField);//, proceedButton);
        dialogContent.setSpacing(15);
        popupMezziContent = MFXGenericDialogBuilder.build()
                .setContent(dialogContent)
                .get();

        popupMezziDialog = MFXGenericDialogBuilder.build(popupMezziContent)
                .toStageDialogBuilder()
                .initOwner(rootAnchorPane.getScene().getWindow())
                .initModality(Modality.APPLICATION_MODAL)
                .setDraggable(true)
                .setTitle(title)
                .setScrimPriority(ScrimPriority.WINDOW)
                .setScrimOwner(true)
                .get();

        //Definisco i pulsanti della dialog
        MFXButton btnProcedi = new MFXButton("Procedi");
        btnProcedi.setPrefWidth(80.0);
        MFXButton btnAnnulla = new MFXButton("Annulla");
        btnAnnulla.setPrefWidth(80.0);

        // Mi creo un handler perché voglio richiamare procedeAction ma prima voglio chiudere la dialog
        EventHandler<MouseEvent> closeProcedeAction = event -> {
            popupMezziDialog.close();
            Platform.runLater(() -> procedeAction.handle(event));
        };

        popupMezziContent.addActions(
                Map.entry(btnAnnulla, event -> popupMezziDialog.close()),
                Map.entry(btnProcedi, closeProcedeAction)
        );

        popupMezziContent.setMaxSize(400, 200);

        // Mostra il dialog
        return popupMezziDialog;
    }

}
