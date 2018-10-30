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
import android.widget.LinearLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.models.RecommendedPlace;

import java.util.Locale;

public class MenuScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static final String PlaceID = "PLACE_ID";
    public static final String CITY ="City" ;
    private GoogleApiClient mGoogleApiClient;
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "longitude";
    private String mLatitude;
    private String mLongitude;
    private String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_screen);

        mLatitude = String.valueOf(getIntent().getDoubleExtra(LATITUDE,0.0));
        mLongitude = String.valueOf(getIntent().getDoubleExtra(LONGITUDE,0.0));
        city= getIntent().getStringExtra(CITY);

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

                    for (Place place : places) {

                        Log.d("place", place.getPlaceTypes().toString());

                    }


                }

                //Release the PlaceBuffer to prevent a memory leak
                places.release();
            }
        });
    }


    public void openRecommendedplace(View view) {

        Intent intent = new Intent(MenuScreen.this, RecommendedPlaces.class);
        intent.putExtra(LATITUDE, mLatitude);
        intent.putExtra(LONGITUDE, mLongitude);
        intent.putExtra(CITY,city);
        startActivity(intent);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
