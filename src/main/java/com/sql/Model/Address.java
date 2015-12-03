package com.sql.Model;

/**
 * Created by Absalon DEEL on 30/11/2015.
 */
public class Address {
    private String building;
    private Coordonnees coord;
    private String street;
    private int zipCode;

    public Address(String building, String street, Coordonnees coord, int zipCode) {
        this.building = building;
        this.street = street;
        this.coord = coord;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Building = " + building + "\nStreet = " + street + "\nZip Code = " + zipCode + "\nCoordonnees = " + coord;
    }

    public String getBuilding() {
        return building;
    }

    public Coordonnees getCoord() {
        return coord;
    }

    public String getStreet() {
        return street;
    }

    public int getZipCode() {
        return zipCode;
    }
}
