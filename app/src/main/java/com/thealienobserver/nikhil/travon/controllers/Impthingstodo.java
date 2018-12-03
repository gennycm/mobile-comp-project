package com.thealienobserver.nikhil.travon.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Impthingstodo extends AppCompatActivity {

    //public static String CITY = "city";


    ArrayList<String> fivething = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importantthings_screen);

        //String cityName = getIntent().getExtras().getString(CITY);
        //setTitle(cityName + " Immigration");


        //Getting immigration important things to do data from API
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://mc-project.herokuapp.com/immigration?country=canada";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                     //Parsing JSON data
                    JSONArray important = response.getJSONArray("important_things");
                    for(int item = 0; item < important.length(); item++) {
                        String name = important.getString(item);
                        fivething.add(name);
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.activity_listview, fivething);

                    ListView listView = (ListView) findViewById(R.id.fiveimportantthings);
                    listView.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("FiveThings Err trycatch", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Handle error
                Log.d("FiveThings Error", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}