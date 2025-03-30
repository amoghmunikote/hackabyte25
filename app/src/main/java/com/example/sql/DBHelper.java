package com.example.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// This class helps manage the SQLite database and handle database creation, version management, and upgrades.
public class DBHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "preservedata.sqlite";  // Database file name
    private static final int DATABASE_VERSION = 1;  // Initial version of the database

    // Table names
    private static final String TABLE_MENU = "menu";
    private static final String TABLE_ORDERS = "orders";

    // Columns for the "menu" table
    private static final String COLUMN_FOOD_ITEM = "foodItem";
    private static final String COLUMN_ALLERGY_WARNINGS = "allergyWarnings";
    private static final String COLUMN_CALORIES = "calories";
    private static final String COLUMN_PRICE = "price";

    // Columns for the "orders" table
    private static final String COLUMN_STUDENT_ID = "studentID";
    private static final String COLUMN_FOOD = "food";
    private static final String COLUMN_QUANTITY = "quantity";

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the "menu" table
        String CREATE_TABLE_MENU = "CREATE TABLE " + TABLE_MENU + " ("
                + COLUMN_FOOD_ITEM + " TEXT,"
                + COLUMN_ALLERGY_WARNINGS + " TEXT,"
                + COLUMN_CALORIES + " INTEGER,"
                + COLUMN_PRICE + " REAL)";

        // Create the "orders" table
        String CREATE_TABLE_ORDERS = "CREATE TABLE " + TABLE_ORDERS + " ("
                + COLUMN_STUDENT_ID + " INTEGER,"
                + COLUMN_FOOD + " TEXT,"
                + COLUMN_QUANTITY + " INTEGER)";

        // Execute SQL statements to create tables
        db.execSQL(CREATE_TABLE_MENU);
        db.execSQL(CREATE_TABLE_ORDERS);
    }

    // Called when the database needs an upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);

        // Recreate the tables
        onCreate(db);
    }
}
