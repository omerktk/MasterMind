package com.zofa.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LevelsActivity extends AppCompatActivity {
    MaterialButton easy,medium,hard,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        easy = (MaterialButton) findViewById(R.id.easy);
        medium = (MaterialButton) findViewById(R.id.medium);
        hard = (MaterialButton) findViewById(R.id.hard);
        back = (MaterialButton) findViewById(R.id.quit1);


        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("uname"))
        {

        }else{
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }

        easy.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),EasywordsActivity.class));
        });

        medium.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),MediumwordsActivity.class));
        });

        hard.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),HardwordsActivity.class));
        });

        back.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            finish();
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
        finish();
    }
}