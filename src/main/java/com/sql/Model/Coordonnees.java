package com.sql.Model;

/**
 * Created by Absalon DEEL on 30/11/2015.
 */
public class Coordonnees {
    private Double x;
    private Double y;

    public Coordonnees(Double x, Double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordonnees{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
