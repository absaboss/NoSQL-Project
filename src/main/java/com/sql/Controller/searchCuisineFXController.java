package com.sql.Controller;

import com.sql.Model.Cuisine_Borough;
import com.sql.Model.MongoDBClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
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

public class searchCuisineFXController implements Initializable{

    @FXML private GridPane grid;
    @FXML private VBox vB;
    private ComboBox<String> comboCuisine;
    private ComboBox<String> comboBorough3;

    private ComboBox<String> comboBorough;
    private ComboBox<String> comboBorough2;

    private Label reponse;
    //private int compteur = 0;
    private Stage stage = new Stage();

    private MongoDBClient mongoClient = new MongoDBClient();
    private List<String> list = mongoClient.allCuisine();
    private List<String> list2 = mongoClient.allBorough();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboCuisine = new ComboBox<>();
        comboBorough = new ComboBox<>();
        comboBorough2 = new ComboBox<>();
        comboBorough3 = new ComboBox<>();

        grid.add(comboBorough, 1,1);
        grid.add(comboBorough2, 1,2);
        grid.add(comboBorough3, 3,2);



        for(int i = 0; i < list.size(); i++)
        {
            comboCuisine.getItems().add(list.get(i));
        }


        for(int j = 0; j < list2.size(); j++)
        {
            comboBorough.getItems().add(list2.get(j));
            comboBorough2.getItems().add(list2.get(j));
            comboBorough3.getItems().add(list2.get(j));
        }

    }



    public void handleSearchCButtonAction(ActionEvent actionEvent) {

        List<String> list5 = mongoClient.find(comboBorough3.getValue());
        List<Cuisine_Borough> listCB = new ArrayList<>();

        for(int i = 0; i<list5.size(); i++){
            JSONObject obj = new JSONObject(list5.get(i));

            Cuisine_Borough cb = new Cuisine_Borough(obj.getString("_id"), obj.getInt("count"));
            listCB.add(cb);
        }


        final PieChart chart = new PieChart();
        String phrase = "Type de cuisine dans ";
        phrase += comboBorough3.getValue();
        chart.setTitle(phrase);

        List<PieChart.Data> l = new ArrayList<>();

        for(int i = 0; i<listCB.size(); i++){
            Cuisine_Borough c = listCB.get(i);
            l.add(new PieChart.Data(c.getCuisine(), c.getNb()));
        }

        chart.getData().setAll(l);
        vB.getChildren().addAll(chart);

    }

    public void handleSearchCButtonAction1(ActionEvent actionEvent) {

        String phrase = "Type de cuisine dans ";
        phrase += comboBorough.getValue() + " ";
        stage.setTitle(phrase);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle(comboBorough.getValue());
        xAxis.setLabel("Cuisines");
        yAxis.setLabel("Number of Restaurants");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(comboBorough.getValue());

        XYChart.Series series2 = new XYChart.Series();
        series2.setName(comboBorough2.getValue());

        ////////////////////////////////////////////////////////////////////////////////////

        List<String> list5 = mongoClient.find(comboBorough.getValue());
        List<Cuisine_Borough> listCB = new ArrayList<>();
        List<Cuisine_Borough> listCBsorted = new ArrayList<>();

        List<String> list52 = mongoClient.find(comboBorough2.getValue());
        List<Cuisine_Borough> listCB2 = new ArrayList<>();
        List<Cuisine_Borough> listCBsorted2 = new ArrayList<>();

        for(int i = 0; i<list5.size(); i++){
            JSONObject obj = new JSONObject(list5.get(i));

            Cuisine_Borough cb = new Cuisine_Borough(obj.getString("_id"), obj.getInt("count"));
            listCB.add(cb);

            JSONObject obj2 = new JSONObject(list52.get(i));

            Cuisine_Borough cb2 = new Cuisine_Borough(obj2.getString("_id"), obj2.getInt("count"));
            listCB2.add(cb2);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        int total = 0;
        for(int i = 0; i < listCB.size(); i++)
        {
            Cuisine_Borough c = listCB.get(i);
            total += c.getNb();
        }




        for(int j = 0; j < 30 ; j++)
        {
            int compteur = 0;
            int valeur = listCB.get(0).getNb();

            int compteur2 = 0;
            int valeur2 = listCB2.get(0).getNb();

            for(int i = 1; i < listCB.size(); i++)
            {
                Cuisine_Borough c = listCB.get(i);
                if (c.getNb() > valeur )
                {
                    valeur = c.getNb();
                    compteur = i;
                }

            }

            for(int i = 1; i < listCB2.size(); i++)
            {
                Cuisine_Borough c = listCB2.get(i);
                if (c.getNb() > valeur )
                {
                    valeur2 = c.getNb();
                    compteur2 = i;
                }

            }

            listCBsorted.add(listCB.get(compteur));
            listCB.remove(compteur);

            listCBsorted2.add(listCB2.get(compteur2));
            listCB2.remove(compteur2);
        }


        for(int i = 0; i < listCBsorted.size(); i++)
            series1.getData().add(new XYChart.Data(listCBsorted.get(i).getCuisine(), listCBsorted.get(i).getNb()));

        for(int i = 0; i < listCBsorted2.size(); i++)
            series2.getData().add(new XYChart.Data(listCBsorted2.get(i).getCuisine(), listCBsorted2.get(i).getNb()));



        Scene scene  = new Scene(bc, 1000, 800);
        bc.getData().addAll(series1, series2);
        stage.setScene(scene);
        stage.show();
    }
}
