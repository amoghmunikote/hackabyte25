package com.example.sql;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class OrdersDataFetcher {

    private final Context context;

    // Constructor that takes context as parameter
    public OrdersDataFetcher(Context context) {
        this.context = context;
    }

    // Method to fetch all orders data from the orders table
    public ArrayList<String> fetchOrdersData() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();  // Open database in read mode

        // Define the columns you want to retrieve
        String[] columns = {"studentID", "food", "quantity"};

        // Execute a query to get all rows from the orders table
        Cursor cursor = db.query("orders", columns, null, null, null, null, null);

        ArrayList<String> ordersList = new ArrayList<>();

        // Check if there are any records and loop through them
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int studentID = cursor.getInt(cursor.getColumnIndex("studentID"));
                @SuppressLint("Range") String food = cursor.getString(cursor.getColumnIndex("food"));
                @SuppressLint("Range") int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                // Format the data and add it to the list
                String order = "Student ID: " + studentID + ", Food: " + food + ", Quantity: " + quantity;
                ordersList.add(order);
            } while (cursor.moveToNext());
        }

        // Close the cursor and the database after fetching data
        if (cursor != null) {
            cursor.close();
        }
        db.close();  // Close the database

        return ordersList;
    }
}
