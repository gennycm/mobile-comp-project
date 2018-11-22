package com.thealienobserver.nikhil.travon.controllers.CostOfLiving;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.apihandlers.CostOfLivingHandler;

public class CostOfLivingScreen extends AppCompatActivity {
    public static final String CITY = "CITY";
    public static final String COUNTRY = "COUNTRY";

    private String city;
    private String country;
    private CardView cardView;
    private TextView categoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_living_menu_screen);

        city = getIntent().getStringExtra(CITY);
        country = getIntent().getStringExtra(COUNTRY);

        CostOfLivingHandler.getInstance(getApplicationContext()).getCostOfLivingFromCity(city, country);
    }

    public void openTransportDetails(View view) {
        Intent transportIntent = new Intent(CostOfLivingScreen.this, CostOfLivingDetailsScreen.class);
        cardView = findViewById(R.id.transportCv);
        categoryTitle = findViewById(R.id.transportTitle);
        int bgColor = cardView.getCardBackgroundColor().getDefaultColor();
        String categoryTitleStr = (String) categoryTitle.getText();

        transportIntent.putExtra(CostOfLivingDetailsScreen.CITY, city);
        transportIntent.putExtra(CostOfLivingDetailsScreen.BG_COLOR, bgColor);
        transportIntent.putExtra(CostOfLivingDetailsScreen.CATEGORY_TITLE, categoryTitleStr);

        startActivity(transportIntent);
    }

    public void openFoodDetails(View view) {
        Intent transportIntent = new Intent(CostOfLivingScreen.this, CostOfLivingDetailsScreen.class);
        cardView = findViewById(R.id.foodCv);
        categoryTitle = findViewById(R.id.foodTv);
        int bgColor = cardView.getCardBackgroundColor().getDefaultColor();
        String categoryTitleStr = (String) categoryTitle.getText();

        transportIntent.putExtra(CostOfLivingDetailsScreen.CITY, city);
        transportIntent.putExtra(CostOfLivingDetailsScreen.BG_COLOR, bgColor);
        transportIntent.putExtra(CostOfLivingDetailsScreen.CATEGORY_TITLE, categoryTitleStr);

        startActivity(transportIntent);
    }

    public void openUtilitiesDetails(View view) {
        Intent utilitiesTransport = new Intent(CostOfLivingScreen.this, CostOfLivingDetailsScreen.class);
        cardView = findViewById(R.id.utilitiesCv);
        categoryTitle = findViewById(R.id.utilitiesTv);
        int bgColor = cardView.getCardBackgroundColor().getDefaultColor();
        String categoryTitleStr = (String) categoryTitle.getText();

        utilitiesTransport.putExtra(CostOfLivingDetailsScreen.CITY, city);
        utilitiesTransport.putExtra(CostOfLivingDetailsScreen.BG_COLOR, bgColor);
        utilitiesTransport.putExtra(CostOfLivingDetailsScreen.CATEGORY_TITLE, categoryTitleStr);

        startActivity(utilitiesTransport);
    }

    public void openRoomsDetails(View view) {
        Intent roomsIntent = new Intent(CostOfLivingScreen.this, CostOfLivingDetailsScreen.class);
        cardView = findViewById(R.id.roomCv);
        categoryTitle = findViewById(R.id.roomTv);
        int bgColor = cardView.getCardBackgroundColor().getDefaultColor();
        String categoryTitleStr = (String) categoryTitle.getText();

        roomsIntent.putExtra(CostOfLivingDetailsScreen.CITY, city);
        roomsIntent.putExtra(CostOfLivingDetailsScreen.BG_COLOR, bgColor);
        roomsIntent.putExtra(CostOfLivingDetailsScreen.CATEGORY_TITLE, categoryTitleStr);

        startActivity(roomsIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
