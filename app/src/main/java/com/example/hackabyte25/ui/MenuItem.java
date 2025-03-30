package com.example.hackabyte25.ui;

/**
 * MenuItem class represents a single menu item with details.
 */
public class MenuItem {
    private String foodName;
    private String allergyWarnings;
    private int calories;
    private double price;

    // Constructor to initialize all fields
    public MenuItem(String foodName, String allergyWarnings, int calories, double price) {
        this.foodName = foodName;
        this.allergyWarnings = allergyWarnings;
        this.calories = calories;
        this.price = price;
    }

    // Getter method for food name
    public String getFoodName() {
        return foodName;
    }

    // Getter method for allergy warnings
    public String getAllergyWarnings() {
        return allergyWarnings;
    }

    // Getter method for calories
    public int getCalories() {
        return calories;
    }

    // Getter method for price
    public double getPrice() {
        return price;
    }
}
