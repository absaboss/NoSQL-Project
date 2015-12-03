package com.sql.View;

import com.sql.Controller.mainWindowFXController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class mainWindowFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private GridPane mainPane;
    //private GridPane mainPane2;
    private Stage primaryStage;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        mainPane = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));

        Scene scene = new Scene(mainPane, 600, 550);

        primaryStage.setTitle("Restaurants Manager");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);

        Button buttonSearch =(Button) mainPane.getChildren().get(1);

        stage = new Stage();
        stage.setMinHeight(600);
        stage.setMinWidth(600);
        buttonSearch.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("searchByName.fxml"));

                    stage.setTitle("Search restaurant by name");
                    stage.setScene(new Scene(root, 450, 450));
                    stage.show();

                    //((Node)(event.getSource())).getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        primaryStage.show();
    }
}
