package com.thealienobserver.nikhil.travon.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.adapters.EventsAdapter;
import com.thealienobserver.nikhil.travon.apihandlers.EventsHandler;
import com.thealienobserver.nikhil.travon.models.Event;

import java.util.ArrayList;

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

        EventsHandler eventsHandler = new EventsHandler(this) {
            @Override
            public void eventGatherFinish(ArrayList<Event> eventList) {
                EventsScreen.this.setupEvents(eventList);
            }
        };
        // TODO: Handle options such as distance and search, as well as multiple pages.
        eventsHandler.getEventList(currentlocation, 100.0f, false, "", 1);
    }

    private void setupEvents(ArrayList<Event> events) {

        RecyclerView eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setAdapter(new EventsAdapter(this, events));
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //eventsRecyclerView.setAdapter();
    }
}
