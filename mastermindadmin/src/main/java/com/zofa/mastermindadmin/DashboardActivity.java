package com.zofa.mastermindadmin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class DashboardActivity extends AppCompatActivity {
    MaterialButton logoutbtn,play,score,about,quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        logoutbtn = (MaterialButton) findViewById(R.id.logout);
        play = (MaterialButton) findViewById(R.id.play);
        score = (MaterialButton) findViewById(R.id.high);
        about = (MaterialButton) findViewById(R.id.about);
        quit = (MaterialButton) findViewById(R.id.quit);

        play.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),LevelsActivity.class));
        });

        score.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),ScoresActivity.class));
        });

        quit.setOnClickListener(view -> {
            finish();
        });

        logoutbtn.setOnClickListener(view -> {

        });
    }


}