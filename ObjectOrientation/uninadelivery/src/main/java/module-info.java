module org.unina.uninadelivery {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    requires java.sql;
    requires org.yaml.snakeyaml;


    opens org.unina.uninadelivery.presentation.app to javafx.fxml;
    exports org.unina.uninadelivery.presentation.app;
    exports org.unina.uninadelivery.presentation.controller;
    opens org.unina.uninadelivery.presentation.controller to javafx.fxml;

}