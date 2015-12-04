package com.sql.Controller;

import com.sql.Model.Cuisine_Borough;
import com.sql.Model.MongoDBClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
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
    private int compteur = 0;

    private MongoDBClient mongoClient = new MongoDBClient();
    private List<String> list = mongoClient.allCuisine();
    private List<String> list2 = mongoClient.allBorough();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboCuisine = new ComboBox<>();
        comboBorough = new ComboBox<>();
        grid.add(comboBorough, 2,1);
        grid.add(comboCuisine, 2,2);


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

        List<String> list = mongoClient.find(comboBorough.getValue());
        List<Cuisine_Borough> listCB = new ArrayList<>();

        for(int i = 0; i<list.size(); i++){
            JSONObject obj = new JSONObject(list.get(i));

            Cuisine_Borough cb = new Cuisine_Borough(obj.getString("_id"), obj.getInt("count"));
            listCB.add(cb);
        }


        final PieChart chart = new PieChart();
        String phrase = "Type de cuisine dans ";
        phrase += comboBorough.getValue();
        chart.setTitle(phrase);

        List<PieChart.Data> l = new ArrayList<>();

        for(int i = 0; i<listCB.size(); i++){
            Cuisine_Borough c = listCB.get(i);
            if(c.getNb()>100){
                l.add(new PieChart.Data(c.getCuisine(), c.getNb()));
            }
        }

        chart.getData().setAll(l);
        vB.getChildren().addAll(chart);

    }

    public void handleSearchCButtonAction1(ActionEvent actionEvent) {

        final List<BarChart.Series> seriesList = new LinkedList<>();

        final String[] categoriesNames = new String[list.size()];

        for(int i = 0; i < list.size(); i++)
            categoriesNames[i] = list.get(i);

        final String[] seriesNames = new String[0];

        seriesNames[0] = comboCuisine.getValue();

        final int[] values = {}; // tableau d'entier de la répartition des restau pour chaque borough
        final double minY = 0;
        double maxY = -Double.MAX_VALUE;
        for (int seriesIndex = 0; seriesIndex < seriesNames.length; seriesIndex++) {
            final BarChart.Series series = new BarChart.Series<>();
            series.setName(seriesNames[seriesIndex]);

            for (int categoryIndex = 0; categoryIndex < categoriesNames.length; categoryIndex++) {
                final int value = values[categoryIndex];
                final String category = categoriesNames[categoryIndex];
                maxY = Math.max(maxY, value);
                final BarChart.Data data = new BarChart.Data(category, value);
                series.getData().add(data);
            }
            seriesList.add(series);
        }
        // Création du graphique.
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.getCategories().setAll(categoriesNames);
        xAxis.setLabel("Type de cuisine");
        final NumberAxis yAxis = new NumberAxis(minY, maxY, 50);
        yAxis.setLabel("Quantité de restaurant");
        final BarChart chart = new BarChart(xAxis, yAxis);
        chart.setTitle("New York");
        chart.getData().setAll(seriesList);
        // Montage de l'IU.
        vB.getChildren().add(chart);
        chart.setTitle("Repartition des restaurants dans New York");

    }
}
