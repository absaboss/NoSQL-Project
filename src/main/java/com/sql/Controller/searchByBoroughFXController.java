package com.sql.Controller;

import com.sql.Model.Address;
import com.sql.Model.Restaurants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Absalon DEEL on 03/12/2015.
 */
public class searchByBoroughFXController extends mainWindowFXController {

    @FXML private TextField restaurantBorough;
    @FXML private GridPane grid;
    @FXML private VBox vBox;
    @FXML private Text response;

    private TableView<Restaurants> table;
    private ObservableList<Restaurants> restaurantsList;


    public void handleSearchButtonBoroughAction(ActionEvent actionEvent) {

        table = new TableView<>();
        vBox.getChildren().add(table);
        table.setEditable(true);

        table.getItems().clear();
        table.setId("tableView");

        List<String> stringList = new ArrayList<>();
        stringList.clear();
        restaurantsList = FXCollections.observableArrayList();
        restaurantsList.clear();

       stringList = mongoClient.findByBorough(restaurantBorough.getText().toString());

        int rand;
        for(int i = 0; i < 20; i++){
            rand = (int)(Math.random() * (stringList.size()));

            Restaurants res = mongoClient.affichage(stringList.get(rand));
            restaurantsList.add(res);
        }

        TableColumn nameCol = new TableColumn("Nom");
        nameCol.setMinWidth(300);
        nameCol.setCellValueFactory(new PropertyValueFactory<Restaurants, String>("name"));

        TableColumn adresse = new TableColumn("Adresse");
        adresse.setMinWidth(500);

        TableColumn building = new TableColumn("Building");
        building.setCellValueFactory(new PropertyValueFactory<Restaurants, String>("building"));

        TableColumn street = new TableColumn("Street");
        street.setCellValueFactory(new PropertyValueFactory<Restaurants, Address>("street"));

        TableColumn zipCode = new TableColumn("Zip Code");
        zipCode.setCellValueFactory(new PropertyValueFactory<Restaurants, Address>("zipCode"));

        TableColumn coordinates = new TableColumn("Coordinates");
        coordinates.setCellValueFactory(new PropertyValueFactory<Restaurants, Address>("coord"));

        adresse.getColumns().addAll(building, street, zipCode, coordinates);

        TableColumn cuisine = new TableColumn("Cuisine");
        cuisine.setMinWidth(200);
        cuisine.setCellValueFactory(new PropertyValueFactory<Restaurants, String>("cuisine"));

        table.setItems(restaurantsList);
        table.getColumns().addAll(nameCol, adresse, cuisine);
    }
}
