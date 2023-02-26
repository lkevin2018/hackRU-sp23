package com.example.zonecalculatorhackru;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FareCalculatorApp extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/zonecalculatorhackru/view.fxml"));
        AnchorPane root = (AnchorPane)loader.load();
        Controller controller = loader.getController();
        controller.start(stage);

        Scene scene = new Scene(root);
        stage.setTitle("Zone Calculator");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}