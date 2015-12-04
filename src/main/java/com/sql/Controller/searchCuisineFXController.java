package com.sql.Controller;

import com.sql.Model.MongoDBClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Absalon DEEL on 02/12/2015.
 */
public class searchCuisineFXController implements Initializable{

    @FXML private GridPane grid;
    private ComboBox<String> comboCuisine;
    private ComboBox<String> comboBorough;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboCuisine = new ComboBox<>();
        comboBorough = new ComboBox<>();
        grid.add(comboBorough, 0,1);
        grid.add(comboCuisine, 0,2);

        comboBorough.setMaxWidth(150);
        comboCuisine.setMaxWidth(350);

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

    }
}
