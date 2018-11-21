package com.thealienobserver.nikhil.travon.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.adapters.WeatherCardAdapter;
import com.thealienobserver.nikhil.travon.apihandlers.WeatherHandler;
import com.thealienobserver.nikhil.travon.models.WeatherModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherScreen extends AppCompatActivity {

    public static final String COUNTRY_CODE_PARAM = "COUNTRY_CODE_PARAM";
    public static final String LAT_LON_PARAM = "LAT_LON_PARAM";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    private String mLatitude;
    private String mLongitude;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_screen);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String Latitude = String.valueOf(b.get("LATITUDE"));
        String Longitude = String.valueOf(b.get("LONGITUDE"));

        WeatherHandler weatherHandler = new WeatherHandler(this) {
            @Override
            public void postFetchingWeather(ArrayList<WeatherModel> weatherModel) {
                WeatherScreen.this.setupWeatherCards(weatherModel);
            }
        };
        weatherHandler.getFiveDaysWeather(Latitude, Longitude);
    }

    private void setupWeatherCards(ArrayList<WeatherModel> weatherModel) {
        RecyclerView weatherRecyclerView = findViewById(R.id.weatherRecyclerView);
        weatherRecyclerView.setAdapter(new WeatherCardAdapter(this, weatherModel));
        //weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        weatherRecyclerView.setLayoutManager(linearLayoutManager);
    }
}