package com.example.groceryapp.ui.recipes;

import java.io.Serializable;

public class Ingredient implements Serializable {
    public String name;
    public boolean isChecked;
    public double price;

    public Ingredient(String name, double price) {
        this.name = name;
        this.isChecked = false;
        this.price = price;
    }
}