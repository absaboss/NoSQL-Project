package com.sql.Model;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.BSON;
import org.bson.BsonDocument;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Absalon DEEL on 30/11/2015.
 */
public class MongoDBClient {

    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection collection;

    public MongoDBClient(){
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase("DBLP");
        collection = db.getCollection("restaurants");
    }

    String textDocumentFormat = "";
    public String findByName(String restaurantName){

        FindIterable<Document> iterable = collection.find(new Document("name", restaurantName));
        iterable.forEach(new Block<Document>() {//On parcours 1 par 1 les réponses obtenues
            @Override
            public void apply(final Document document) {
                textDocumentFormat = document.toJson();//On fait une conversion en JSON pour faciliter la manipulation
            }
        });

        return textDocumentFormat;
    }

    List<String> stringList;
    public List<String> findByBorough(String restaurantBorough){
        stringList = new ArrayList<>();

        FindIterable<Document> iterable = collection.find(eq("borough", restaurantBorough));
        iterable.forEach(new Block<Document>() {//On parcours 1 par 1 les réponses obtenues
            @Override
            public void apply(final Document document) {
                stringList.add(document.toJson());//On ajoute nos fichier string a notre list
            }
        });

        return stringList;
    }

    public void affichage(String json){
        //Variable pour Restaurant
        String name;
        String borough;
        String cuisine;

        //Variable pour address
        String building;
        Coordonnees coord;
        String street;
        int zipCode;

        JSONObject obj = new JSONObject(json);//On crée notre nouvelle objet Json
        name = obj.getString("name");
        borough = obj.getString("borough");
        cuisine = obj.getString("cuisine");

        JSONObject address = obj.getJSONObject("address");//On récupère l'objet Address

        building = address.getString("building");
        zipCode = address.getInt("zipcode");
        street = address.getString("street");

        JSONObject coordinates = address.getJSONObject("coord");//On récupère l'objet Address
        JSONArray coordinate = coordinates.getJSONArray("coordinates");//On récupère le tableau avec les 2 coordonnées
        coord = new Coordonnees(coordinate.getDouble(0), coordinate.getDouble(1));//On instancie la nouvelle coordonnées

        Address add = new Address(building, street, coord, zipCode);//On instancie l'address

        Restaurants res = new Restaurants(name, add, borough, cuisine);//Enfin on instancie un nouveau restaurant

        System.out.println(res);
    }

    public List<String> getStringList() {
        return stringList;
    }
}
