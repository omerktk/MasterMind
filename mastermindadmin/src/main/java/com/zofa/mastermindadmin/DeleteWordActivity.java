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

public class DeleteWordActivity extends AppCompatActivity {

    EditText name,pass;
    MaterialButton btn,btn2;
    ProgressBar progressBar;
    private static final String url=Api.apiurl+"mastermind/api/game/deleteword.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_word);



        name = findViewById(R.id.deleteid);
        btn = (MaterialButton) findViewById(R.id.deletebtn);
        progressBar = findViewById(R.id.progressBar2);




        btn.setOnClickListener(view -> {
            easyword();
        });


    }

    private void easyword() {

        String dusername = name.getText().toString().trim();


        if(dusername.isEmpty()){
            name.setError("Field Is Empty");
            name.requestFocus();
            return;
        }





        progressBar.setVisibility(View.VISIBLE);

        String qry="?email="+dusername;

        class dbprocess extends AsyncTask<String,Void,String>
        {
            @Override
            protected  void onPostExecute(String data)
            {
                if(data.equals("found"))
                {
                    name.setText("");

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(DeleteWordActivity.this, "Word Deleted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    name.setText("");


                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(DeleteWordActivity.this, "Failed to delete", Toast.LENGTH_LONG).show();

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