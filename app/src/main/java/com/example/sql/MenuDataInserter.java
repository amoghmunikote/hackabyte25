package com.example.sql;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

// This class handles inserting data into the "menu" table.
public class MenuDataInserter {

    // Context to interact with the database
    private final Context context;

    // Constructor that takes the context
    public MenuDataInserter(Context context) {
        this.context = context;
    }

    // Method to insert sample data into the "menu" table
    public void insertMenuData(String foodItem,String allergyWarnings,int calories,int price) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();  // Open the database in write mode

        // Create a ContentValues object to hold the values for a new row in the table
        ContentValues values = new ContentValues();

        // Insert data for the first food item (Pizza)
        values.put("foodItem", foodItem);  // Insert food item
        values.put("allergyWarnings", allergyWarnings);  // Insert allergy warnings
        values.put("calories", calories);  // Insert calories count
        values.put("price", price);  // Insert price
        db.insert("menu", null, values);  // Insert values into the "menu" table

        // Clear the ContentValues object to reuse it for another row
        values.clear();
        // Close the database after insertion
        db.close();
    }
}
