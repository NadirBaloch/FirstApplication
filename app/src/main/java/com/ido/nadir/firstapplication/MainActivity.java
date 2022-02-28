package com.ido.nadir.firstapplication;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public String url = "https://6217195471e7672e53735dca.mockapi.io/api/v1/test/2";
    private static final String TAG = "MainActivity";
    public static final String PREF = "myPref";
    Button btLogin;
    EditText etUserName;
    EditText etPassword;
    TextView tvRegister;
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
        Log.e(TAG, "onCreate: Last Line");

        OKHTTPHandler okhttpHandler = new OKHTTPHandler();
        okhttpHandler.execute();
    }

    public class OKHTTPHandler extends AsyncTask {
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.i(TAG, "onPostExecute: download finished");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "onPreExecute: I am about to download names");
        }

        OkHttpClient client = new OkHttpClient();
        @Override
        protected Object doInBackground(Object[] objects) {
                Request.Builder builder = new Request.Builder();
                builder.url(url);
                Request request =builder.build();
                try{
                    Response response = client.newCall(request).execute();
                    Log.i(TAG, "download doInBackground: "+response.body().string());
                    return response.body().string();
                }catch(Exception e){
                    e.printStackTrace();
                }
            return null;
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
        SharedPreferences sharedPreferences = getSharedPreferences(PREF,MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        etUserName.setText(name);
        boolean check = sharedPreferences.getBoolean("check",false);
        if(check){
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
        if(password.equals("123")){
            //Move to next page
//            Toast.makeText(MainActivity.this, "Welcome test user", Toast.LENGTH_SHORT).show();
            if(cbStaySigned.isChecked()){
                SharedPreferences sharedPreferences = getSharedPreferences(PREF,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("check",true);
                editor.apply();
            }
            Intent intent = new Intent(MainActivity.this,Dashboard.class);
            intent.putExtra("USER",userName);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "LoginClicked: " );
        }
        Log.e(TAG, "LoginClicked: "+userName);
    }

    public void registerButtonClicked(View view) {
        Log.i(TAG, "registerButtonClicked: Register clicked");
    }


}