package com.thealienobserver.nikhil.travon.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thealienobserver.nikhil.travon.R;

public class Immigrationfaqs extends AppCompatActivity {
    //public static String CITY = "city";

    // Array of strings...
    String[] FAQs = {"1.What is the procedure to study in Canada",
            "The first step is to get admission to a Canadian Designated Learning Institution (DLI) recognized by the government of Canada. Once an individual receives a letter of acceptance from a DLI, he or she may be able to apply for a study permit. Applications may be made online or by mail.",
            "2.How much does it cost to study in Canada",
             "Tuition fees vary based on institution. Please contact the administration where you intend to study for more information.",
             "3.What is biometric identification and do I need it for study permit",
            "Canada requires citizens of certain countries to have their biometrics (fingerprints and photographs) provided.  For a complete list of countries that require biometric identification"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immigrationfaqs_screen);

        //String cityName = getIntent().getStringExtra(CITY);
        //setTitle(cityName + " Immigration");


        //Showing list of FAQs
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, FAQs);

        ListView listView = (ListView) findViewById(R.id.FAQs);
        listView.setAdapter(adapter);
    }
}