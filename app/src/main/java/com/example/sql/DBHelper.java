package com.example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "preservedata.sqlite";
    public static final String MENU_TABLE = "MenuTable";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MENU_TABLE + " (foodItem TEXT PRIMARY KEY, allergyWarnings TEXT, calories INTEGER, price REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MENU_TABLE);
        onCreate(db);
    }

    public boolean insertMenuData(String foodItem, String allergyWarnings, int calories, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("foodItem", foodItem);
        values.put("allergyWarnings", allergyWarnings);
        values.put("calories", calories);
        values.put("price", price);

        long result = db.insert(MENU_TABLE, null, values);
        return result != -1;
    }

    public boolean removeMenuItem(String foodName) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(MENU_TABLE, "foodItem = ?", new String[]{foodName});
        return result > 0;
    }

    public Cursor getAllMenuItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + MENU_TABLE, null);
    }
}