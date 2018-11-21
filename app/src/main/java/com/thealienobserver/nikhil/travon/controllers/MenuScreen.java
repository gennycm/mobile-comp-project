package com.thealienobserver.nikhil.travon.controllers;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.apihandlers.MainMenuHandler;
import com.thealienobserver.nikhil.travon.controllers.RecommendedPlaces.RecommendedPlacesActivity;

import java.util.ArrayList;

public class MenuScreen extends AppCompatActivity {
//    public static final String PlaceID = "PLACE_ID";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String CITY = "CITY";

    public  static final String ADDRESSES = "ADDRESSES";
    private TextView cityTextView;
    private ImageView cityImageview;

    private ArrayList<Address> addresses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_screen);

        cityTextView = findViewById(R.id.cityTextView);
        cityImageview = findViewById(R.id.cityImageView);

        addresses = getIntent().getParcelableArrayListExtra(ADDRESSES);
        cityTextView.setText(addresses.get(0).getLocality());

        String latitude = String.valueOf(addresses.get(0).getLatitude());
        String longitude = String.valueOf(addresses.get(0).getLongitude());
        MainMenuHandler mainMenuHandler = new MainMenuHandler(this, cityImageview);
        mainMenuHandler.getPlaceID(longitude, latitude);
    }


    public void openRecommendedplace(View view) {
        String latitude = String.valueOf(addresses.get(0).getLatitude());
        String longitude = String.valueOf(addresses.get(0).getLongitude());
        String city = addresses.get(0).getLocality();
        city = (city == null)? addresses.get(0).getAdminArea(): city;

        Intent intent = new Intent(MenuScreen.this, RecommendedPlacesActivity.class);
        intent.putExtra(LATITUDE, latitude);
        intent.putExtra(LONGITUDE, longitude);
        intent.putExtra(CITY, city);
        startActivity(intent);

    }
    public void openImmigration(View view) {
        Intent immigrationInfoIntent = new Intent(MenuScreen.this, ImmNav1Screen.class);
        startActivity(immigrationInfoIntent);
    }

    public void openEvents(View view) {
        LatLng currentLocation = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());

        Intent eventIntent = new Intent(this, EventsScreen.class);
        eventIntent.putExtra(EventsScreen.LAT_LON_PARAM, currentLocation);
        startActivity(eventIntent);
    }

    public void comingSoon(View view) {
        Toast.makeText(this, "Coming Soon!!!", Toast.LENGTH_SHORT).show();
    }

    public void openNews(View view) {
        String city = addresses.get(0).getLocality();
        city = (city == null)? addresses.get(0).getAdminArea(): city;
        Intent newsIntent = new Intent(MenuScreen.this, NewsScreen.class);
        newsIntent.putExtra(NewsScreen.COUNTRY_PARAM, addresses.get(0).getCountryName());
        newsIntent.putExtra(NewsScreen.CITY_PARAM, city);
        startActivity(newsIntent);
    }

    public void openWeather(View view) {
        LatLng currentLocation = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
        String country = addresses.get(0).getCountryCode();
        Intent weatherIntent = new Intent(this, WeatherScreen.class);
        weatherIntent.putExtra(WeatherScreen.COUNTRY_CODE_PARAM, country);
        weatherIntent.putExtra(WeatherScreen.LAT_LON_PARAM, currentLocation);
        weatherIntent.putExtra(WeatherScreen.LATITUDE, currentLocation.latitude);
        weatherIntent.putExtra(WeatherScreen.LONGITUDE, currentLocation.longitude);
        startActivity(weatherIntent);
    }
}
