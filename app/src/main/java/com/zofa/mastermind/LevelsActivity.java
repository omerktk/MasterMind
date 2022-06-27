package com.zofa.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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

    }
}