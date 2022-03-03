package com.ido.nadir.firstapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String data = "init ";
    String studentName;
    String topic;
    boolean isSubmitted;
    DatabaseHelper databaseHelper;

    public String url = "https://6217195471e7672e53735dca.mockapi.io/api/v1/test/2";
    private static final String TAG = "MainActivity";
    public static final String PREF = "myPref";
    Button btLogin;
    EditText etUserName;
    EditText etPassword;
    TextView tvRegister;
    TextView tvAPIData;
    CheckBox cbStaySigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = findViewById(R.id.btLogin);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        tvRegister = findViewById(R.id.tvRegister);
        cbStaySigned = findViewById(R.id.cbStaySigned);
        tvAPIData = findViewById(R.id.tvAPIData);
        Log.e(TAG, "onCreate: Last Line");

        OKHTTPHandler okhttpHandler = new OKHTTPHandler();
        okhttpHandler.execute();

    }

    public class OKHTTPHandler extends AsyncTask<String, String, String> {
        String myData;
        JSONObject jObject;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                jObject = new JSONObject(s);
//                JSONArray jarray  = new JSONArray(s);
                studentName = jObject.getString("studentname");
                topic = jObject.getString("topic");
                isSubmitted = jObject.getBoolean("isSubmitted");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "onPostExecute: after download" + s);
            tvAPIData.setText(studentName + " " + topic);
//            tvAPIData.setText(s);
            Log.i(TAG, "onPostExecute: " + myData);
        }

        OKHTTPHandler() {
            myData = "nothing Yet";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvAPIData.setText("Downloading");
            Log.i(TAG, "onPreExecute: I am about to download names");
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            Request request = builder.build();
            try {
                Response response = client.newCall(request).execute();
                //Log.i(TAG, "doInBackground: "+response.body().string());
                return "" + response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: is called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: is called");
        SharedPreferences sharedPreferences = getSharedPreferences(PREF, MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        etUserName.setText(name);
        boolean check = sharedPreferences.getBoolean("check", false);
        if (check) {
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", etUserName.getText().toString());
        editor.apply();

    }


    public void LoginClicked(View view) {
//        try{
//            Toast.makeText(MainActivity.this,etUserName.getText().toString(), Toast.LENGTH_SHORT).show();
//        }catch(Exception e){
//            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//        }
        String userName;
        String password;
        userName = etUserName.getText().toString();
        password = etPassword.getText().toString();
        if (password.equals("123")) {
            //Move to next page
//            Toast.makeText(MainActivity.this, "Welcome test user", Toast.LENGTH_SHORT).show();
            if (cbStaySigned.isChecked()) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREF, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("check", true);
                editor.apply();
            }
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            intent.putExtra("USER", userName);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "LoginClicked: ");
        }
        Log.e(TAG, "LoginClicked: " + userName);
    }

    public void registerButtonClicked(View view) {
        Log.i(TAG, "registerButtonClicked: Register clicked");
    }


}