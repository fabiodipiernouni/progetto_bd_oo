package org.unina.uninadelivery.presentation.controller.orgdomain;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.unina.uninadelivery.bll.shipmentdomain.ShipmentService;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.presentation.helper.Session;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeOpCorriereController implements Initializable {
    private final Stage dashboardStage;
    private final OperatoreCorriereDTO operatoreCorriereDTO;
    private final ShipmentService shipmentService;


    @FXML
    protected Label lblCntOrdiniPackagingDaPrendereInCarico;
    @FXML
    protected Label lblOrdiniPackagingDaTerminare;
    @FXML
    protected Label lblOrdiniTrasportoDaPrendereInCarico;
    @FXML
    protected Label lblCntOrdiniTrasportoDaTerminare;

    public HomeOpCorriereController(Stage dashboardStage) {
        this.dashboardStage = dashboardStage;
        Session session = Session.getInstance();
        operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();
        shipmentService = new ShipmentService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Task<Integer> cntPackagingDaPrendereInCaricoTask = shipmentService.getCountOrdiniDiLavoroPackagingDaPrendereInCarico(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
        cntPackagingDaPrendereInCaricoTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblCntOrdiniPackagingDaPrendereInCarico.setText(String.valueOf(newValue));
        });

        Task<Integer> cntPackagingDaTerminareTask = shipmentService.getCountOrdiniDiLavoroPackagingDaPrendereInCarico(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
        cntPackagingDaTerminareTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblOrdiniPackagingDaTerminare.setText(String.valueOf(newValue));
        });

        Task<Integer> cntTrasportoDaPrendereInCaricoTask = shipmentService.getCountOrdiniDiLavoroTrasportoDaPrendereInCarico(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
        cntTrasportoDaPrendereInCaricoTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblOrdiniTrasportoDaPrendereInCarico.setText(String.valueOf(newValue));
        });

        Task<Integer> cntTrasportoDaTerminareTask = shipmentService.getCountOrdiniDiLavoroSpedizioneDaTerminare(operatoreCorriereDTO.getGruppoCorriere().getFiliale());
        cntTrasportoDaTerminareTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            lblCntOrdiniTrasportoDaTerminare.setText(String.valueOf(newValue));
        });

        //Eseguo i task
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(cntPackagingDaPrendereInCaricoTask);
        executorService.submit(cntPackagingDaTerminareTask);
        executorService.submit(cntTrasportoDaPrendereInCaricoTask);
        executorService.submit(cntTrasportoDaTerminareTask);
    }
}
