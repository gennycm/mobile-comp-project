package com.thealienobserver.nikhil.travon.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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
    private int page = 1;
    private int distance = 100;
    private boolean freeOnly = false;
    private String searchQuery = "";
    private EventsHandler eventsHandler;
    private LatLng currentlocation;
    private Button advanced, prev, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_screen);

        // Gather event details
        currentlocation = (LatLng) getIntent().getExtras().get(LAT_LON_PARAM);

        advanced = findViewById(R.id.advanced);
        prev = findViewById(R.id.prevPage);
        next = findViewById(R.id.nextPage);

        eventsHandler = new EventsHandler(this) {
            @Override
            public void eventGatherFinish(ArrayList<Event> eventList, boolean newPage) {
                EventsScreen.this.setupEvents(eventList);
                next.setEnabled(newPage);
                if (page > 1)
                    prev.setEnabled(true);
                else
                    prev.setEnabled(false);
            }
        };
        // TODO: Handle options such as distance and search, as well as multiple pages.
        eventsHandler.getEventList(currentlocation, distance, freeOnly, searchQuery, page);

        prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: Clicking the button multiple times goes multiple pages, potentially past limit!
                page--;
                eventsHandler.getEventList(currentlocation, distance, freeOnly, searchQuery, page);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                page++;
                eventsHandler.getEventList(currentlocation, distance, freeOnly, searchQuery, page);
            }
        });
    }

    private void setupEvents(ArrayList<Event> events) {

        RecyclerView eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setAdapter(new EventsAdapter(this, events));
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //eventsRecyclerView.setAdapter();
    }
}
