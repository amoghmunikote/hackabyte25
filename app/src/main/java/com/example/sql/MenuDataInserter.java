package com.example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MenuDataInserter {

    private final Context context;

    // Constructor that takes context as a parameter
    public MenuDataInserter(Context context) {
        this.context = context;
    }

    // Method to insert a new menu item into the menu table
    public void insertMenuData(String foodItem, String allergyWarnings, int calories, double price) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();  // Open database in write mode

        // Create a ContentValues object to hold the data
        ContentValues values = new ContentValues();
        values.put("foodItem", foodItem);
        values.put("allergyWarnings", allergyWarnings);
        values.put("calories", calories);
        values.put("price", price);

        // Insert the new menu item into the "menu" table
        long result = db.insert("menu", null, values);

        if (result == -1) {
            Log.e("DB_ERROR", "Failed to insert data into menu table.");
        } else {
            Log.d("DB_SUCCESS", "Menu item inserted successfully: " + foodItem);
        }

        // Close the database after insertion
        db.close();
    }
}
