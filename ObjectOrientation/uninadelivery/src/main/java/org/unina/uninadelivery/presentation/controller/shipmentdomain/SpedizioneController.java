package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.customerdomain.SpedizioneModel;
import org.unina.uninadelivery.presentation.orchestrator.shipmentdomain.OdlOrchestrator;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class SpedizioneController implements Initializable {

    private final Property<SpedizioneModel> spedizioneModel;
    @FXML
    public Label lblCntOrdiniTrasportoDaCompletare;

    @FXML
    protected Label lblCntOrdiniPackagingDaCompletare;
    @FXML
    protected Label lblCntPacchiDaSpedire;
    @FXML
    protected MFXButton pacchiButton;
    @FXML
    protected MFXButton odlTrasportoButton;
    @FXML
    protected MFXButton odlPackagingButton;
    @FXML
    protected ImageView imgCopyToTracking;
    @FXML
    protected Label lblNumeroSpedizione;
    @FXML
    protected Label lblRagSoc;
    @FXML
    protected Label lblStato;
    @FXML
    protected Label lblOrganizzatore;
    @FXML
    protected Label lblCntOrdiniPackagingEmessi;
    @FXML
    protected Label lblCntOrdiniTrasportoEmessi;
    @FXML
    protected Label lblCntPacchiGenerati;
    @FXML
    protected Label lblDataCreazione;
    @FXML
    protected Label lblDataInizioLav;
    @FXML
    protected Label lblDataFineLav;
    @FXML
    protected Label lblTrackingNum;
    private OdlOrchestrator odlOrchestrator;
    private DashboardController dashboardController;

    public SpedizioneController(SpedizioneModel spedizioneModel) {
        this.spedizioneModel = new SimpleObjectProperty<>();
        this.spedizioneModel.addListener(
                (observable, oldValue, newValue) -> {
                    Platform.runLater(() -> updateData(newValue));
                }
        );

        this.spedizioneModel.setValue(spedizioneModel);
    }

    private void updateData(SpedizioneModel model) {
        Session session = Session.getInstance();
        UtenteDTO utente = session.getUserDto().getValue();

        Stage dashboardStage = (Stage) session.getSessionData("dashboardStage");
        dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        odlOrchestrator = OdlOrchestrator.getOdlOrchestrator(dashboardStage);

        //Init delle label sfruttando il model
        lblNumeroSpedizione.setText(model.getNumeroSpedizione());
        lblRagSoc.setText(model.getRagioneSocialeCliente());
        lblStato.setText(model.getStato());
        lblOrganizzatore.setText(model.getOrganizzatore());
        lblTrackingNum.setText(model.getTrackingNumber());
        lblDataCreazione.setText(model.getDataCreazione().toString());

        if (model.getDataInizioLavorazione() != null) {
            lblDataInizioLav.setText(model.getDataInizioLavorazione().toString());
        } else {
            lblDataInizioLav.setText("N/A");
        }

        if (model.getDataFineLavorazione() != null) {
            lblDataFineLav.setText(model.getDataFineLavorazione().toString());
        } else {
            lblDataFineLav.setText("N/A");
        }

        //Gestisce i task ricevuti dal model e li esegue
        workTasks(model);

        imgCopyToTracking.setOnMouseClicked(e -> {
            javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
            javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
            content.putString(model.getTrackingNumber());
            clipboard.setContent(content);
            dashboardController.showDialog("info", "Tracking Number", "Tracking Number copiato negli appunti");
        });

        //verticolare rosa, ordini di packaging
        if (model.getStato().equals("DaLavorare")) {
            if (utente.getProfilo().equals("Operatore")) { //il manager non può generare ordini di lavoro
                odlPackagingButton.setText("Genera"); //Genera per tutte le filiali e non solo quella dell'operatore
                odlPackagingButton.setOnAction(null); //rimuovo l'azione precedente per evitare che venga eseguita più volte
                odlPackagingButton.setOnAction(e -> {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    try {
                        Task<Void> task = odlOrchestrator.generaOdlPackagingClicked(model.getOrdineCliente());
                        task.setOnRunning(event -> {
                            odlOrchestrator.taskRunning();
                        });
                        task.setOnSucceeded(event -> {
                            dashboardController.showDialog("info", "Generazione Ordini Packaging", "Ordini di Packaging generati con successo!");
                            odlOrchestrator.taskCompleted();
                            SpedizioneModel newModel = model.toBuilder()
                                    .stato("InLavorazionePackaging")
                                    .dataInizioLavorazione(LocalDate.now())
                                    .build();
                            spedizioneModel.setValue(newModel);
                        });
                        task.setOnFailed(event -> {
                            dashboardController.showDialog("error", "Generazione Ordini Packaging", "Errore nella generazione degli ordini di packaging");
                            odlOrchestrator.taskCompleted();
                        });

                        executorService.submit(task);
                    } catch (SpedizioniException ex) {
                        dashboardController.showDialog("error", "Generazione Ordini Packaging", ex.getMessage());
                    } finally {
                        executorService.shutdown();
                    }
                });
            } else {
                odlPackagingButton.setVisible(false);
            }
            pacchiButton.setVisible(false);
            odlTrasportoButton.setVisible(false);
        } else {
            //ordini di lavoro packaging possono essere solo visualizzati
            odlPackagingButton.setText("Visualizza");
            odlPackagingButton.setVisible(true);
            odlTrasportoButton.setOnAction(null); //rimuovo l'azione precedente per evitare che venga eseguita più volte
            odlPackagingButton.setOnAction(e -> {
                try {
                    odlOrchestrator.visualizzaOrdiniPackagingClicked(model.getOrdineCliente());
                } catch (SpedizioniException ex) {
                    dashboardController.showDialog("error", "Visualizzazione Ordini Packaging", ex.getMessage());
                }
            });

            odlTrasportoButton.setVisible(false);
        }

        //verticolare verde, ordini di trasporto
        if (model.getStato().equals("LavorataPackaging")) {
            odlTrasportoButton.setText("Genera");
            odlTrasportoButton.setVisible(true);
            odlTrasportoButton.setOnAction(null);
            odlTrasportoButton.setOnAction(e -> {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                try {
                    Task<Void> task = odlOrchestrator.generaOdlTrasportoClicked(model.getOrdineCliente());
                    task.setOnRunning(event -> {
                        Platform.runLater(() -> {
                            odlOrchestrator.taskRunning();
                        });
                    });
                    task.setOnSucceeded(event -> {
                        Platform.runLater(() -> {
                            SpedizioneModel newModel = model.toBuilder()
                                    .stato("InLavorazioneSpedizione")
                                    .build();
                            spedizioneModel.setValue(newModel);
                            dashboardController.showDialog("info", "Generazione Ordini Trasporto", "Ordini di Trasporto generati con successo!");
                            odlOrchestrator.taskCompleted();
                        });
                    });
                    task.setOnFailed(event -> {
                        Platform.runLater(() -> {
                            odlOrchestrator.taskCompleted();
                            dashboardController.showDialog("error", "Generazione Ordini Trasporto", "Errore nella generazione degli ordini di trasporto");
                        });
                    });

                    executorService.submit(task);
                } catch (SpedizioniException ex) {
                    dashboardController.showDialog("error", "Generazione Ordini Trasporto", ex.getMessage());
                } finally {
                    executorService.shutdown();
                }
            });
        } else if (model.getStato().equals("InLavorazioneSpedizione") || model.getStato().equals("LavorataSpedizione")) {
            odlTrasportoButton.setText("Visualizza");
            odlTrasportoButton.setVisible(true);
            odlTrasportoButton.setOnAction(null);
            odlTrasportoButton.setOnAction(e -> {
                try {
                    odlOrchestrator.visualizzaOrdiniTrasportoClicked(model.getSpedizioneDTO(), model.getFilialeDTO());
                } catch (SpedizioniException ex) {
                    dashboardController.showDialog("error", "Visualizzazione Ordini Trasporto", ex.getMessage());
                }
            });
        } else {
            odlTrasportoButton.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void workTasks(SpedizioneModel model) {
        //AtomicBoolean è un boolean condiviso tra i thread in maniera safe. Lo uso per eseguire il taskRunning solo la prima volta
        AtomicBoolean isProcessingStarted = new AtomicBoolean(false);

        Task<Integer> numeroOrdiniPackagingTask = model.getNumeroOrdiniPackaging();
        numeroOrdiniPackagingTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(() -> odlOrchestrator.taskRunning());
            }
        });
        numeroOrdiniPackagingTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer result = (Integer) event.getSource().getValue();
                lblCntOrdiniPackagingEmessi.setText(String.valueOf(result));
            });
        });

        numeroOrdiniPackagingTask.setOnFailed(event -> {
            System.out.println("Error in getNumeroOrdiniPackagingTask.");
        });

        Task<Integer> numeroOrdiniPackagingDaCompletareTask = model.getNumeroOrdiniPackagingDaCompletare();
        numeroOrdiniPackagingDaCompletareTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(odlOrchestrator::taskRunning);
            }
        });
        numeroOrdiniPackagingDaCompletareTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer result = (Integer) event.getSource().getValue();
                lblCntOrdiniPackagingDaCompletare.setText(String.valueOf(result));
            });
        });

        numeroOrdiniPackagingDaCompletareTask.setOnFailed(event -> {
            System.out.println("Error in getNumeroOrdiniPackagingDaCompletareTask.");
        });

        Task<Integer> numeroPacchiGeneratiTask = model.getNumeroPacchiGenerati();
        numeroPacchiGeneratiTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(odlOrchestrator::taskRunning);
            }
        });
        numeroPacchiGeneratiTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer result = (Integer) event.getSource().getValue();
                lblCntPacchiGenerati.setText(String.valueOf(result));
                if (result > 0) {
                    pacchiButton.setVisible(true);
                    pacchiButton.setText("Visualizza");
                    pacchiButton.setOnAction(e -> {
                        try {
                            SpedizioneDTO spedizione = new SpedizioneDTO();
                            spedizione.setId(Long.parseLong(model.getNumeroSpedizione()));

                            odlOrchestrator.visualizzaPacchiClicked(spedizione);
                        } catch (SpedizioniException ex) {
                            dashboardController.showDialog("error", "Visualizzazione Pacchi", ex.getMessage());
                        }
                    });
                } else {
                    pacchiButton.setVisible(false);
                }
            });
        });

        numeroPacchiGeneratiTask.setOnFailed(event -> {
            System.out.println("Error in getNumeroPacchiGeneratiTask.");
        });

        Task<Integer> numeroPacchiDaSpedireTask = model.getNumeroPacchiDaSpedire();
        numeroPacchiDaSpedireTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(odlOrchestrator::taskRunning);
            }
        });
        numeroPacchiDaSpedireTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer result = (Integer) event.getSource().getValue();
                lblCntPacchiDaSpedire.setText(String.valueOf(result));
            });
        });

        numeroPacchiDaSpedireTask.setOnFailed(event -> {
            System.out.println("Error in getNumeroPacchiDaSpedireTask.");
        });

        Task<Integer> numeroOrdiniTrasportoTask = model.getNumeroOrdiniTrasporto();
        numeroOrdiniTrasportoTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(odlOrchestrator::taskRunning);
            }
        });
        numeroOrdiniTrasportoTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer result = (Integer) event.getSource().getValue();
                lblCntOrdiniTrasportoEmessi.setText(String.valueOf(result));
            });
        });

        numeroOrdiniTrasportoTask.setOnFailed(event -> {
            System.out.println("Error in getNumeroOrdiniTrasportoTask.");
        });

        Task<Integer> numeroOrdiniTrasportoDaCompletareTask = model.getNumeroOrdiniTrasportoDaCompletare();
        numeroOrdiniTrasportoDaCompletareTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(odlOrchestrator::taskRunning);
            }
        });
        numeroOrdiniTrasportoDaCompletareTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer result = (Integer) event.getSource().getValue();
                lblCntOrdiniTrasportoDaCompletare.setText(String.valueOf(result));
            });
        });

        numeroOrdiniTrasportoDaCompletareTask.setOnFailed(event -> {
            System.out.println("Error in getNumeroOrdiniTrasportoDaCompletareTask.");
        });

        List<Task<Integer>> tasks = Arrays.asList(
                numeroOrdiniPackagingTask,
                numeroOrdiniPackagingDaCompletareTask,
                numeroPacchiGeneratiTask,
                numeroPacchiDaSpedireTask,
                numeroOrdiniTrasportoTask,
                numeroOrdiniTrasportoDaCompletareTask);

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                tasks.stream()
                        .map(task -> CompletableFuture.runAsync(task, executorService)
                                .exceptionally(ex -> {
                                    System.out.println("Task failed with exception: " + ex);
                                    return null;
                                })
                                .thenRun(() -> System.out.println("Task completed")))
                        .toArray(CompletableFuture[]::new));

        //In questo modo sto gestendo in maniera asincrona il completamento di tutti i task
        allOf.thenRunAsync(() -> {
            Platform.runLater(odlOrchestrator::taskCompleted);
            executorService.shutdown();
        }, executorService);
    }
}
