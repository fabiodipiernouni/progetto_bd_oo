package org.uninadelivery.app;

import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.uninadelivery.entity.appdomain.UtenteDto;

import java.io.IOException;
import java.util.Objects;

public class UninaApplication extends Application {

    private Property<UtenteDto> utenteDto;

    @Override
    public void init() {
        utenteDto = new SimpleObjectProperty<>();

        utenteDto.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                //System.out.println("Logged in as " + newValue.getUsername());
            }
            else {
                //System.out.println("Logged out");
            }
        });
    }

    @Override
    public void start(Stage stage) throws IOException {
        //var res = UninaApplication.class.getResource("/view/appdomain/login-view.fxml");
        var res = UninaApplication.class.getResource("/view/unina-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(res);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.getIcons().add(new Image(Objects.requireNonNull(UninaApplication.class.getResourceAsStream("/icons/delivery-truck_12731488.png"))));
        stage.setTitle("UninaDelivery - login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}