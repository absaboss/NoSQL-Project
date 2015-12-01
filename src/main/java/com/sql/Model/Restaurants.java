package com.sql.Model;

/**
 * Created by Absalon DEEL on 30/11/2015.
 */
public class Restaurants {
    private String name;
    private Address address;
    private String borough;
    private char grade;
    private String cuisine;

    public Restaurants(String name, Address address, String borough, char grade, String cuisine) {
        this.name = name;
        this.address = address;
        this.borough = borough;
        this.grade = grade;
        this.cuisine = cuisine;
    }

    public Restaurants(String name, Address address, String borough, String cuisine) {
        this.name = name;
        this.address = address;
        this.borough = borough;
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return "Restaurants { " +
                "name='" + name + '\'' +
                ", address=" + address +
                ", borough='" + borough + '\'' +
                ", grade=" + grade +
                ", cuisine='" + cuisine + '\'' +
                '}';
    }
}
