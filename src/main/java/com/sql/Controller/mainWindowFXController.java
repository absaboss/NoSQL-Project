package com.sql.Controller;

import com.sql.Model.MongoDBClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class mainWindowFXController implements Initializable {

    @FXML private TextField inputVal;

    MongoDBClient mongoClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mongoClient = new MongoDBClient();
    }

    public void handleSubmitButtonAction(ActionEvent actionEvent) {


        String restaurantName = inputVal.getText().toString();

//        mongoClient.affichage(mongoClient.findByName(restaurantName));

        List<String> stringList = mongoClient.findByBorough(restaurantName);

        //On aussi faire un tirage aléatoire pour savoir où on commence
        for(int i = 0; i <5;i++){//On affiche seulement 5
            mongoClient.affichage(stringList.get(i));
        }
    }
}
