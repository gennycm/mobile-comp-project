package com.thealienobserver.nikhil.travon.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.apihandlers.ImmigrationHandler;

public class ImmigrationActivity extends AppCompatActivity {

    public static String CITY = "city";
    public String city;
    private CardView cardView;
    private TextView categoryTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immigration_menu_screen);

        String city = getIntent().getStringExtra(CITY);
        setTitle("Immigration");

        ImmigrationHandler.getInstance(getApplicationContext()).getImmigrationInformation();
    }
    public void impThingstodoOnClick(View view) {
        cardView = findViewById(R.id.impThingsCv);
        categoryTitle = findViewById(R.id.impThingsTv);
        immigrationMenuItemOnClick(view, cardView, categoryTitle);
    }

    public void formsOnClick(View view) {
        cardView = findViewById(R.id.formsCv);
        categoryTitle = findViewById(R.id.formsTv);
        immigrationMenuItemOnClick(view, cardView, categoryTitle);
    }

    public void officesOnClick(View view) {
        cardView = findViewById(R.id.officesCv);
        categoryTitle = findViewById(R.id.officesTv);
        immigrationMenuItemOnClick(view, cardView, categoryTitle);
    }
    public void faqsOnClick(View view) {
        cardView = findViewById(R.id.faqCv);
        categoryTitle = findViewById(R.id.faqTv);
        immigrationMenuItemOnClick(view, cardView, categoryTitle);
    }

    public void immigrationMenuItemOnClick(View view, CardView cardView, TextView categoryTitle) {
        Intent intent = new Intent(ImmigrationActivity.this, ImmigrationDetailActivity.class);
        int bgColor = cardView.getCardBackgroundColor().getDefaultColor();
        String categoryTitleStr = (String) categoryTitle.getText();


        intent.putExtra(ImmigrationDetailActivity.BG_COLOR, bgColor);
        intent.putExtra(ImmigrationDetailActivity.CATEGORY_TITLE, categoryTitleStr);

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

