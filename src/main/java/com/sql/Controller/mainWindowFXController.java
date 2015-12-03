package com.sql.Controller;

import com.sql.Model.MongoDBClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainWindowFXController implements Initializable {

    @FXML private TextField inputVal;
    public MongoDBClient mongoClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mongoClient = new MongoDBClient();
    }

    public void handleSubmitButtonAction(ActionEvent actionEvent) throws IOException {

    }
}
