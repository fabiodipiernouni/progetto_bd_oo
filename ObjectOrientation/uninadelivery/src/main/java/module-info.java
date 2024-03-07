module org.unina.uninadelivery {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires static lombok;
    requires org.yaml.snakeyaml;
    requires java.sql;
    requires mfx.resources;
    requires mfx.core;
    requires mfx.localization;
    requires mfx.effects;
    requires MaterialFX;
    requires VirtualizedFX;

    opens org.unina.uninadelivery.presentation.app to javafx.fxml;
    exports org.unina.uninadelivery.presentation.app;

    opens org.unina.uninadelivery.presentation.controller to javafx.fxml;
    exports org.unina.uninadelivery.presentation.controller;
    exports org.unina.uninadelivery.presentation.controller.appdomain;
    opens org.unina.uninadelivery.presentation.controller.appdomain to javafx.fxml;
    exports org.unina.uninadelivery.presentation.controller.shipmentdomain;
    opens org.unina.uninadelivery.presentation.controller.shipmentdomain to javafx.fxml;
}