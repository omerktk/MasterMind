package com.zofa.mastermindadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class LevelsActivity extends AppCompatActivity {
    MaterialButton list,adde,addh,delete,quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        list = (MaterialButton) findViewById(R.id.wordlist);
        adde = (MaterialButton) findViewById(R.id.addeasyword);
        addh = (MaterialButton) findViewById(R.id.addhardword);
        delete = (MaterialButton) findViewById(R.id.dword);

        list.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),WordslistActivity.class));
        });

        adde.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),AddEasyActivity.class));
        });

        addh.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),AddHardActivity.class));
        });

        delete.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),DeleteWordActivity.class));
        });

    }
}