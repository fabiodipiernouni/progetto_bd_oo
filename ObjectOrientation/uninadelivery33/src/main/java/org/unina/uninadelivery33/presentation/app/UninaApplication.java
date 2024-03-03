package org.unina.uninadelivery33.presentation.app;

import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.unina.uninadelivery33.entity.appdomain.UtenteDto;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class UninaApplication extends Application {

    private Property<UtenteDto> utenteDto;

    public UtenteDto getUtenteDto() {
        if(utenteDto.getValue() != null) return utenteDto.getValue();

        return null;
    }

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
        //var res = UninaApplication.class.getResource("/views/appdomain/login-view.fxml");
        URL res = UninaApplication.class.getResource("/presentation/views/unina-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(res);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.getIcons().add(new Image(Objects.requireNonNull(UninaApplication.class.getResourceAsStream("/presentation/icons/delivery-truck_12731488.png"))));
        stage.setTitle("UninaDelivery - login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}