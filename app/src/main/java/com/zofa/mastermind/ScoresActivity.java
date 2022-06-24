package com.zofa.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ScoresActivity extends AppCompatActivity {

    private static final String url="http://192.168.120.108/mastermind/api/users/scores.php";
    RecyclerView lv;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        lv = (RecyclerView) findViewById(R.id.lv1);
        progressBar = findViewById(R.id.progressBar);

        lv.setLayoutManager(new LinearLayoutManager(this));
        
        processdata();

    }

    private void processdata() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson=builder.create();
                scoredata data[] = gson.fromJson(response,scoredata[].class);

                scoreadapter adapter = new scoreadapter(data);

                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Failed to Load", Toast.LENGTH_LONG).show();

            }
        }
        );


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }
}