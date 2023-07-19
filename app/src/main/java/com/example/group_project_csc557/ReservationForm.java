package com.example.group_project_csc557;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group_project_csc557.model.Reservation;
import com.example.group_project_csc557.model.SharedPrefManager;
import com.example.group_project_csc557.model.User;
import com.example.group_project_csc557.remote.ApiUtils;
import com.example.group_project_csc557.remote.ReservationService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationForm extends AppCompatActivity {

    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPhone;
    private EditText txtTime;
    private static TextView tvCreated; // static because need to be accessed by DatePickerFragment
    private static Date createdAt; // static because need to be accessed by DatePickerFragment

    private Spinner spinner_guests;
    private Context context;

    private Button btReserve;

    /**
     * Date picker fragment class
     * Reference: https://developer.android.com/guide/topics/ui/controls/pickers
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            // create a date object from selected year, month and day
            createdAt = new GregorianCalendar(year, month, day).getTime();

            // display in the label beside the button with specific date format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tvCreated.setText( sdf.format(createdAt) );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        // store context
        context = this;

        // get view objects references
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtTime = findViewById(R.id.txtTime);
        tvCreated = findViewById(R.id.tvCreated);
        spinner_guests = findViewById(R.id.spinner_guests);
        btReserve = findViewById(R.id.btReserve);

        // set default createdAt value, get current date
        createdAt = new Date();

        // display in the label beside the button with specific date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tvCreated.setText( sdf.format(createdAt));
    }

    /**
     * Called when pick date button is clicked. Display a date picker dialog
     * @param v
     */
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * Called when Add Book button is clicked
     * @param v
     */

    public void addNewBooking(View v) {

        //get values in form
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        String time = txtTime.getText().toString();
        String numGuest = spinner_guests.getSelectedItem().toString();

        // convert createdAt date to format in DB
        // reference: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String created_at = sdf.format(createdAt);

        // set updated_at with the same value as created_at
        String updated_at = created_at;

        // create a Reservation object
        Reservation r = new Reservation(name, email, phone, created_at, time, numGuest, null,null);

        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // send request to add new book to the REST API
        ReservationService reservationService = ApiUtils.getReservationService();
        Call<Reservation> call = reservationService.addReservation(user.getToken(), r);

        // execute
        call.enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {

                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // invalid session?
                if (response.code() == 401)
                    displayAlert("Invalid session. Please re-login");

                // book added successfully?
                Reservation addedReservation = response.body();
                if (addedReservation != null) {
                    // display message
                    Toast.makeText(context,
                            addedReservation.getName() + " added successfully.",
                            Toast.LENGTH_LONG).show();

                    // end this activity and forward user to BookListActivity
                    Intent intent = new Intent(context, CustView.class);
                    startActivity(intent);
                    finish();
                } else {
                    displayAlert("Add New Book failed.");
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                displayAlert("Error [" + t.getMessage() + "]");
                // for debug purpose
                Log.d("MyApp:", "Error: " + t.getCause().getMessage());
            }
        });
    }

    /**
     * Displaying an alert dialog with a single button
     * @param message - message to be displayed
     */
    public void displayAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}