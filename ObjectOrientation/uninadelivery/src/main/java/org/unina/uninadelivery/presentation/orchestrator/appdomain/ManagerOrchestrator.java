package org.unina.uninadelivery.presentation.orchestrator.appdomain;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.customerdomain.CustomerService;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdineController;
import org.unina.uninadelivery.presentation.controller.orgdomain.HomeManagerController;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ManagerOrchestrator extends Orchestrator {

    private ShipmentService shipmentService;
    private CustomerService customerService;

    private HomeManagerController homeManagerController;

    public ManagerOrchestrator(Stage dashboardStage, HomeManagerController homeManagerController) {
        super(dashboardStage);
        this.homeManagerController = homeManagerController;

        customerService = new CustomerService();
        shipmentService = new ShipmentService();
    }

    public void caricaStatistiche(LocalDate dataInizio, LocalDate dataFine) {
        Task<Integer> numeroTotaleOrdiniTask = customerService.getNumeroTotaleOrdini(dataInizio, dataFine);
        numeroTotaleOrdiniTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> homeManagerController.setNumeroTotaleOrdini(newValue));
        });
        numeroTotaleOrdiniTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                dashboardController.showDialog("error", "Errore caricamento statistica", "Errore nel recupero delle informazioni.");
                numeroTotaleOrdiniTask.getException().printStackTrace();
            });
        });

        Task<Float> numeroMedioOrdiniTask = customerService.getNumeroMedioOrdini(dataInizio, dataFine);
        numeroMedioOrdiniTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> homeManagerController.setNumeroMedioOrdini(newValue));
        });
        numeroMedioOrdiniTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                dashboardController.showDialog("error", "Errore caricamento statistica", "Errore nel recupero delle informazioni.");
                numeroMedioOrdiniTask.getException().printStackTrace();
            });
        });

        Task<Integer> numeroSpedizioniCreateTask = shipmentService.getNumeroSpedizioniCreate(dataInizio, dataFine);
        numeroSpedizioniCreateTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!Objects.equals(oldValue, newValue))
                Platform.runLater(() -> homeManagerController.setNumeroSpedizioniCreate(newValue));
        });
        numeroSpedizioniCreateTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                dashboardController.showDialog("error", "Errore caricamento statistica", "Errore nel recupero delle informazioni.");
                numeroSpedizioniCreateTask.getException().printStackTrace();
            });
        });

        Task<Integer> numeroSpedizioniCompletateTask = shipmentService.getNumeroSpedizioniCompletate(dataInizio, dataFine);
        numeroSpedizioniCompletateTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> homeManagerController.setNumeroSpedizioniCompletate(newValue));
        });
        numeroSpedizioniCompletateTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                dashboardController.showDialog("error", "Errore caricamento statistica", "Errore nel recupero delle informazioni.");
                numeroSpedizioniCompletateTask.getException().printStackTrace();
            });
        });

        Task<Optional<OrdineClienteDTO>> ordineMaggiorNumeroProdottiTask = customerService.getOrdineMaggiorNumeroProdotti(dataInizio, dataFine);
        ordineMaggiorNumeroProdottiTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> homeManagerController.setOrdineMaggiorNumeroProdotti(newValue.orElse(null)));
        });
        ordineMaggiorNumeroProdottiTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                dashboardController.showDialog("error", "Errore caricamento statistica", "Errore nel recupero delle informazioni.");
                ordineMaggiorNumeroProdottiTask.getException().printStackTrace();
            });
        });

        Task<Optional<OrdineClienteDTO>> ordineMinorNumeroProdottiTask = customerService.getOrdineMinorNumeroProdotti(dataInizio, dataFine);
        ordineMinorNumeroProdottiTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> homeManagerController.setOrdineMinorNumeroProdotti(newValue.orElse(null)));
        });
        ordineMinorNumeroProdottiTask.setOnFailed(event -> {
            Platform.runLater(() -> {
                dashboardController.showDialog("error", "Errore caricamento statistica", "Errore nel recupero delle informazioni.");
                ordineMinorNumeroProdottiTask.getException().printStackTrace();
            });
        });

        //Eseguo i task
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(numeroTotaleOrdiniTask);
        executorService.submit(numeroMedioOrdiniTask);
        executorService.submit(numeroSpedizioniCreateTask);
        executorService.submit(numeroSpedizioniCompletateTask);
        executorService.submit(ordineMaggiorNumeroProdottiTask);
        executorService.submit(ordineMinorNumeroProdottiTask);
        executorService.shutdown();
    }

    public void visualizzaOrdine(OrdineClienteDTO ordine) {
        OrdineController ordineController = new OrdineController(dashboardStage, ordine);
        DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        dashboardController.changeView("ORDINE", "/views/customerdomain/ordine-view.fxml", c -> ordineController);
    }

    public void wrongFilters() {
        dashboardController.showDialog("error", "Errore filtro", "La data di inizio non può essere successiva a quella di fine.");
    }
}
