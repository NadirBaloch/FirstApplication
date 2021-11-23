package com.ido.nadir.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    public static final String PREF = "myPref";
    private static final String TAG = "Dashboard";
    TextView tvGreetings;
    EditText etName;
    EditText etCurrency;
    DatabaseHelper dbHelper;
    ListView lvListView;
    Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tvGreetings = findViewById(R.id.tvGreetings);
        dbHelper = new DatabaseHelper(Dashboard.this);
        country = new Country();
        etName = findViewById(R.id.etName);
        etCurrency = findViewById(R.id.etCurrency);
        lvListView = findViewById(R.id.lvListView);
        populateList();
        if (getIntent().getExtras() != null) {
            String userName = getIntent().getExtras().getString("USER");
            tvGreetings.setText("Welcome " + userName);
        }
        Log.e(TAG, "onCreate: First Function");
    }

    private void populateList() {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = dbHelper.getCurrencyDetails();
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME)) +"->"+
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.CURRENCY)));
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Dashboard.this,
                android.R.layout.simple_list_item_activated_1, arrayList);
        lvListView.setAdapter(arrayAdapter);
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
            Toast.makeText(Dashboard.this, "Insert values first", Toast.LENGTH_SHORT).show();
        } else {
            country.name = etName.getText().toString();
            country.currency = etCurrency.getText().toString();
            dbHelper.insertIntoTable(country);
            etName.setText("");
            etCurrency.setText("");
            Toast.makeText(Dashboard.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            populateList();
        }
    }
}