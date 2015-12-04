package com.sql.Controller;

import com.sql.Model.Cuisine_Borough;
import com.sql.Model.MongoDBClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
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

//        reponse = new Label();

//        String phrase = "";

        List<String> list = mongoClient.find(comboBorough.getValue());
        List<Cuisine_Borough> listCB = new ArrayList<>();

        for(int i = 0; i<list.size(); i++){
            JSONObject obj = new JSONObject(list.get(i));

            Cuisine_Borough cb = new Cuisine_Borough(obj.getString("_id"), obj.getInt("count"));
            listCB.add(cb);
        }


        final PieChart chart = new PieChart();
        chart.setTitle("Cuisine");

        List<PieChart.Data> l = new ArrayList<>();

        for(int i = 0; i<listCB.size(); i++){
            Cuisine_Borough c = listCB.get(i);
            l.add(new PieChart.Data(c.getCuisine(), c.getNb()));
        }

        chart.getData().setAll(l);

        vB.getChildren().addAll(chart);

    }
}
