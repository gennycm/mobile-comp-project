package com.thealienobserver.nikhil.travon;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainScreen extends AppCompatActivity {

    private Marker locationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        this.setupMap();
        locationMarker = null;
    }

    private void setupMap() {
        SupportMapFragment userMapLocation = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        userMapLocation.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

                // Setup marker when user long clicks on the map
                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {
                        if(locationMarker == null) {
                            locationMarker = googleMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
                        } else {
                            locationMarker.setPosition(latLng);
                        }
                        Geocoder geocoder = new Geocoder(MainScreen.this, Locale.getDefault());
                        try {
                            TextView placeName = findViewById(R.id.placeName);
                            List<Address> adresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                            String locality = adresses.get(0).getLocality();
                            RelativeLayout optionsDialog = findViewById(R.id.optionsDialog);
                            if(locality.length() > 0) {
                                placeName.setText(adresses.get(0).getLocality());
                                optionsDialog.setVisibility(View.VISIBLE);
                            } else {
                                optionsDialog.setVisibility(View.GONE);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                // Ready map with the marker on user's initial location
                MainScreen.this.readyMap(googleMap);
            }
        });
    }

    private void readyMap(final GoogleMap googleMap) {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION  }, 10 );
        }
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    if(locationMarker == null) {
                        locationMarker = googleMap.addMarker(new MarkerOptions().position(userLocation).title("Location"));
                    } else {
                        locationMarker.setPosition(userLocation);
                    }

                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
                }
            }
        });
    }
}
