package com.ido.nadir.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {
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
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: Register Clicked");
            }
        });
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
}