package com.thealienobserver.nikhil.travon.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.thealienobserver.nikhil.travon.R;

public class Immigrationmainscreen extends AppCompatActivity {

    public static String CITY = "city";
    public String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immigration_screen);

        String city = getIntent().getStringExtra(CITY);
        setTitle(city + " Immigration");
        setTitle("Immigration");
    }

    public void onClickImm1(View v) {
        Intent myintent = new Intent(Immigrationmainscreen.this, Impthingstodo.class);
        myintent.putExtra(Immigrationmainscreen.CITY, city);
        startActivity(myintent);
    }

    public void onClickImm2(View v) {
        Intent myintent = new Intent(Immigrationmainscreen.this, Immigrationforms.class);
        myintent.putExtra(Immigrationmainscreen.CITY, city);
        startActivity(myintent);
    }

    public void onClickImm3(View v) {
        Intent myintent = new Intent(Immigrationmainscreen.this, Immigrationoffices.class);
        myintent.putExtra(Immigrationmainscreen.CITY, city);
        startActivity(myintent);
    }

    public void onClickImm4(View v) {
        Intent myintent = new Intent(Immigrationmainscreen.this, Immigrationfaqs.class);
        myintent.putExtra(Immigrationmainscreen.CITY, city);
        startActivity(myintent);
    }

}