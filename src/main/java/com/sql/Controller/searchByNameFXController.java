package com.sql.Controller;

import com.sql.Model.Address;
import com.sql.Model.MongoDBClient;
import com.sql.Model.Restaurants;
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
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Absalon DEEL on 03/12/2015.
 */
public class searchByNameFXController implements Initializable {

    public Button searchButton;
    public Button searchButton2;
    @FXML private TextField restaurantName;
    @FXML private GridPane grid;
    @FXML private Text response;
    @FXML private VBox vBox;

    private MongoDBClient mongoClient;
    private ComboBox<String> comboBorough;
    private ComboBox<String> comboGrade;

    private TableView<Restaurants> table;
    private ObservableList<Restaurants> restaurantsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mongoClient = new MongoDBClient();

        restaurantsList = FXCollections.observableArrayList();

        List<String> listBorough = mongoClient.allBorough();
        listBorough.sort(Comparator.<String>naturalOrder());
        comboBorough = new ComboBox<>();
        grid.add(comboBorough, 1, 3);
        comboBorough.setValue("Manhattan");


        for(int i = 0; i < listBorough.size(); i++)
            if(!listBorough.get(i).equals("Missing")) comboBorough.getItems().add(listBorough.get(i));


        comboGrade = new ComboBox<>();
        grid.add(comboGrade, 1, 4);
        comboGrade.getItems().addAll("A", "B", "C");
        comboGrade.setValue("A");

        searchButton2.setVisible(false);
    }

    public void handleSearchButtonAction(ActionEvent actionEvent) {

        List<String> stringList = mongoClient.findByName(restaurantName.getText().toString(), comboBorough.getValue().toString(), comboGrade.getValue().toString());

        restaurantsList = FXCollections.observableArrayList();

        for(int i = 0; i < stringList.size(); i++){
            Restaurants res = mongoClient.affichage(stringList.get(i));
            restaurantsList.add(res);
        }

        table = new TableView<>();
        vBox.getChildren().add(table);
        table.setEditable(true);

        table.setId("tableView");

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

        TableColumn grade = new TableColumn("Grade");
        grade.setCellValueFactory(new PropertyValueFactory<Restaurants,String>("grade"));

        table.setItems(restaurantsList);
        table.getColumns().addAll(nameCol, adresse, cuisine, grade);

        searchButton2.setVisible(true);
    }

    public void handleSearchButtonAction2(ActionEvent actionEvent) {

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
