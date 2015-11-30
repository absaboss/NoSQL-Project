package com.sql.Controller;

import com.sql.Model.MongoDBClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class mainWindowFXController {

    @FXML private TextField inputVal;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        MongoDBClient mongoClient = new MongoDBClient();

        String restaurantName = inputVal.getText().toString();

        mongoClient.findByName(restaurantName);
    }
}
