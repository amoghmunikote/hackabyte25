package com.example.hackabyte25.ui; // Package name

import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import com.example.hackabyte25.R;
import com.example.sql.DBHelper; // Import Database Helper

public class EditMenu extends AppCompatActivity {

    private EditText etFoodName, etAllergyWarnings, etCalories, etPrice;
    private Button btnAddFood, btnRemoveFood;
    private ListView menuListView;
    private DBHelper dbHelper;
    private MenuAdapter menuAdapter;
    private ArrayList<MenuItem> menuList;
    private String selectedFoodName; // Stores the selected food for deletion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_menu); // Set correct XML layout

        // Initialize UI components
        etFoodName = findViewById(R.id.etFoodName);
        etAllergyWarnings = findViewById(R.id.etAllergyWarnings);
        etCalories = findViewById(R.id.etCalories);
        etPrice = findViewById(R.id.etPrice);
        btnAddFood = findViewById(R.id.btnAddFood);
        btnRemoveFood = findViewById(R.id.btnRemoveFood);
        menuListView = findViewById(R.id.menuListView);

        // Initialize database helper and array list
        dbHelper = new DBHelper(this);
        menuList = new ArrayList<>();

        // Load menu items into the list view
        loadMenuData();

        // Button Listeners
        btnAddFood.setOnClickListener(v -> addFoodItem());
        btnRemoveFood.setOnClickListener(v -> removeFoodItem());

        // ListView item click listener (for selecting items to remove)
        menuListView.setOnItemClickListener((parent, view, position, id) -> {
            MenuItem selectedItem = menuList.get(position);
            selectedFoodName = selectedItem.getFoodName(); // Store selected food
            etFoodName.setText(selectedFoodName);
        });
    }

    // Fetches menu items from DB and loads them into the ListView
    private void loadMenuData() {
        Cursor cursor = dbHelper.getAllMenuItems();
        menuList.clear();

        if (cursor.moveToFirst()) {
            do {
                String foodName = cursor.getString(0);
                String allergyWarnings = cursor.getString(1);
                int calories = cursor.getInt(2);
                double price = cursor.getDouble(3);
                menuList.add(new MenuItem(foodName, allergyWarnings, calories, price));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Attach adapter to the ListView
        menuAdapter = new MenuAdapter(this, menuList);
        menuListView.setAdapter((ListAdapter) menuAdapter);
    }

    // Adds a food item to the database
    private void addFoodItem() {
        String food = etFoodName.getText().toString().trim();
        String allergyWarnings = etAllergyWarnings.getText().toString().trim();
        String caloriesStr = etCalories.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();

        // Validate input fields
        if (food.isEmpty() || caloriesStr.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Fill in all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        int calories = Integer.parseInt(caloriesStr);
        double price = Double.parseDouble(priceStr);

        // Insert into DB and show confirmation
        if (dbHelper.insertMenuData(food, allergyWarnings, calories, price)) {
            Toast.makeText(this, "Food added!", Toast.LENGTH_SHORT).show();
            loadMenuData(); // Refresh ListView
        } else {
            Toast.makeText(this, "Error adding food!", Toast.LENGTH_SHORT).show();
        }
    }

    // Removes a food item from the database
    private void removeFoodItem() {
        if (selectedFoodName == null || selectedFoodName.isEmpty()) {
            Toast.makeText(this, "Select a food item to remove!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Remove from DB and update UI
        if (dbHelper.removeMenuItem(selectedFoodName)) {
            Toast.makeText(this, "Food removed!", Toast.LENGTH_SHORT).show();
            selectedFoodName = null;
            loadMenuData();
        } else {
            Toast.makeText(this, "Food not found!", Toast.LENGTH_SHORT).show();
        }
    }
}
