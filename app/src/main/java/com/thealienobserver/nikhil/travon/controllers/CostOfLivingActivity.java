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

        setTitle("Cost of living in " + city);
        CostOfLivingHandler.getInstance(getApplicationContext()).getCostOfLivingFromCity(city, country);
    }

    public void transportOnClick(View view) {
        cardView = findViewById(R.id.transportCv);
        categoryTitle = findViewById(R.id.transportTitle);
        costOfLivingMenuItemOnClick(view, cardView, categoryTitle);
    }

    public void foodOnClick(View view) {
        cardView = findViewById(R.id.foodCv);
        categoryTitle = findViewById(R.id.foodTv);
        costOfLivingMenuItemOnClick(view, cardView, categoryTitle);
    }

    public void utilitiesOnClick(View view) {
        cardView = findViewById(R.id.utilitiesCv);
        categoryTitle = findViewById(R.id.utilitiesTv);
        costOfLivingMenuItemOnClick(view, cardView, categoryTitle);
    }

    public void roomsOnClick(View view) {
        cardView = findViewById(R.id.roomCv);
        categoryTitle = findViewById(R.id.roomTv);
        costOfLivingMenuItemOnClick(view, cardView, categoryTitle);
    }

    public void childcareOnClick(View view) {
        cardView = findViewById(R.id.childcareCv);
        categoryTitle = findViewById(R.id.childcareTv);
        costOfLivingMenuItemOnClick(view, cardView, categoryTitle);
    }

    public void clothingOnClick(View view) {
        cardView = findViewById(R.id.clothingCv);
        categoryTitle = findViewById(R.id.clothingTv);
        costOfLivingMenuItemOnClick(view, cardView, categoryTitle);
    }

    public void costOfLivingMenuItemOnClick(View view, CardView cardView, TextView categoryTitle) {
        Intent intent = new Intent(CostOfLivingScreen.this, CostOfLivingDetailsScreen.class);
        int bgColor = cardView.getCardBackgroundColor().getDefaultColor();
        String categoryTitleStr = (String) categoryTitle.getText();

        intent.putExtra(CostOfLivingDetailsScreen.CITY, city);
        intent.putExtra(CostOfLivingDetailsScreen.BG_COLOR, bgColor);
        intent.putExtra(CostOfLivingDetailsScreen.CATEGORY_TITLE, categoryTitleStr);

        startActivity(intent);
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
