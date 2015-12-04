package com.sql.Controller;

import com.sql.Model.MongoDBClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Absalon DEEL on 02/12/2015.
 */
public class searchCuisineFXController implements Initializable{

    @FXML private GridPane grid;
    @FXML private VBox vB;
    private ComboBox<String> comboCuisine;
    private ComboBox<String> comboBorough;
    private Label reponse;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboCuisine = new ComboBox<>();
        comboBorough = new ComboBox<>();
        grid.add(comboBorough, 2,1);
        grid.add(comboCuisine, 2,2);

        MongoDBClient mongoClient = new MongoDBClient();

        List<String> list = mongoClient.allCuisine();
        List<String> list2 = mongoClient.allBorough();

        for(int i = 0; i < list.size(); i++)
        {
            comboCuisine.getItems().add(list.get(i));
        }


        for(int j = 0; j < list2.size(); j++)
        {
            comboBorough.getItems().add(list2.get(j));
        }
    }



    public void handleSearchCButtonAction(ActionEvent actionEvent) {

        MongoDBClient mongoClient = new MongoDBClient();

        reponse = new Label();

        String phrase = "";

        List<String> list = mongoClient.find(comboCuisine.getValue(),comboBorough.getValue());

        for(int i = 0; i < list.size(); i++) {
            phrase += list.get(i);
        }

        reponse.setText(phrase);
        vB.getChildren().addAll(reponse);
//        grid.add(reponse, 0,3);

    }
}
