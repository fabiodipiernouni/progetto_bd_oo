package org.unina.uninadelivery.presentation.controller.orgdomain;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeOpFilialeController {
    @FXML
    public AnchorPane mainAnchorPane;

    public HomeOpFilialeController(Stage dashboardStage) {
        /*StackPane stackPane = (StackPane) dashboardStage.getScene().getRoot().lookup("#contentPane");
        double anchorPaneWidth = stackPane.getWidth();
        double anchorPaneHeight = stackPane.getHeight();

        double controlWidth = mainAnchorPane.getWidth();
        double controlHeight = mainAnchorPane.getHeight();

        AnchorPane.setTopAnchor(mainAnchorPane, (anchorPaneHeight - controlHeight) / 2);
        AnchorPane.setBottomAnchor(mainAnchorPane, (anchorPaneHeight - controlHeight) / 2);
        AnchorPane.setLeftAnchor(mainAnchorPane, (anchorPaneWidth - controlWidth) / 2);
        AnchorPane.setRightAnchor(mainAnchorPane, (anchorPaneWidth - controlWidth) / 2);*/
    }
}
