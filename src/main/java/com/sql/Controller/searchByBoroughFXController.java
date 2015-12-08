package com.sql.Controller;

import com.sql.Model.Address;
import com.sql.Model.MongoDBClient;
import com.sql.Model.Restaurants;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Absalon DEEL on 03/12/2015.
 */
public class searchByBoroughFXController implements Initializable {

    @FXML private TextField restaurantBorough;
    @FXML private GridPane grid;
    @FXML private VBox vBox;
    @FXML private Text response;
    private ComboBox<String> comboBorough;
    private Stage stage = new Stage();
    private TableView<Restaurants> table = new TableView<>();
    private ObservableList<Restaurants> restaurantsList;
    private  MongoDBClient mongoClient = new MongoDBClient();
    private List<String> list2 = mongoClient.allBorough();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBorough = new ComboBox<>();
        grid.add(comboBorough, 1,1);

        for(int j = 1; j < list2.size(); j++)
        {
            comboBorough.getItems().add(list2.get(j));
        }

        comboBorough.getSelectionModel().select(list2.get(2));

    }


    public void handleSearchButtonBoroughAction(ActionEvent actionEvent) {

        String phrase = "Tableau des ";
        phrase += restaurantBorough.getText() + " restaurants aleatoire dans ";
        phrase += comboBorough.getValue();
        stage.setTitle(phrase);

        Scene scene = new Scene(table);

        table.setEditable(true);

        table.getItems().clear();
        table.setId("tableView");

        List<String> stringList = new ArrayList<>();
        stringList.clear();
        restaurantsList = FXCollections.observableArrayList();
        restaurantsList.clear();

        stringList = mongoClient.findByBorough(comboBorough.getValue());

        int rand;
        for(int i = 0; i < Integer.parseInt(restaurantBorough.getText()); i++){
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

        stage.setScene(scene);
        stage.setMinWidth(1102);
        stage.setMinHeight(800);
        stage.show();

    }

    public void handleSearchButtonBoroughAction2(ActionEvent actionEvent) {

        SelectionModel<Restaurants> valeur = table.getSelectionModel();
        Restaurants test = valeur.getSelectedItem();

        Stage primaryStage = new Stage();
        WebView webView = new WebView();
        Scene scene2 = new Scene(webView, 900, 900);

        Double x = test.getCoord().getX();
        Double y = test.getCoord().getY();

        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://www.google.fr/maps/@" + y +","+ x + ",19z?hl=fr");

        primaryStage.setTitle("Geolocalisation de " + test.getName().toString() + " dans " + test.getBorough().toString());
        primaryStage.setScene(scene2);
        primaryStage.show();

    }
}
