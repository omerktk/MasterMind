package com.zofa.mastermindadmin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText name,pass;
    MaterialButton btn,btn2;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



            pass = findViewById(R.id.password);
            name = findViewById(R.id.username);
            btn = (MaterialButton) findViewById(R.id.loginbtn);
            progressBar = findViewById(R.id.progressBar2);




            btn.setOnClickListener(view -> {
               loginuser();
            });


    }

    private void loginuser() {

        String dusername = name.getText().toString().trim();
        String dpassword = pass.getText().toString().trim();

        if(dusername.isEmpty()){
            name.setError("Field Is Empty");
            name.requestFocus();
            return;
        }


        if(dpassword.isEmpty()){
            pass.setError("Field Is Empty");
            pass.requestFocus();
            return;
        }


        if(dpassword.length() < 6){
            pass.setError("Password is less then 6 words!");
            pass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        if(dusername.toString().equals("Karachi") && dpassword.toString().equals("Karachi")){
            SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("uname",name.getText().toString());
            editor.commit();
            progressBar.setVisibility(View.GONE);
            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            finish();
        }else{
            name.setText("");
            pass.setText("");
            progressBar.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this, "Failed to Login", Toast.LENGTH_LONG).show();

        }





    }
}