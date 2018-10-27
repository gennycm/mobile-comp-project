package com.thealienobserver.nikhil.travon.controllers;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.thealienobserver.nikhil.travon.R;

import java.util.Locale;

public class MenuScreen extends AppCompatActivity {
    private GoogleApiClient mGoogleApiClient;
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "longitude";
    private String mLatitude;
    private String mLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_screen);



        mLatitude = getIntent().getStringExtra(LATITUDE);
        mLatitude = getIntent().getStringExtra(LATITUDE);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }


    private void findPlaceById(String id) {
        if (TextUtils.isEmpty(id) || mGoogleApiClient == null || !mGoogleApiClient.isConnected())
            return;

        Places.GeoDataApi.getPlaceById(mGoogleApiClient, id).setResultCallback(new ResultCallback<PlaceBuffer>() {
            @Override
            public void onResult(PlaceBuffer places) {
                if (places.getStatus().isSuccess()) {
                    Place place = places.get(0);
                    Log.d("place", place.toString());
                }

                //Release the PlaceBuffer to prevent a memory leak
                places.release();
            }
        });
    }

    public void openRecommendedPlaces(View view) {
        Toast.makeText(getApplicationContext(), "This is my Toast message!", Toast.LENGTH_LONG).show();

        //  Intent recommendedPlacesIntent = new Intent(MenuScreen.this, RecommendedPlacesActivity.class);
      //  startActivity(recommendedPlacesIntent);

    }

}
