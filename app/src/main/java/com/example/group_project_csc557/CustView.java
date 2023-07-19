package com.example.group_project_csc557;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class CustView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_view);

        Button buttonBookingStatus = findViewById(R.id.btStatus);
        Button buttonNewBooking = findViewById(R.id.btNewBooking);

        buttonBookingStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the action when "Booking Status" button is clicked
                // For example, open a new activity to display booking status
            }
        });

        buttonNewBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the action when "New Booking" button is clicked
                // For example, open a new activity to create a new reservation
                finish();
                startActivity(new Intent(getApplicationContext(), ReservationForm.class));
            }
        });
    }
}
