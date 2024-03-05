module org.unina.uninadelivery33 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires static lombok;

    requires fr.brouillard.oss.cssfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires materialfx.all;

    requires java.sql;
    requires org.yaml.snakeyaml;

    opens org.unina.uninadelivery33.presentation.app to javafx.fxml;
    exports org.unina.uninadelivery33.presentation.app;
    exports org.unina.uninadelivery33.presentation.controller;
    opens org.unina.uninadelivery33.presentation.controller to javafx.fxml;

}