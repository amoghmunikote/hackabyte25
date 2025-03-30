package com.example.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MenuDataRemover {

    private final Context context;

    // Constructor
    public MenuDataRemover(Context context) {
        this.context = context;
    }

    // Method to remove a menu item by food name
    public void removeMenuItem(String foodItem) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();  // Open database in write mode

        // Delete the row where foodItem matches
        db.delete("menu", "foodItem = ?", new String[]{foodItem});

        // Close the database
        db.close();
    }
}
