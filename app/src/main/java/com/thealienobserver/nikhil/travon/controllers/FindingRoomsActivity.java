package com.thealienobserver.nikhil.travon.controllers;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.adapters.FindingRoomsAdapter;
import com.thealienobserver.nikhil.travon.apihandlers.FindingRoomsHandler;
import com.thealienobserver.nikhil.travon.models.Room;

import java.util.ArrayList;

public class FindingRoomsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finding_rooms);
        final String urlWithBase = "https://mc-project.herokuapp.com/rooms?city=halifax";

        FindingRoomsHandler newsHandler = new FindingRoomsHandler(this) {
            @Override
            public void postFetchingAvailableRooms(ArrayList<Room> availableRooms) {
                FindingRoomsActivity.this.setupRoomsCards(availableRooms);
            }
        };
        newsHandler.getAvailableRooms("");
    }

    private void setupRoomsCards(ArrayList<Room> newsArticles) {
        RecyclerView roomsRecyclerView = findViewById(R.id.availableRoomsRecyclerView);
        roomsRecyclerView.setAdapter(new FindingRoomsAdapter(this, newsArticles));
        roomsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    }
