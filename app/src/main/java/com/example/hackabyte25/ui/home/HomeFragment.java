package com.example.hackabyte25.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackabyte25.databinding.FragmentHomeBinding;
import com.example.sql.DBHelper;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private DBHelper dbHelper;
    private MenuAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbHelper = new DBHelper(requireContext());

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        loadMenuItems();

        binding.textHome.setText("Menu");

        return root;
    }

    private void loadMenuItems() {
        Cursor cursor = dbHelper.getAllMenuItems();
        if (cursor != null) {
            adapter = new MenuAdapter(cursor);
            binding.recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null && adapter.cursor != null) {
            adapter.cursor.close();
        }
        if (dbHelper != null) {
            dbHelper.close();
        }
        binding = null;
    }

    private static class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
        private final Cursor cursor;

        MenuAdapter(Cursor cursor) {
            this.cursor = cursor;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (cursor != null && cursor.moveToPosition(position)) {
                String foodItem = cursor.getString(cursor.getColumnIndex("foodItem"));
                String allergyWarnings = cursor.getString(cursor.getColumnIndex("allergyWarnings"));
                int calories = cursor.getInt(cursor.getColumnIndex("calories"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));

                holder.text1.setText(foodItem);
                holder.text2.setText(String.format("Price: $%.2f | Calories: %d\nAllergies: %s",
                        price, calories, allergyWarnings));

                holder.text1.setTextSize(16);
                holder.text2.setTextSize(14);
                holder.text1.setTextColor(android.graphics.Color.WHITE);
                holder.text2.setTextColor(android.graphics.Color.WHITE);
            }
        }

        @Override
        public int getItemCount() {
            return cursor != null ? cursor.getCount() : 0;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            final TextView text1;
            final TextView text2;

            ViewHolder(View view) {
                super(view);
                text1 = view.findViewById(android.R.id.text1);
                text2 = view.findViewById(android.R.id.text2);
            }
        }
    }
}