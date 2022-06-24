package com.zofa.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;

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
        btn2 = (MaterialButton) findViewById(R.id.btn2);
        progressBar = findViewById(R.id.progressBar2);

        //button 2 change screen onclick
        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,RegActivity.class);
            startActivity(intent);
        });

    }
}