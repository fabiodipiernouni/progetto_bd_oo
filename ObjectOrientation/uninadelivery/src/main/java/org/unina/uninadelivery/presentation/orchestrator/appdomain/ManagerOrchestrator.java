package org.unina.uninadelivery.presentation.orchestrator.appdomain;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.customerdomain.CustomerService;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.presentation.controller.DashboardController;
import org.unina.uninadelivery.presentation.controller.customerdomain.OrdineController;
import org.unina.uninadelivery.presentation.controller.orgdomain.HomeManagerController;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;

import java.time.LocalDate;
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
            homeManagerController.setNumeroTotaleOrdini(newValue);
        });
        numeroTotaleOrdiniTask.setOnFailed(event -> {
            //TODO: gestire eccezione
            numeroTotaleOrdiniTask.getException().printStackTrace();
        });

       Task<Float> numeroMedioOrdiniTask = customerService.getNumeroMedioOrdini(dataInizio, dataFine);
        numeroMedioOrdiniTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            homeManagerController.setNumeroMedioOrdini(newValue);
        });
        numeroMedioOrdiniTask.setOnFailed(event -> {
            //TODO: gestire eccezione
            numeroMedioOrdiniTask.getException().printStackTrace();
        });

        Task<Integer> numeroSpedizioniCreateTask = shipmentService.getNumeroSpedizioniCreate(dataInizio, dataFine);
        numeroSpedizioniCreateTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            homeManagerController.setNumeroSpedizioniCreate(newValue);
        });
        numeroSpedizioniCreateTask.setOnFailed(event -> {
            //TODO: gestire eccezione
            numeroSpedizioniCreateTask.getException().printStackTrace();
        });

        Task<Integer> numeroSpedizioniCompletateTask = shipmentService.getNumeroSpedizioniCompletate(dataInizio, dataFine);
        numeroSpedizioniCompletateTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            homeManagerController.setNumeroSpedizioniCompletate(newValue);
        });
        numeroSpedizioniCompletateTask.setOnFailed(event -> {
            //TODO: gestire eccezione
            numeroSpedizioniCompletateTask.getException().printStackTrace();
        });

        Task<Optional<OrdineClienteDTO> > ordineMaggiorNumeroProdottiTask = customerService.getOrdineMaggiorNumeroProdotti(dataInizio, dataFine);
        ordineMaggiorNumeroProdottiTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            homeManagerController.setOrdineMaggiorNumeroProdotti(newValue.orElse(null));
        });
        ordineMaggiorNumeroProdottiTask.setOnFailed(event -> {
            //TODO: gestire eccezione
            ordineMaggiorNumeroProdottiTask.getException().printStackTrace();
        });

        Task<Optional<OrdineClienteDTO>> ordineMinorNumeroProdottiTask = customerService.getOrdineMinorNumeroProdotti(dataInizio, dataFine);
        ordineMinorNumeroProdottiTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            homeManagerController.setOrdineMinorNumeroProdotti(newValue.orElse(null));
        });
        ordineMinorNumeroProdottiTask.setOnFailed(event -> {
            //TODO: gestire eccezione
            ordineMinorNumeroProdottiTask.getException().printStackTrace();
        });

        //Eseguo i task
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(numeroTotaleOrdiniTask);
        executorService.submit(numeroMedioOrdiniTask);
        executorService.submit(numeroSpedizioniCreateTask);
        executorService.submit(numeroSpedizioniCompletateTask);
        executorService.submit(ordineMaggiorNumeroProdottiTask);
        executorService.submit(ordineMinorNumeroProdottiTask);

    }

    public void visualizzaOrdine(OrdineClienteDTO ordine) {

        OrdineController ordineController = new OrdineController(dashboardStage, ordine);
        DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        dashboardController.changeView("ORDINE", "/views/customerdomain/ordine-view.fxml", c-> ordineController);

    }
}
