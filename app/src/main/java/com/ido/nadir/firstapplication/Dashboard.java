package com.ido.nadir.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    private static final String TAG = "Dashboard";
    TextView tvGreetings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tvGreetings = findViewById(R.id.tvGreetings);
        String userName = getIntent().getExtras().getString("USER");
        tvGreetings.setText("Welcome "+userName);
        Log.e(TAG, "onCreate: First Function" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: Second Function" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: is called" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: is called" );
    }
}