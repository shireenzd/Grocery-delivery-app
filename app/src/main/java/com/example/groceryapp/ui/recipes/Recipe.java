package com.example.groceryapp.ui.recipes;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    public String videoId;
    public String title;
    public String description;
    public String minutes;
    public String level;
    public String author;
    public List<Ingredient> ingredients;
    public String recipeSteps;

    public Recipe(String videoId, String title, String description, String minutes, String level, String author, List<Ingredient> ingredients, String recipeSteps) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.minutes = minutes;
        this.level = level;
        this.author = author;
        this.ingredients = ingredients;
        this.recipeSteps = recipeSteps;
    }
}