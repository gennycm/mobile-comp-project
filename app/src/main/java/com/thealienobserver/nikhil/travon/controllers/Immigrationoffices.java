package com.thealienobserver.nikhil.travon.controllers;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thealienobserver.nikhil.travon.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Immigrationoffices extends Immigrationmainscreen {
    public static String CITY = "city";


    ArrayList<String> offices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immigrationoffices_screen);

        String cityName = getIntent().getStringExtra(CITY);
        setTitle(cityName + " Offices");


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://mc-project.herokuapp.com/immigration?country=canada";

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {

                    JSONArray office = response.getJSONArray("offices");
                    for (int item = 0; item < office.length(); item++) {
                        JSONObject val = office.getJSONObject(item);
                        String name = val.getString("name");
                        String desc = val.getString("description");
                        offices.add(name + ": " + desc);
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.activity_listview, offices);

                    ListView listView = (ListView) findViewById(R.id.offices);
                    listView.setAdapter(adapter);


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Offices Err trycatch", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Handle error
                Log.d("Offices Error", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
