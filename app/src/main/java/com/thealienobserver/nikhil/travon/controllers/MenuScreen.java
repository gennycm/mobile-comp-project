package com.thealienobserver.nikhil.travon.controllers;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.apihandlers.MainMenuHandler;
import com.thealienobserver.nikhil.travon.models.RecommendedPlace;

import java.util.ArrayList;
import java.util.Locale;

public class MenuScreen extends AppCompatActivity {
    public static final String PlaceID = "PLACE_ID";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String CITY = "CITY";

    private String mLatitude;
    private String mLongitude;
    private String mCity;
    private TextView cityTextView;
    private ImageView cityImageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_screen);

        cityTextView = findViewById(R.id.cityTextView);
        cityImageview = findViewById(R.id.cityImageView);

        mLatitude = String.valueOf(getIntent().getDoubleExtra(LATITUDE, 0.0));
        mLongitude = String.valueOf(getIntent().getDoubleExtra(LONGITUDE, 0.0));
        mCity = getIntent().getStringExtra(CITY);

        cityTextView.setText(mCity);

        MainMenuHandler mainMenuHandler = new MainMenuHandler(this, cityImageview);
        mainMenuHandler.getPlaceID(mLongitude, mLatitude);


    }


    public void openRecommendedplace(View view) {

        Intent intent = new Intent(MenuScreen.this, RecommendedPlaces.class);
        intent.putExtra(LATITUDE, mLatitude);
        intent.putExtra(LONGITUDE, mLongitude);
        intent.putExtra(CITY, mCity);
        startActivity(intent);

    }


}
