package com.zofa.mastermindadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserslistActivity extends AppCompatActivity {

    ListView lv;
    String fetch_data= "mastermind/api/users/get.php";
    MaterialButton btn,btn2;
    MyAdapter adapter;
    List<ModelMind> ViewAdsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userslist);
        lv = findViewById(R.id.lv1);
        ViewAdsList = new ArrayList<>();

        GetData();
    }

    public void GetData(){
        String apiurldata = Api.apiurl + fetch_data;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, apiurldata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        ModelMind list=new ModelMind(jsonObject.getString("id")+" | Email : "+jsonObject.getString("email") ,jsonObject.getString("name"));
                        ViewAdsList.add(list);
                    }
                    adapter=new MyAdapter(ViewAdsList,UserslistActivity.this);
                    lv.setAdapter(adapter);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserslistActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}