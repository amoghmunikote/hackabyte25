package com.example.hackabyte25.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.example.hackabyte25.ui.MenuItem;
import com.example.hackabyte25.R;

/**
 * MenuAdapter binds menu data to RecyclerView.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private List<MenuItem> menuList;

    // Constructor to initialize adapter with context and menu list
    public MenuAdapter(Context context, List<MenuItem> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    // Inflates the row_menu_item layout and returns a ViewHolder
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    // Binds data to ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuList.get(position);
        holder.foodName.setText(item.getFoodName());
        holder.allergyWarnings.setText(item.getAllergyWarnings());
        holder.calories.setText(String.valueOf(item.getCalories()));
        holder.price.setText(String.format("$%.2f", item.getPrice()));
    }

    // Returns the total count of menu items
    @Override
    public int getItemCount() {
        return menuList.size();
    }

    // ViewHolder class for RecyclerView
    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, allergyWarnings, calories, price;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            allergyWarnings = itemView.findViewById(R.id.allergyWarnings);
            calories = itemView.findViewById(R.id.calories);
            price = itemView.findViewById(R.id.price);
        }
    }
}
