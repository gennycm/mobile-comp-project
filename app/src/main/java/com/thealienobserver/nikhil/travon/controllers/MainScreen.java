package com.thealienobserver.nikhil.travon.controllers;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.thealienobserver.nikhil.travon.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainScreen extends AppCompatActivity {

    private Marker locationMarker;
    private GoogleMap mapInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        this.setupMap();
        this.setupPlacesSearch();
        locationMarker = null;
    }

    private void setupMap() {
        SupportMapFragment userMapLocation = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        userMapLocation.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                mapInstance = googleMap;

                // Setup marker when user long clicks on the map
                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {
                        updateUserSelectedLocation(latLng);
                    }
                });

                // Ready map with the marker on user's initial location
                MainScreen.this.setMapToUserLocation();
            }
        });
    }

    private void setupPlacesSearch() {
        SupportPlaceAutocompleteFragment autocompleteFragment = (SupportPlaceAutocompleteFragment) getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                LatLng searchedPlace = place.getLatLng();
                updateUserSelectedLocation(searchedPlace);
                moveMapToPlace(searchedPlace);
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getApplication().getApplicationContext(), "Unable to find place.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void updateUserSelectedLocation(LatLng location) {
        if (locationMarker == null) {
            locationMarker = mapInstance.addMarker(new MarkerOptions().position(location));
        } else {
            locationMarker.setPosition(location);
        }
        Geocoder geocoder = new Geocoder(MainScreen.this, Locale.getDefault());
        try {
            TextView placeName = findViewById(R.id.placeName);
            List<Address> adresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);

            String locality = adresses.get(0).getLocality();
            Log.d("location", adresses.toString());
            RelativeLayout optionsDialog = findViewById(R.id.optionsDialog);
            if (locality != null && locality.length() > 0) {
                placeName.setText(locality);
                optionsDialog.setVisibility(View.VISIBLE);
                locationMarker.setTitle(locality);
            } else {
                optionsDialog.setVisibility(View.GONE);
                Toast.makeText(this, "Unable to find data on that location.", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveMapToPlace(LatLng place) {
        mapInstance.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 10));
    }

    private void setMapToUserLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 10);
        }
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    updateUserSelectedLocation(userLocation);
                    moveMapToPlace(userLocation);
                }
            }
        });
    }

    public void navigate(View view) {
        Button clickedButton = (Button) view;
        TextView placeName = findViewById(R.id.placeName);


        if (clickedButton.getText().toString().toUpperCase().equals("NEWS")) {
            LatLng currentLocation = this.locationMarker.getPosition();
            Geocoder geocoder = new Geocoder(MainScreen.this, Locale.getDefault());
            String country = null;
            try {
                List<Address> adresses = geocoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1);
                country = adresses.get(0).getCountryCode();
                Log.d("Country Code", country);
            } catch (IOException e) {

            }


            Intent newsIntent = new Intent(this, NewsScreen.class);
            newsIntent.putExtra(NewsScreen.COUNTRY_CODE_PARAM, country);
            newsIntent.putExtra(NewsScreen.LAT_LON_PARAM, currentLocation);
            startActivity(newsIntent);
        }
        else if(clickedButton.getText().toString().toUpperCase().equals("WEATHER")){
            LatLng currentLocation = this.locationMarker.getPosition();
            Geocoder geocoder = new Geocoder(MainScreen.this, Locale.getDefault());
            String country = null;
            try {
                List<Address> adresses = geocoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1);
                country = adresses.get(0).getCountryCode();
                Log.d("Country Code", country);
            } catch (IOException e) {

            }
            Intent weatherIntent = new Intent(this, WeatherScreen.class);
            weatherIntent.putExtra(WeatherScreen.COUNTRY_CODE_PARAM, country);
            weatherIntent.putExtra(WeatherScreen.LAT_LON_PARAM, currentLocation);
            weatherIntent.putExtra(WeatherScreen.LATITUDE, currentLocation.latitude);
            weatherIntent.putExtra(WeatherScreen.LONGITUDE, currentLocation.longitude);
            startActivity(weatherIntent);
        }
    }

    public void openMainMenu(View view) {
        LatLng currentLocation = this.locationMarker.getPosition();
        Intent menuIntent = new Intent(this, MenuScreen.class);
        menuIntent.putExtra(MenuScreen.LATITUDE, currentLocation.latitude);
        menuIntent.putExtra(MenuScreen.LONGITUDE, currentLocation.longitude);
        startActivity(menuIntent);

    }
}
