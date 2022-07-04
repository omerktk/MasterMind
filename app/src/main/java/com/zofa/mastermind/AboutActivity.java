package com.zofa.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

public class AboutActivity extends AppCompatActivity {
    MaterialButton easy,medium,hard,back;
    RequestQueue requestQueue;
    TextView tv,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        back = (MaterialButton) findViewById(R.id.quit1);

        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("uname"))
        {

        }else{
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }

        back.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            finish();
        });
        GetScore();

    }


    private void GetScore() {

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Api.apiurl +"mastermind/api/game/about.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                tv = findViewById(R.id.mediumtv);
                tv2 = findViewById(R.id.hardtv);

                try {
                    String a = response.getString("easy");
                    String b = response.getString("hard");
                    tv.setText("We have 0"+a+" Easy Words to solve.");
                    tv2.setText("We have 0"+b+" Medium/Hard Words to solve.");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error"+e, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
        finish();
    }
}