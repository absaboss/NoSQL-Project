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
    public MongoDBClient(){
        mongoClient = new MongoClient();

        MongoDatabase db = mongoClient.getDatabase("DBLP");


        MongoCollection coll = db.getCollection("restaurants");

        FindIterable<Document> iterable = coll.find(new Document("borough", "Manhattan"));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });

    }
}
