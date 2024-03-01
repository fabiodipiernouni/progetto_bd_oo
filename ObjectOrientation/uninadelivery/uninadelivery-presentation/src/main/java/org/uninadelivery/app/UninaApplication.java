package org.uninadelivery.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UninaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var res = UninaApplication.class.getResource("/View/appdomain/login-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(res);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(UninaApplication.class.getResourceAsStream("/icons/delivery-truck_12731488.png"))));
        stage.setTitle("UninaDelivery - login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}