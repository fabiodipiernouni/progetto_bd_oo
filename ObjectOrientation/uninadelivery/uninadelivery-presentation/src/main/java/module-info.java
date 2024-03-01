module org.uninadelivery.uninadeliverypresentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens view to javafx.fxml;
    opens org.uninadelivery.controller to javafx.fxml;
    exports org.uninadelivery.controller;
    opens org.uninadelivery.controller.appdomain to javafx.fxml;
    exports org.uninadelivery.controller.appdomain;
    exports org.uninadelivery.app;
    opens org.uninadelivery.app to javafx.fxml;
}