package org.unina.uninadelivery.presentation.controller.orgdomain;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.HomeOrchestrator;
import org.unina.uninadelivery.presentation.exception.HomeException;
import org.unina.uninadelivery.presentation.helper.Session;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class HomeOpCorriereController implements Initializable {
    private final Stage dashboardStage;
    private final OperatoreCorriereDTO operatoreCorriereDTO;

    private final HomeOrchestrator homeOrchestrator;

    @FXML
    protected Label lblCntOrdiniPackagingDaPrendereInCarico;
    @FXML
    protected Label lblOrdiniPackagingDaTerminare;
    @FXML
    protected Label lblOrdiniTrasportoDaPrendereInCarico;
    @FXML
    protected Label lblCntOrdiniTrasportoDaTerminare;
    private Task<Integer> cntPackagingDaPrendereInCaricoTask;
    private Task<Integer> cntPackagingDaTerminareTask;
    private Task<Integer> cntTrasportoDaPrendereInCaricoTask;
    private Task<Integer> cntTrasportoDaTerminareTask;
    private List<Task<Integer>> tasks;

    public HomeOpCorriereController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
        Session session = Session.getInstance();
        operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();
        homeOrchestrator = new HomeOrchestrator(dashboardStage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Imposto i task per il conteggio degli ordini
        //AtomicBoolean è un boolean condiviso tra i thread in maniera safe. Lo uso per eseguire il taskRunning solo la prima volta
        setupTasks();
    }

    private void setupTasks() {
        AtomicBoolean isProcessingStarted = new AtomicBoolean(false);

        try {
            cntPackagingDaPrendereInCaricoTask = homeOrchestrator.homeOpCorriereSetupOrdiniDiLavoroPackagingDaPrendereInCarico(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
        } catch (HomeException e) {
            homeOrchestrator.manageError("Errore ricezione conteggi", e);
            return;
        }

        /*cntPackagingDaPrendereInCaricoTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(() -> homeOrchestrator.taskRunning());
            }
        });*/

        cntPackagingDaPrendereInCaricoTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer result = (Integer) event.getSource().getValue();
                lblCntOrdiniPackagingDaPrendereInCarico.setText(String.valueOf(result));
            });
        });

        cntPackagingDaPrendereInCaricoTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                homeOrchestrator.taskCompleted();
                if (cntPackagingDaPrendereInCaricoTask.getException() instanceof ServiceException)
                    homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException(cntPackagingDaPrendereInCaricoTask.getException().getMessage()));
                else
                    homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
            });
        });

        try {
            cntPackagingDaTerminareTask = homeOrchestrator.homeOpCorriereSetupOrdiniDiLavoroPackagingDaTerminare(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
        } catch (HomeException e) {
            homeOrchestrator.manageError("Errore ricezione conteggi", e);
            return;
        }

        /*cntPackagingDaTerminareTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(() -> homeOrchestrator.taskRunning());
            }
        });*/

        cntPackagingDaTerminareTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer result = (Integer) event.getSource().getValue();
                lblOrdiniPackagingDaTerminare.setText(String.valueOf(result));
            });
        });

        cntPackagingDaTerminareTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                homeOrchestrator.taskCompleted();
                if (cntPackagingDaTerminareTask.getException() instanceof ServiceException)
                    homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException(cntPackagingDaTerminareTask.getException().getMessage()));
                else
                    homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
            });
        });

        try {
            cntTrasportoDaPrendereInCaricoTask = homeOrchestrator.homeOpCorriereSetupOrdiniDiLavoroTrasportoDaPrendereInCarico(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
        } catch (HomeException e) {
            homeOrchestrator.manageError("Errore ricezione conteggi", e);
            return;
        }

        /*cntTrasportoDaPrendereInCaricoTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(() -> homeOrchestrator.taskRunning());
            }
        });*/

        cntTrasportoDaPrendereInCaricoTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer result = (Integer) event.getSource().getValue();
                lblOrdiniTrasportoDaPrendereInCarico.setText(String.valueOf(result));
            });
        });

        cntTrasportoDaPrendereInCaricoTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                homeOrchestrator.taskCompleted();
                if (cntTrasportoDaPrendereInCaricoTask.getException() instanceof ServiceException)
                    homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException(cntTrasportoDaPrendereInCaricoTask.getException().getMessage()));
                else
                    homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
            });
        });

        try {
            cntTrasportoDaTerminareTask = homeOrchestrator.homeOpCorriereSetupOrdiniDiLavoroSpedizioneDaTerminare(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
        } catch (HomeException e) {
            homeOrchestrator.manageError("Errore ricezione conteggi", e);
            return;
        }

        /*cntTrasportoDaTerminareTask.setOnRunning(event -> {
            if (isProcessingStarted.compareAndSet(false, true)) {
                Platform.runLater(() -> homeOrchestrator.taskRunning());
            }
        });*/

        cntTrasportoDaTerminareTask.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                Integer newValue = (Integer) event.getSource().getValue();
                lblCntOrdiniTrasportoDaTerminare.setText(String.valueOf(newValue));
            });
        });

        cntTrasportoDaTerminareTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                homeOrchestrator.taskCompleted();
                if (cntTrasportoDaTerminareTask.getException() instanceof ServiceException)
                    homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException(cntTrasportoDaTerminareTask.getException().getMessage()));
                else
                    homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
            });
        });

        tasks = Arrays.asList(
                cntPackagingDaPrendereInCaricoTask,
                cntPackagingDaTerminareTask,
                cntTrasportoDaPrendereInCaricoTask,
                cntTrasportoDaTerminareTask
        );
    }

    public void updateData() {
        try {
            homeOrchestrator.taskRunning();

            //Eseguo i task
            ExecutorService executorService = Executors.newFixedThreadPool(6);

            CompletableFuture<Void> allOf = CompletableFuture.allOf(
                    tasks.stream()
                            .map(task -> CompletableFuture.runAsync(task, executorService)
                                    .exceptionally(ex -> {
                                        System.out.println("Task failed with exception: " + ex);
                                        Platform.runLater(homeOrchestrator::taskCompleted);
                                        return null;
                                    })
                                    .thenRun(() -> System.out.println("Task completed")))
                            .toArray(CompletableFuture[]::new));

            //In questo modo sto gestendo in maniera asincrona il completamento di tutti i task
            allOf.thenRunAsync(() -> {
                System.out.println("All tasks completed");
                Platform.runLater(()->homeOrchestrator.taskCompleted());
                //executorService.shutdown();
            });
        } catch (Exception e) {
            e.printStackTrace();
            homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
        }
    }
}
