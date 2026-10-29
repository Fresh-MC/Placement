package com.rest.Models;

public class menu {
    
    public enum Type {
        FOOD, DRINK, DESSERT, STARTERS
    }

    private int id;
    private String custName;
    private double price;
    private Type type;

    public menu(int id, String custName, double price, Type type) {
        this.id = id;
        this.custName = custName;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return custName; 
    }

    public void setName(String custName) {
        this.custName = custName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}