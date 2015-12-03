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

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getBuilding(){
        return address.getBuilding();
    }

    public String getStreet(){
        return address.getStreet();
    }

    public int getZipCode(){
        return address.getZipCode();
    }

    public Coordonnees getCoord(){
        return address.getCoord();
    }

    public String getBorough() {
        return borough;
    }

    public char getGrade() {
        return grade;
    }

    public String getCuisine() {
        return cuisine;
    }
}
