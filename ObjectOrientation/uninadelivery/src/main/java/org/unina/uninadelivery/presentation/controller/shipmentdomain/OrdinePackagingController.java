package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.filter.FloatFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDetailDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.shipmentdomain.OrdinePackagingModel;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrdinePackagingController implements Initializable {
    private final OrdinePackagingModel ordinePackagingModel;
    private final UtenteDTO utente;
    private final Stage dashboardStage;
    private final OdlOrchestrator odlOrchestrator;
    private ChangeListener<? super Boolean> giveFeedback;
    private DashboardController dashboardController;

    @FXML
    protected AnchorPane rootAnchorPane;

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
    protected MFXTableView dettGrid;

    private MFXGenericDialog popupNoteContent;
    private MFXStageDialog popupNoteDialog;

    private Task<Boolean> operatoreAssegnaAMeTask;
    private Task<Boolean> operatoreInizaLavPackagingTask;

    public OrdinePackagingController(OrdinePackagingModel ordinePackagingModel) {
        this.ordinePackagingModel = ordinePackagingModel;

        //questi sono dati immutabili, quindi li prendo subito
        Session session = Session.getInstance();
        dashboardStage = (Stage) session.getSessionData("dashboardStage");
        utente = session.getUserDto().getValue();

        odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);
    }

    //preparo tutti i task che potrebbero poi essere eseguiti dall'actionButton
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Preparo i task che possono esssere eseguiti dall'actionButton generico, utilizzerò poi quello
        //corretto in base al model
        prepareTasks();

        lblNumeroSpedizione.setText(String.valueOf(ordinePackagingModel.getSpedizione().getId()));
        lblTrackingNum.setText(ordinePackagingModel.getSpedizione().getTrackingNumber());
        lblRagSoc.setText(ordinePackagingModel.getCliente());
        lblStato.setText(ordinePackagingModel.getStato());
        lblOrganizzatore.setText(ordinePackagingModel.getOrganizzatore());
        lblDataCreazione.setText(ordinePackagingModel.getDataCreazione().toString());
        lblDataInizioLav.setText(ordinePackagingModel.getDataInizioLavorazione() != null ? ordinePackagingModel.getDataInizioLavorazione().toString() : "N/A");
        lblDataFineLav.setText(ordinePackagingModel.getDataFineLavorazione() != null ? ordinePackagingModel.getDataFineLavorazione().toString() : "N/A");

        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();
        Stage dashboardStage = (Stage) session.getSessionData("dashboardStage");
        dashboardController = (DashboardController) dashboardStage.getScene().getUserData();

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
                        ExecutorService executorService = Executors.newSingleThreadExecutor(); // newFixedThreadPool(2);
                        executorService.submit(operatoreAssegnaAMeTask);
                        executorService.shutdown();
                    });
                }
                else {
                    actionLabel.setVisible(true);
                    actionLabel.setText("L'ordine non è assegnato a nessun corriere.");
                }
                break;
            case "Assegnato":
                if (utente.getProfilo().equals("OperatoreCorriere")) {
                    actionLabel.setVisible(true);
                    actionButton.setVisible(true);
                    actionLabel.setText("L'ordine è assegnato, iniziare la lavorazione?");
                    actionButton.setText("Inizia lavorazione");
                    actionButton.setOnAction(event -> {
                        ExecutorService executorService = Executors.newSingleThreadExecutor(); // newFixedThreadPool(2);
                        executorService.submit(operatoreInizaLavPackagingTask);
                        executorService.shutdown();
                    });
                }
                else {
                    actionLabel.setVisible(true);
                    actionLabel.setText("L'ordine è assegnato, l'operatore del corriere dovrà cominciare a lavorare l'ordine.");
                }
                break;
            case "InLavorazione":
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
                }
                else {
                    actionLabel.setVisible(true);
                    actionLabel.setText("L'ordine è in lavorazione, solo l'operatore del corriere che ha in carico l'ordine può concluderlo.");
                }
                break;
                case "Lavorato": {
                    actionLabel.setVisible(true);
                    actionButton.setVisible(true);
                    actionLabel.setText("L'ordine è stato lavorato, clicca qui per vedere i pacchi generati ->");
                    actionButton.setText("Visualizza pacchi");
                    actionButton.setOnAction(event -> {
                        try {
                            odlOrchestrator.visualizzaPacchiClicked(ordinePackagingModel.getOrdine());
                        } catch (SpedizioniException e) {
                            odlOrchestrator.manageError("Errore visualizzazione pacchi", e.getMessage());
                        }
                    });
                }
                break;
            default:
                actionLabel.setVisible(false);
                actionButton.setVisible(false);
        }

        Platform.runLater(() -> {
            ConfigureNotePopup("Concludi lavorazione", "Hai note da segnalare?",
                    "Inserisci le note qui...", event1 -> {
                        MFXButton procede = (MFXButton) event1.getSource();
                        TextArea noteTextField = (TextArea) procede.getScene().lookup("#noteTextField");
                        Task<Boolean> operatoreConcludiLavTask = new Task<>() {
                            @Override
                            protected Boolean call() {
                                try {
                                    odlOrchestrator.concludiLavorazioneOdlPackagingClicked(ordinePackagingModel.getMagazzino(), ordinePackagingModel.getSpedizione(), noteTextField.getText());
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
        //Inizializziamo la tabella dei dettagli

        setupGrid();

        dettGrid.autosizeColumnsOnInitialization();

    }

    private void setupGrid() {
        MFXTableColumn<OrdineDiLavoroPackagingDetailDTO> prodotto = new MFXTableColumn<>("Prodotto", false, Comparator.comparing(dettaglio -> dettaglio.getMerceStoccata().getProdotto().getNome()));
        MFXTableColumn<OrdineDiLavoroPackagingDetailDTO> quantita = new MFXTableColumn<>("Quantità", false, Comparator.comparing(dettaglio -> dettaglio.getDettaglioOrdineCliente().getQuantita()));
        MFXTableColumn<OrdineDiLavoroPackagingDetailDTO> peso = new MFXTableColumn<>("Peso Unitario", false, Comparator.comparing(dettaglio -> dettaglio.getMerceStoccata().getProdotto().getPeso()));
        MFXTableColumn<OrdineDiLavoroPackagingDetailDTO> pericolosita = new MFXTableColumn<>("Pericolosità", false, Comparator.comparing(OrdineDiLavoroPackagingDetailDTO::getPericolosita));
        MFXTableColumn<OrdineDiLavoroPackagingDetailDTO> codicePropostaPacco = new MFXTableColumn<>("Codice Proposta Pacco", false, Comparator.comparing(OrdineDiLavoroPackagingDetailDTO::getCodicePropostaPacco));
        MFXTableColumn<OrdineDiLavoroPackagingDetailDTO> magazzino = new MFXTableColumn<>("Magazzino", false, Comparator.comparing(dettaglio -> dettaglio.getMerceStoccata().getMagazzino().getNome().replace("Magazzino ", "") + "(" + dettaglio.getMerceStoccata().getSettoreMagazzino() + ")"));

        prodotto.setRowCellFactory(dettaglio -> new MFXTableRowCell<>(dett -> dett.getMerceStoccata().getProdotto().getNome()));
        quantita.setRowCellFactory(dettaglio -> new MFXTableRowCell<>(dett -> dett.getDettaglioOrdineCliente().getQuantita()));
        peso.setRowCellFactory(dettaglio -> new MFXTableRowCell<>(dett -> dett.getMerceStoccata().getProdotto().getPeso()));
        pericolosita.setRowCellFactory(dettaglio -> new MFXTableRowCell<>(OrdineDiLavoroPackagingDetailDTO::getPericolosita));
        codicePropostaPacco.setRowCellFactory(dettaglio -> new MFXTableRowCell<>(OrdineDiLavoroPackagingDetailDTO::getCodicePropostaPacco));
        magazzino.setRowCellFactory(dettaglio -> new MFXTableRowCell<>(dett -> dett.getMerceStoccata().getMagazzino().getNome().replace("Magazzino ", "") + "(" + dett.getMerceStoccata().getSettoreMagazzino() + ")"));

        dettGrid.getTableColumns().addAll(prodotto, quantita, peso, pericolosita, codicePropostaPacco, magazzino);
        dettGrid.getFilters().addAll(
                new StringFilter<OrdineDiLavoroPackagingDetailDTO>("Prodotto", dettaglio -> dettaglio.getMerceStoccata().getProdotto().getNome()),
                new IntegerFilter<OrdineDiLavoroPackagingDetailDTO>("Quantità", dettaglio -> dettaglio.getDettaglioOrdineCliente().getQuantita()),
                new FloatFilter<OrdineDiLavoroPackagingDetailDTO>("Peso Unitario", dettaglio -> dettaglio.getMerceStoccata().getProdotto().getPeso()),
                new StringFilter<>("Pericolosità", OrdineDiLavoroPackagingDetailDTO::getPericolosita),
                new IntegerFilter<>("Codice Proposta Pacco", OrdineDiLavoroPackagingDetailDTO::getCodicePropostaPacco),
                new StringFilter<OrdineDiLavoroPackagingDetailDTO>("Magazzino", dettaglio -> dettaglio.getMerceStoccata().getMagazzino().getNome().replace("Magazzino ", "") + "(" + dettaglio.getMerceStoccata().getSettoreMagazzino() + ")")
        );

        try {
            odlOrchestrator.ordinePackagingPronta(this, ordinePackagingModel.getOrdine());
        } catch (SpedizioniException e) {
            odlOrchestrator.manageError("Errore", "Errore nel caricamento dei pacchi");
        }
    }

    public void setDettGridItems(List<OrdineDiLavoroPackagingDetailDTO> dettList) {
        dettGrid.getItems().clear();
        dettGrid.setItems(FXCollections.observableArrayList(dettList));

    }

    private void prepareTasks() {
        dashboardController = (DashboardController) dashboardStage.getScene().getUserData();

        //Prepariamoci a gestire il feedback da dare all'utente in caso di esito positivo o negativo.
        //Il seguente è un event handler generico che sarà poi assegnato più avanti ai vari listner
        giveFeedback = (observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue) {
                    dashboardController.showDialog("info", "Operazione completata", "L'operazione è stata completata con successo");
                    //ricarico la pagina
                    try {
                        odlOrchestrator.ordinePackagingRicaricami(ordinePackagingModel);
                    } catch (SpedizioniException e) {
                        e.printStackTrace();
                        //non voglio in questo caso notificare qualcosa all'utente
                    }
                } else {
                    dashboardController.showDialog("error", "Errore", "L'operazione non è andata a buon fine.");
                }
            });
        };

        operatoreAssegnaAMeTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                try {
                    odlOrchestrator.assegnaOdlPackagingClicked(
                            ordinePackagingModel.getMagazzino(),
                            ordinePackagingModel.getSpedizione(),
                            (OperatoreCorriereDTO) utente);
                    return true;
                } catch (SpedizioniException ex) {
                    return false;
                }
            }
        };

        operatoreAssegnaAMeTask.valueProperty().addListener(giveFeedback);

        operatoreInizaLavPackagingTask = new Task<>() {
            @Override
            protected Boolean call() {
                try {
                    odlOrchestrator.iniziaLavorazioneOdlPackagingClicked(ordinePackagingModel.getMagazzino(), ordinePackagingModel.getSpedizione());
                    return true;
                } catch (SpedizioniException ex) {
                    return false;
                }
            }
        };

        operatoreInizaLavPackagingTask.valueProperty().addListener(giveFeedback);
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

}
