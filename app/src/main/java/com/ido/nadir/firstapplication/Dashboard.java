package com.ido.nadir.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    public static final String PREF = "myPref";
    private static final String TAG = "Dashboard";
    TextView tvGreetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tvGreetings = findViewById(R.id.tvGreetings);
        if (getIntent().getExtras()!= null) {
            String userName = getIntent().getExtras().getString("USER");
            tvGreetings.setText("Welcome " + userName);
        }
        Log.e(TAG, "onCreate: First Function");
    }

    public void LogOut(View view) {
        //TODO call sharedPreference PREF and set its check variable to false


        //TODO call activity Main from intent and close this activity with finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: Second Function");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: is called");
    }
}