package com.zofa.mastermindadmin;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

        about.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),UsersActivity.class));
        });

        quit.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setIcon(R.drawable.logo)
                    .setCancelable(false)
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    }).create().show();

        });

        logoutbtn.setOnClickListener(view -> {

        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setIcon(R.drawable.logo)
                .setCancelable(false)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }).create().show();

    }


}