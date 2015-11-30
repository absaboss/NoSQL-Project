package com.sql.View;/**
 * Created by Absalon DEEL on 30/11/2015.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class mainWindowFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private GridPane mainPane;

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));

        Scene scene = new Scene(mainPane, 600, 550);

        primaryStage.setTitle("Restaurants Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
