package com.example.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import java.util.ArrayList;

// This class is responsible for querying the "menu" table and returning the menu items.
public class MenuDataFetcher {

    private final Context context;

    // Constructor that takes the context to interact with the database
    public MenuDataFetcher(Context context) {
        this.context = context;
    }

    // Method to fetch the menu data from the database
    public ArrayList<String> fetchMenuData() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();  // Open the database in read mode

        // Columns to retrieve from the "menu" table
        String[] columns = { "foodItem", "allergyWarnings", "calories", "price" };

        // Execute a query to select all rows from the "menu" table
        Cursor cursor = db.query("menu", columns, null, null, null, null, null);

        // Create an ArrayList to store the formatted string of menu items
        ArrayList<String> menuItems = new ArrayList<>();

        // Check if the cursor has data
        if (cursor != null && cursor.moveToFirst()) {
            // Loop through all rows in the cursor
            do {
                // Get the data from each column of the current row
                int foodItemIndex = cursor.getColumnIndex("foodItem");
                int caloriesIndex = cursor.getColumnIndex("calories");
                int priceIndex = cursor.getColumnIndex("price");
                int allergyWarningsIndex=cursor.getColumnIndex("allergyWarnings");

                if (foodItemIndex != -1 && caloriesIndex != -1 && priceIndex != -1 && allergyWarningsIndex !=-1) {
                    String foodItem = cursor.getString(foodItemIndex);
                    int calories = cursor.getInt(caloriesIndex);
                    double price = cursor.getDouble(priceIndex);
                    String allergyWarnings=cursor.getString(allergyWarningsIndex);
                    menuItems.add(foodItem + " - " + "Allergy Warnings: " + allergyWarnings +
                            ", Calories: " + calories + ", Price: $" + price);
                }
            } while (cursor.moveToNext());  // Move to the next row
        }

        // Close the cursor after retrieving data
        if (cursor != null) {
            cursor.close();
        }

        // Close the database after retrieving data
        db.close();

        // Return the list of formatted menu items
        return menuItems;
    }
}
