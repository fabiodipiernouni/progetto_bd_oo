package org.unina.uninadelivery.presentation.controller.shipmentdomain;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.unina.uninadelivery.entity.shipmentdomain.PaccoDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PacchiController implements Initializable {
    private final Stage dashboardStage;
    private final List<PaccoDTO> pacchi;

    public PacchiController(Stage dashboardStage, List<PaccoDTO> pacchi) {
        this.dashboardStage = dashboardStage;
        this.pacchi = pacchi;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // todo
    }
}
