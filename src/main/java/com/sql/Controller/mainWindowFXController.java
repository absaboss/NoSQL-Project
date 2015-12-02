package com.sql.Controller;

import com.sql.Model.MongoDBClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainWindowFXController implements Initializable {

    @FXML private TextField inputVal;
    MongoDBClient mongoClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mongoClient = new MongoDBClient();
    }

    public void handleSubmitButtonAction(ActionEvent actionEvent) throws IOException {


//        GridPane mainPane2 = FXMLLoader.load(getClass().getResource("countRestaurant.fxml"));
//
//        Scene scene2 = new Scene(mainPane2, 600, 550);
//        primaryStage.setScene(scene2);
//        primaryStage.show();

        //String restaurantName = inputVal.getText().toString();

//        mongoClient.affichage(mongoClient.findByName(restaurantName));

//        List<String> stringList = mongoClient.findByBorough(restaurantName);

        //mongoClient.find();
        //On aussi faire un tirage aléatoire pour savoir où on commence
//        for(int i = 0; i <5;i++){//On affiche seulement 5
//            mongoClient.affichage(stringList.get(i));
//        }
    }
}
