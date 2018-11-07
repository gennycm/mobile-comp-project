package com.thealienobserver.nikhil.travon.controllers;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thealienobserver.nikhil.travon.R;

public class ImmiList3 extends ImmNav1Screen  {
    // Array of strings...
    String[] Offices = {"Driving License Office",
            "Passport Office",
            "Halifax Transport Office",
            "CIBC Office"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immig3_screen);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, Offices);

        ListView listView = (ListView) findViewById(R.id.Offices);
        listView.setAdapter(adapter);
    }
}
