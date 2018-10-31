package com.thealienobserver.nikhil.travon.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.thealienobserver.nikhil.travon.R;

/**
 * Created by Charley LeBlanc
 */

public class EventsScreen extends AppCompatActivity {

    public static final String LAT_LON_PARAM = "LAT_LON_PARAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_screen);

        // Gather event details
        LatLng currentlocation = (LatLng) getIntent().getExtras().get(LAT_LON_PARAM);
        /*
        EventsHandler eventsHandler = new EventsHandler(this) {
            @Override
            public void eventGatherFinish(ArrayList<>)
        }
        */
    }
}
