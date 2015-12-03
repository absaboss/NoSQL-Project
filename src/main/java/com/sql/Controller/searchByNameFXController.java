package com.sql.Controller;

import com.sql.Model.Restaurants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Created by Absalon DEEL on 03/12/2015.
 */
public class searchByNameFXController extends mainWindowFXController {

    @FXML private TextField restaurantName;
    @FXML private GridPane grid;
    @FXML private Text response;

    public void handleSearchButtonAction(ActionEvent actionEvent) {
        Restaurants res = mongoClient.affichage(mongoClient.findByName(restaurantName.getText().toString()));

       response.setText("Reponse : ");

        Label name = new Label("Nom : ");
        grid.add(name, 0, 7);

        Label restName = new Label(res.getName());
        grid.add(restName, 1, 7);

        Label address = new Label("Adresse : ");
        grid.add(address, 0,8);

        Label restAddress = new Label(res.getAddress().toString());
        grid.add(restAddress, 1, 8);

        Label cuisine = new Label("Cuisine : ");
        grid.add(cuisine, 0, 9);

        Label restCuisine = new Label(res.getCuisine());
        grid.add(restCuisine, 1, 9);

        Label borough = new Label("Borough : ");
        grid.add(borough, 0, 10);

        Label restBorough = new Label(res.getBorough());
        grid.add(restBorough, 1, 10);
    }
}
