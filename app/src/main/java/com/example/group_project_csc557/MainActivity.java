package com.example.group_project_csc557;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group_project_csc557.model.SharedPrefManager;
import com.example.group_project_csc557.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get reference to the textview
        TextView txtHello = findViewById(R.id.txtHello);

        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // set the textview to display username
        txtHello.setText("Hello " + ((User) user).getUsername() + " !");
    }

    public void doLogout(View view) {
        // clear the shared preferences
        SharedPrefManager.getInstance(getApplicationContext()).logout();

        // display message
        Toast.makeText(getApplicationContext(),
                "You have successfully logged out.",
                Toast.LENGTH_LONG).show();

        // forward to LoginActivity
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}