package com.example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MenuDataEditor {

    private final Context context;

    // Constructor
    public MenuDataEditor(Context context) {
        this.context = context;
    }

    // Method to edit a menu item's details
    public void editMenuItem(String oldFoodItem, String newFoodItem, String newAllergyWarnings, int newCalories, double newPrice) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();  // Open database in write mode

        // Create ContentValues object to hold updated data
        ContentValues values = new ContentValues();
        values.put("foodItem", newFoodItem);
        values.put("allergyWarnings", newAllergyWarnings);
        values.put("calories", newCalories);
        values.put("price", newPrice);

        // Update the row where foodItem matches oldFoodItem
        db.update("menu", values, "foodItem = ?", new String[]{oldFoodItem});

        // Close the database
        db.close();
    }
}
