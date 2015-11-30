package com.sql.Model;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.json.JSONObject;

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

    public void findByName(String restaurantName){

        FindIterable<Document> iterable = collection.find(new Document("name", restaurantName));

        if(iterable != null){
            iterable.forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {

                    System.out.println(document);
                    System.out.println();
                    System.out.println();


                    System.out.println(document.get("address"));

                // Test de push Adrien O.O


                }
            });
        }

        else{
            System.out.println("Le restaurant n'a pas été trouvé");
        }


    }
}
