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

public class Immigrationforms extends AppCompatActivity {
    //public static String CITY = "city";


    ArrayList<String> forms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immigrationforms_screen);

        //String cityName = getIntent().getStringExtra(CITY);
        //setTitle(cityName + " Immigration");

        //Getting immigration forms data from API
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://mc-project.herokuapp.com/immigration?country=canada";

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    //Parsing JSON data
                    JSONArray form = response.getJSONArray("forms");
                    for (int item = 0; item < form.length(); item++) {
                        JSONObject val = form.getJSONObject(item);
                        String name = val.getString("name");
                        String desc = val.getString("description");
                        forms.add(name + ": " + desc);
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.activity_listview, forms);

                    ListView listView = (ListView) findViewById(R.id.forms);
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
                Log.d("Forms Error", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
