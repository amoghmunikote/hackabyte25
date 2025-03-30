package com.example.hackabyte25;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;



import com.example.sql.OrdersDataFetcher;

public class orderChecking extends Fragment {

    public orderChecking() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the ListView
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ListView orderListView = view.findViewById(R.id.order_list_view);

        // Fetch orders data
        OrdersDataFetcher dataFetcher = new OrdersDataFetcher(getContext());
        ArrayList<String> ordersList = dataFetcher.fetchOrdersData();

        // Handle empty orders list
        if (ordersList.isEmpty()) {
            ordersList.add("No orders found");
        }

        // Set the data to the ListView using an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, ordersList);
        orderListView.setAdapter(adapter);

        return view;
    }
}
