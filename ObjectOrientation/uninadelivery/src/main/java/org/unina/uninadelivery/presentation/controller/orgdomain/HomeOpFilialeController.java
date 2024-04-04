package org.unina.uninadelivery.presentation.controller.orgdomain;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.presentation.orchestrator.appdomain.HomeOrchestrator;
import org.unina.uninadelivery.presentation.exception.HomeException;
import org.unina.uninadelivery.presentation.helper.Session;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeOpFilialeController implements Initializable {
    final OperatoreFilialeDTO operatoreFilialeDTO;

    private final Stage dashboardStage;


    @FXML
    public AnchorPane mainAnchorPane;
    @FXML
    public Label lblCntOrdiniClienteFiliale;

    public Label lblCntOrdiniClienteFiliale1;
    @FXML
    public Label lblSpedizioniDaLavorare;
    @FXML

    public Label lblOrdiniPackagingDaTrasportare;
    @FXML
    public Label lblCntOrdiniTrasportoNonConclusi;
    private Task<Integer> cntOrdiniTrasportoNonConclusiAsync;
    private Task<Integer> cntSpedizioniDaLavorareAsync;
    private Task<Integer> cntOrdiniPackagingDaTrasportareAsync;
    private Task<Integer> cntOrdiniDaLavorareTaskAsync;
    
    private final HomeOrchestrator homeOrchestrator;

    public HomeOpFilialeController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;

        Session session = Session.getInstance();
        operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();
        homeOrchestrator = new HomeOrchestrator(dashboardStage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Richiedo al controller di effettuare il setup dei task per i conteggi

        try {
            cntOrdiniDaLavorareTaskAsync = homeOrchestrator.homeOpFilialeSetupOrdiniDaLavorare(operatoreFilialeDTO.getFiliale());
        } catch (HomeException e) {
            homeOrchestrator.manageError("Errore ricezione conteggi", e);
            return;
        }

        cntOrdiniDaLavorareTaskAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblCntOrdiniClienteFiliale.setText(newValue.toString());
        });

        cntOrdiniDaLavorareTaskAsync.setOnFailed(event -> {
            System.out.println("Task failed...");
            if (cntOrdiniDaLavorareTaskAsync.getException() instanceof ServiceException)
                homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException(cntOrdiniDaLavorareTaskAsync.getException().getMessage()));
            else
                homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
        });

        //Setup task per il conteggio delle spedizioni da lavorare
        try {
            cntSpedizioniDaLavorareAsync = homeOrchestrator.homeOpFilialeSetupSpedizioniDaLavorare(operatoreFilialeDTO);
        } catch (HomeException e) {
            homeOrchestrator.manageError("Errore ricezione conteggi", e);
            return;
        }
        cntSpedizioniDaLavorareAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblSpedizioniDaLavorare.setText(newValue.toString());
        });

        cntSpedizioniDaLavorareAsync.setOnFailed(event -> {
            System.out.println("Task failed...");
            if (cntSpedizioniDaLavorareAsync.getException() instanceof ServiceException)
                homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException(cntSpedizioniDaLavorareAsync.getException().getMessage()));
            else
                homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
        });

        //Setup task per il conteggio degli ordini di packaging conclusi in attesa di trasporto
        try {
            cntOrdiniPackagingDaTrasportareAsync = homeOrchestrator.homeOpFilialeSetupOrdiniDiLavoroPackagingConclusiAttesaTrasporto(operatoreFilialeDTO.getFiliale());
        } catch (HomeException e) {
            homeOrchestrator.manageError("Errore ricezione conteggi", e);
            return;
        }

        cntOrdiniPackagingDaTrasportareAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblOrdiniPackagingDaTrasportare.setText(newValue.toString());
        });

        cntOrdiniPackagingDaTrasportareAsync.setOnFailed(event -> {
            System.out.println("Task failed...");
            if (cntOrdiniPackagingDaTrasportareAsync.getException() instanceof ServiceException)
                homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException(cntOrdiniPackagingDaTrasportareAsync.getException().getMessage()));
            else
                homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
        });

        //Setup task per il conteggio degli ordini di trasporto non conclusi
        try {
            cntOrdiniTrasportoNonConclusiAsync = homeOrchestrator.homeOpFilialeSetupOrdiniDiLavoroSpedizioneDaTerminare(operatoreFilialeDTO.getFiliale());
        } catch (HomeException e) {
            homeOrchestrator.manageError("Errore ricezione conteggi", e);
            return;
        }

        cntOrdiniTrasportoNonConclusiAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblCntOrdiniTrasportoNonConclusi.setText(newValue.toString());
        });

        cntOrdiniTrasportoNonConclusiAsync.setOnFailed(event -> {
            System.out.println("Task failed...");
            if (cntOrdiniTrasportoNonConclusiAsync.getException() instanceof ServiceException)
                homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException(cntOrdiniTrasportoNonConclusiAsync.getException().getMessage()));
            else
                homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
        });
    }

    public void updateData() {
        //Eseguo i task
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            executorService.submit(cntOrdiniDaLavorareTaskAsync);
            executorService.submit(cntSpedizioniDaLavorareAsync);
            executorService.submit(cntOrdiniTrasportoNonConclusiAsync);
            executorService.submit(cntOrdiniPackagingDaTrasportareAsync);
            executorService.shutdown();
        } catch (Exception e) {
            homeOrchestrator.manageError("Errore ricezione conteggi", new HomeException("Errore durante il conteggio degli ordini da lavorare"));
        }
    }
}
