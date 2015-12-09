package com.sql.Model;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.util.JSON;
import org.bson.BSON;
import org.bson.BsonDocument;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;

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

    public List<String> findByName(String restaurantName, String borough, String grade){

        List<String> stringList = new ArrayList<>();
        FindIterable<Document> iterable = collection.find(new Document("$and",
                asList(new Document("name", java.util.regex.Pattern.compile(restaurantName)),
                new Document("borough", borough))).append("grades.grade", grade));
        iterable.forEach(new Block<Document>() {//On parcours 1 par 1 les réponses obtenues
            @Override
            public void apply(final Document document) {
                stringList.add(document.toJson());//On fait une conversion en JSON pour faciliter la manipulation
            }
        });

        return stringList;
    }

    public List<String> findByBorough(String restaurantBorough){
        List<String> stringList = new ArrayList<>();
        FindIterable<Document> iterable = collection.find(eq("borough", restaurantBorough));

        iterable.forEach(new Block<Document>() {//On parcours 1 par 1 les réponses obtenues
            @Override
            public void apply(final Document document) {
                stringList.add(document.toJson());//On ajoute nos fichier string a notre list
            }
        });

        return stringList;
    }

    public List<String> allCuisine(){
        List<String> stringList = new ArrayList<>();

        AggregateIterable<Document> iterable = collection.aggregate(asList(
                new Document("$group", new Document("_id", "$cuisine"))));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                JSONObject obj = new JSONObject(document.toJson());
                String diffCuisine = obj.getString("_id");

                stringList.add(diffCuisine);
            }
        });

        return stringList;
    }

    public List<String> allBorough(){
        List<String> stringList = new ArrayList<>();

        AggregateIterable<Document> iterable = collection.aggregate(asList(
                new Document("$group", new Document("_id", "$borough"))));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                JSONObject obj = new JSONObject(document.toJson());
                String diffBorough = obj.getString("_id");

                stringList.add(diffBorough);
            }
        });

        return stringList;
    }

    public List<String> find(String borough){
        List<String> stringList = new ArrayList<>();
        AggregateIterable<Document> iterable = collection.aggregate(asList(
                new Document("$match", new Document("borough", borough)),
                new Document("$group", new Document("_id", "$cuisine").append("count", new Document("$sum", 1)))));

        iterable.forEach(new Block<Document>() {//On parcours 1 par 1 les réponses obtenues
            @Override
            public void apply(final Document document) {
//                System.out.println(document.toJson());//On ajoute nos fichier string a notre list
                stringList.add(document.toJson());
            }
        });

        return stringList;
    }

    public Restaurants affichage(String json){
        //Variable pour Restaurant
        String name;
        String borough;
        String cuisine;

        //Variable pour address
        String building;
        Coordonnees coord;
        String street;
        int zipCode;
        String restaurant_grade = "";
        JSONObject obj = new JSONObject(json);//On crée notre nouvelle objet Json
        name = obj.getString("name");
        borough = obj.getString("borough");
        cuisine = obj.getString("cuisine");
        Date date;

        JSONObject address = obj.getJSONObject("address");//On récupère l'objet Address

        building = address.getString("building");
        zipCode = address.getInt("zipcode");
        street = address.getString("street");

        JSONObject coordinates = address.getJSONObject("coord");//On récupère l'objet Address
        JSONArray coordinate = coordinates.getJSONArray("coordinates");//On récupère le tableau avec les 2 coordonnées
        coord = new Coordonnees(coordinate.getDouble(0), coordinate.getDouble(1));//On instancie la nouvelle coordonnées

        JSONArray grades = obj.getJSONArray("grades");
        for(int i = 0; i < grades.length(); i++){
            JSONObject grade = grades.getJSONObject(i);
            restaurant_grade = grade.getString("grade");
        }


        Address add = new Address(building, street, coord, zipCode);//On instancie l'address

        Restaurants res = new Restaurants(name, add, borough, restaurant_grade, cuisine);//Enfin on instancie un nouveau restaurant

        return res;
    }
}
