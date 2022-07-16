package com.zofa.mastermindadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class UsersActivity extends AppCompatActivity {
    MaterialButton add,update,list,quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        add = (MaterialButton) findViewById(R.id.adduserdata);
        update = (MaterialButton) findViewById(R.id.updateuserdata);
        list = (MaterialButton) findViewById(R.id.userlist);

        add.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),RegActivity.class));
        });
        list.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),UserslistActivity.class));
        });
        update.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),UpdateusersActivity.class));
        });
    }

}