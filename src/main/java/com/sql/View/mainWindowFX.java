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
    private Stage primaryStage;
    private Stage stageSearchByName;
    private Stage stageSearchCuisine;
    private Stage stageSearchBorough;

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
        Button buttonSearchBorough = (Button) mainPane.getChildren().get(2);
        Button buttonSearchCusine = (Button) mainPane.getChildren().get(3);

        stageSearchByName = new Stage();
        stageSearchByName.setMinHeight(600);
        stageSearchByName.setMinWidth(600);
        buttonSearch.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("searchByName.fxml"));

                    stageSearchByName.setTitle("Search restaurant by name");
                    stageSearchByName.setScene(new Scene(root, 600, 600));
                    stageSearchByName.show();

                    //((Node)(event.getSource())).getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        stageSearchCuisine = new Stage();
        stageSearchCuisine.setMinHeight(600);
        stageSearchCuisine.setMinWidth(600);
        buttonSearchCusine.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("searchCuisine.fxml"));

                    stageSearchCuisine.setTitle("Search cuisine by borough");
                    stageSearchCuisine.setScene(new Scene(root, 450, 450));
                    stageSearchCuisine.show();

                    //((Node)(event.getSource())).getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        stageSearchBorough = new Stage();
        stageSearchBorough.setMinHeight(700);
        stageSearchBorough.setMinWidth(1000);
        buttonSearchBorough.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("searchByBorough.fxml"));

                    stageSearchBorough.setTitle("Search restaurants by borough");
                    stageSearchBorough.setScene(new Scene(root, 1000, 700));
                    stageSearchBorough.show();

                    //((Node)(event.getSource())).getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        primaryStage.show();
    }
}
