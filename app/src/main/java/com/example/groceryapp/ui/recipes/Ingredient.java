package com.example.groceryapp.ui.recipes;

import java.io.Serializable;

public class Ingredient implements Serializable {
    public String name;
    public boolean isChecked;

    public Ingredient(String name) {
        this.name = name;
        this.isChecked = false;
    }
}