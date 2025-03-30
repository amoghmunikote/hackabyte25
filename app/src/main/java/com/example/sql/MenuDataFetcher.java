package com.example.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MenuDataFetcher {
    private DBHelper dbHelper;

    public MenuDataFetcher(Context context) {
        dbHelper = new DBHelper(context);
    }

    public List<String> getAllMenuItems() {
        List<String> menuItems = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT foodItem FROM menu", null);

        if (cursor.moveToFirst()) {
            do {
                menuItems.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return menuItems;
    }
}
