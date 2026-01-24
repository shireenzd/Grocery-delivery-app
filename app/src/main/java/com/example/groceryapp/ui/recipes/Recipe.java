package com.example.groceryapp.ui.recipes;

public class Recipe {
    public String videoId;
    public String title;
    public String description;
    public String minutes;
    public String level;
    public String webUrl;

    public Recipe(String videoId, String title, String description, String minutes, String level, String webUrl) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.minutes = minutes;
        this.level = level;
        this.webUrl = webUrl;
    }
}