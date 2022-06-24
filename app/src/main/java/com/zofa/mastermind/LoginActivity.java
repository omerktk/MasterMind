package com.zofa.mastermind;

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

    private static final String url="http://192.168.120.108/mastermind/api/users/login.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("uname"))
        {

            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            finish();

    }else{

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

            btn.setOnClickListener(view -> {
                loginuser();
            });

        }
    }

    private void loginuser() {

        String dusername = name.getText().toString().trim();
        String dpassword = pass.getText().toString().trim();

        if(dusername.isEmpty()){
            name.setError("Field Is Empty");
            name.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(dusername).matches()){
            name.setError("Email Is Not Vaild");
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

        String qry="?email="+dusername+"&pass="+dpassword;

        class dbprocess extends AsyncTask<String,Void,String>
        {
            @Override
            protected  void onPostExecute(String data)
            {
                if(data.equals("found"))
                {
                    SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("uname",name.getText().toString());
                    editor.commit();
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                    finish();
                }
                else
                {
                    name.setText("");
                    pass.setText("");
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Failed to Login", Toast.LENGTH_LONG).show();

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