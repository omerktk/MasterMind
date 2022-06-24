package com.zofa.mastermind;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity {
    EditText username,age,mail,password;
    MaterialButton regbtn,btn2;
    ProgressBar progressBar;
    private static final String url="http://192.168.120.108/mastermind/api/users/reg.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        progressBar = findViewById(R.id.progressBar);
        username = findViewById(R.id.username);
        age = findViewById(R.id.age);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        regbtn = (MaterialButton) findViewById(R.id.loginbtn);
        regbtn.setOnClickListener(view -> {
            registerUser();
        });
    }

    private void registerUser() {

        String dmail = mail.getText().toString().trim();
        String dage = age.getText().toString().trim();
        String dusername = username.getText().toString().trim();
        String dpassword = password.getText().toString().trim();




        if(dusername.isEmpty()){
            username.setError("Field Is Empty");
            username.requestFocus();
            return;
        }

        if(dage.isEmpty()){
            age.setError("Field Is Empty");
            age.requestFocus();
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
            password.setError("Field Is Empty");
            password.requestFocus();
            return;
        }


        if(dpassword.length() < 6){
            password.setError("Password is less then 6 words!");
            password.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                username.setText("");
                age.setText("");
                mail.setText("");
                password.setText("");
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegActivity.this, "User Registered", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                username.setText("");
                age.setText("");
                mail.setText("");
                password.setText("");
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();

            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("name",dusername);
                map.put("pass",dpassword);
                map.put("email",dmail);
                return map;


            }
        };


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }
}