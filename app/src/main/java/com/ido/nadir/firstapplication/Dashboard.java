package com.ido.nadir.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    public static final String PREF = "myPref";
    private static final String TAG = "Dashboard";
    TextView tvGreetings;
    EditText etName;
    EditText etCurrency;
    DatabaseHelper dbHelper;
    TextView tvShowValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tvGreetings = findViewById(R.id.tvGreetings);
        dbHelper = new DatabaseHelper(Dashboard.this);

        etName = findViewById(R.id.etName);
        etCurrency = findViewById(R.id.etCurrency);
        tvShowValues = findViewById(R.id.tvShowData);
        String value = "";
        Cursor cursor = dbHelper.getCurrencyDetails();
        if (cursor.moveToFirst()) {
            do {
                value += cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME)) +
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.CURRENCY));
            } while (cursor.moveToNext());
        }
        tvShowValues.setText(value);
        if (getIntent().getExtras() != null) {
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

    public void InsertData(View view) {
        if (etName.getText().toString().equals("") || etCurrency.getText().toString().equals("")) {
            Toast.makeText(Dashboard.this, "Pullo Chezy blik", Toast.LENGTH_SHORT).show();
        } else {
            String name = etName.getText().toString();
            String currency = etCurrency.getText().toString();
            dbHelper.insertIntoTable(name, currency);
            Toast.makeText(Dashboard.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
        }
    }
}