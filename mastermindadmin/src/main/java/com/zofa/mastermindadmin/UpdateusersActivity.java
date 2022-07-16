package com.zofa.mastermindadmin;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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


public class UpdateusersActivity extends AppCompatActivity {


    EditText name,pass,id,mail;
    MaterialButton btn,btn2;
    ProgressBar progressBar;

    private static final String url=Api.apiurl+"mastermind/api/users/update.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateusers);
        pass = findViewById(R.id.passwordupdate);
        id = findViewById(R.id.idupdate);
        mail = findViewById(R.id.mailupdate);
        name = findViewById(R.id.usernameupdate);
        btn = (MaterialButton) findViewById(R.id.loginbtnupdate);
        progressBar = findViewById(R.id.progressBar88);


        btn.setOnClickListener(view -> {
            loginuser();
        });

    }


    private void loginuser() {

        String dusername = name.getText().toString().trim();
        String dpassword = pass.getText().toString().trim();
        String did = id.getText().toString().trim();
        String dmail = mail.getText().toString().trim();


        if(did.isEmpty()){
            id.setError("Field Is Empty");
            id.requestFocus();
            return;
        }

        if(dusername.isEmpty()){
            name.setError("Field Is Empty");
            name.requestFocus();
            return;
        }

        if(dmail.isEmpty()){
            mail.setError("Field Is Empty");
            mail.requestFocus();
            return;
        }



        if(!Patterns.EMAIL_ADDRESS.matcher(dmail).matches()){
            mail.setError("Email Is Not Vaild");
            mail.requestFocus();
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

        String qry="?email="+dmail+"&pass="+dpassword+"&id="+did+"&name="+dusername;

        class dbprocess extends AsyncTask<String,Void,String>
        {
            @Override
            protected  void onPostExecute(String data)
            {
                if(data.equals("found"))
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(UpdateusersActivity.this, "User Data Updated", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),UserslistActivity.class));
                }
                else
                {
                    id.setText("");
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(UpdateusersActivity.this, "User not Found", Toast.LENGTH_LONG).show();

                }
            }
            @Override
            protected String doInBackground(String... params)
            {
                String furl=params[0];

                try
                {
                    URL url=new URL(furl);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    return br.readLine();

                }catch (Exception ex)
                {
                    return ex.getMessage();
                }
            }
        }

        dbprocess obj=new dbprocess();
        obj.execute(url+qry);


    }



}