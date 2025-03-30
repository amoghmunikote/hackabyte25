package com.example.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MenuDataRemover {

    private final Context context;

    // Constructor that takes context as a parameter
    public MenuDataRemover(Context context) {
        this.context = context;
    }

    // Method to remove a menu item from the menu table
    public void removeMenuItem(String foodItem) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();  // Open database in write mode

        // Delete the menu item where foodItem matches
        int rowsAffected = db.delete("menu", "foodItem = ?", new String[]{foodItem});

        if (rowsAffected > 0) {
            Log.d("DB_SUCCESS", "Menu item deleted successfully: " + foodItem);
        } else {
            Log.e("DB_ERROR", "Failed to delete menu item: " + foodItem);
        }

        // Close the database after deletion
        db.close();
    }
}
