package org.unina.uninadelivery.presentation.controller.orgdomain;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.customerdomain.CustomerService;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentAsyncService;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.presentation.helper.Session;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeOpFilialeController implements Initializable {
    final CustomerService customerAsyncService;
    final ShipmentAsyncService shipmentAsyncService;
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

    public HomeOpFilialeController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
        customerAsyncService = new CustomerService();
        shipmentAsyncService = new ShipmentAsyncService();

        Session session = Session.getInstance();
        operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Setup task per il conteggio degli ordini da lavorare
        //TODO: dovremmo passare per un orchestratore, una boundary non può chiamare direttamente il service
        Task<Integer> cntOrdiniDaLavorareTaskAsync = customerAsyncService.getCountOrdiniDaLavorareTask(operatoreFilialeDTO.getFiliale());
        cntOrdiniDaLavorareTaskAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblCntOrdiniClienteFiliale.setText(newValue.toString());
        });
        cntOrdiniDaLavorareTaskAsync.setOnFailed(event -> {
            System.out.println("Task failed...");
            if (cntOrdiniDaLavorareTaskAsync.getException() instanceof ServiceException)
                System.out.println("...with a ServiceException");
            else
                System.out.println("...with an unknown exception");
        });
        //TODO: aggiungere gestione errori OnFailed per tutti i task (vedi esempio sopra)

        //Setup task per il conteggio delle spedizioni da lavorare
        Task<Integer> cntSpedizioniDaLavorareAsync = shipmentAsyncService.getCountSpedizioniDaLavorare(operatoreFilialeDTO);
        cntSpedizioniDaLavorareAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblSpedizioniDaLavorare.setText(newValue.toString());
        });

        //Setup task per il conteggio degli ordini di packaging conclusi in attesa di trasporto
        Task<Integer> cntOrdiniPackagingDaTrasportareAsync = shipmentAsyncService.getCountOrdiniDiLavoroPackagingConclusiAttesaTrasporto(operatoreFilialeDTO.getFiliale());
        cntOrdiniPackagingDaTrasportareAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblOrdiniPackagingDaTrasportare.setText(newValue.toString());
        });

        //Setup task per il conteggio degli ordini di trasporto non conclusi
        Task<Integer> cntOrdiniTrasportoNonConclusiAsync = shipmentAsyncService.getCountOrdiniDiLavoroSpedizioneAperti(operatoreFilialeDTO.getFiliale());
        cntOrdiniTrasportoNonConclusiAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblCntOrdiniTrasportoNonConclusi.setText(newValue.toString());
        });

        //Eseguo i task
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(cntOrdiniDaLavorareTaskAsync);
        executorService.submit(cntSpedizioniDaLavorareAsync);
        executorService.submit(cntOrdiniTrasportoNonConclusiAsync);
        executorService.submit(cntOrdiniPackagingDaTrasportareAsync);
    }
}
