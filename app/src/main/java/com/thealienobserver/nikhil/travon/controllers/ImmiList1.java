package com.thealienobserver.nikhil.travon.controllers;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thealienobserver.nikhil.travon.R;

public class ImmiList1 extends ImmNav1Screen  {
    // Array of strings...
    String[] fivethings = {"Get your Nova Scotia ID",
                           "Keep Your Study Permit always with you ",
                           "You can work offcampus maximum 20 hrs. a week ",
                           "Get your SIN no. as soon as possible",
                           "Get your phone no. as soon as possible"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_important_things);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, fivethings);

        ListView listView = (ListView) findViewById(R.id.fiveimportantthings);
        listView.setAdapter(adapter);
    }
}
