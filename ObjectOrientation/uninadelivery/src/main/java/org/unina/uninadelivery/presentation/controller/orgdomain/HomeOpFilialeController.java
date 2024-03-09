package org.unina.uninadelivery.presentation.controller.orgdomain;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.customerdomain.CustomerAsyncService;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentAsyncService;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.presentation.helper.Session;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeOpFilialeController implements Initializable {
    final CustomerAsyncService customerAsyncService;
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
        customerAsyncService = new CustomerAsyncService();
        shipmentAsyncService = new ShipmentAsyncService();

        Session session = Session.getInstance();
        operatoreFilialeDTO = (OperatoreFilialeDTO) session.getUserDto().getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Task<Integer> cntOrdiniDaLavorareTaskAsync = customerAsyncService.getCountOrdiniDaLavorareTask(operatoreFilialeDTO.getFiliale());
        cntOrdiniDaLavorareTaskAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblCntOrdiniClienteFiliale.setText(newValue.toString());
        });

        Task<Integer> cntSpedioniDaLavorareAsync = shipmentAsyncService.getCountSpedioniDaLavorare(operatoreFilialeDTO);
        cntSpedioniDaLavorareAsync.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblSpedizioniDaLavorare.setText(newValue.toString());
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(cntOrdiniDaLavorareTaskAsync);
        executorService.submit(cntSpedioniDaLavorareAsync);
    }
}
