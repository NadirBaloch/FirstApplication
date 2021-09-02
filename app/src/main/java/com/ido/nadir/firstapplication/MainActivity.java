package com.ido.nadir.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btLogin;
    EditText etUserName;
    EditText etPassword;
    TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btLogin = findViewById(R.id.btLogin);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        tvRegister = findViewById(R.id.tvRegister);
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
        if(userName.equals("test")&&password.equals("123")){
            //Move to next page
//            Toast.makeText(MainActivity.this, "Welcome test user", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,Dashboard.class);
            intent.putExtra("USER",userName);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    public void registerClicked(View view) {
        Toast.makeText(MainActivity.this,"Register Clicked",Toast.LENGTH_LONG).show();
    }
}