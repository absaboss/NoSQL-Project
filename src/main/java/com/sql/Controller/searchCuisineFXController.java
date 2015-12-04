package com.sql.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Absalon DEEL on 02/12/2015.
 */
public class searchCuisineFXController extends mainWindowFXController{


    public void handleSearchCButtonAction(ActionEvent actionEvent) {
        mongoClient.allCuisine();
    }
}
