package com.zofa.mastermindadmin;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScoresActivity extends AppCompatActivity {
    ListView lv;
    String fetch_data= "http://192.168.120.108/mastermind/api/users/scores.php";

    Adapter adapter;
    List<Mind> ViewAdsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        lv = findViewById(R.id.lv1);
        ViewAdsList = new ArrayList<>();
        GetData();
    }



    public void GetData(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, fetch_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    //   JSONArray jsonArray=jsonObject.getJSONArray("all");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        //JSONObject object=jsonArray.getJSONObject(i);
                        Mind list=new Mind(jsonObject.getString("name"),jsonObject.getString("score"));
                        ViewAdsList.add(list);
                    }
                    adapter=new Adapter(ViewAdsList,ScoresActivity.this);
                    lv.setAdapter(adapter);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ScoresActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}