
package com.thealienobserver.nikhil.travon.controllers;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.adapters.RecommendedPlacesAdapter;
import com.thealienobserver.nikhil.travon.apihandlers.RecommendedPlacesHandler;
import com.thealienobserver.nikhil.travon.models.RecommendedPlace;

import java.util.ArrayList;

import static com.thealienobserver.nikhil.travon.controllers.MenuScreen.LATITUDE;
import static com.thealienobserver.nikhil.travon.controllers.MenuScreen.LONGITUDE;


public class RecommendedPlaces extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback {

    private String mLatLong;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomended_places_screen);

        mLatLong = getIntent().getStringExtra(LATITUDE) + "," + getIntent().getStringExtra(LONGITUDE);
        city = getIntent().getStringExtra(MenuScreen.CITY);

        //fetching places
        RecommendedPlacesHandler recommendedPlacesHandler = new RecommendedPlacesHandler(this) {
            @Override
            public void postFetchingRecomendedPlaces(ArrayList<RecommendedPlace> recomendedPlaces) {
                RecommendedPlaces.this.setupRecomendedPlaces(recomendedPlaces);

            }


        };
        recommendedPlacesHandler.getTopRecomendedPlaces(city);
    }

    private void setupRecomendedPlaces(ArrayList<RecommendedPlace> recomendedPlacesArrayList) {

        RecyclerView newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setAdapter(new RecommendedPlacesAdapter(this, recomendedPlacesArrayList));
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


}
