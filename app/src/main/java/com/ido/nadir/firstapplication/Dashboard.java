package com.ido.nadir.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    TextView tvGreetings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tvGreetings = findViewById(R.id.tvGreetings);
        String userName = getIntent().getExtras().getString("USER");
        tvGreetings.setText("Welcome "+userName);
    }
}