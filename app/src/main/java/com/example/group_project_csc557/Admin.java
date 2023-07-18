package com.example.group_project_csc557;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    private ListView listViewReservations;
    private ArrayList<String> reservationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Get a reference to the ListView
        listViewReservations = findViewById(R.id.list_view_reservations);

        // Initialize the reservations list with some sample data (you should retrieve this data from your database)
        reservationsList = new ArrayList<>();
        reservationsList.add("Reservation 1: John Doe - 2 guests - Date: 2023-07-25 - Time: 19:00");
        reservationsList.add("Reservation 2: Jane Smith - 4 guests - Date: 2023-07-27 - Time: 18:30");

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                reservationsList
        );

        // Set the adapter to the ListView
        listViewReservations.setAdapter(adapter);
    }
}
