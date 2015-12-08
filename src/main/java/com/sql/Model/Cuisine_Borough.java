package com.sql.Model;

/**
 * Created by Absalon DEEL on 04/12/2015.
 */
public class Cuisine_Borough {
    String cuisine;
    int nb;

    public Cuisine_Borough(String cuisine, int nb) {
        this.cuisine = cuisine;
        this.nb = nb;
    }

    @Override
    public String toString() {
        return "Cuisine_Borough{" +
                "cuisine='" + cuisine + '\'' +
                ", nb=" + nb +
                '}';
    }

    public String getCuisine() {
        return cuisine;
    }

    public int getNb() {
        return nb;
    }
}
