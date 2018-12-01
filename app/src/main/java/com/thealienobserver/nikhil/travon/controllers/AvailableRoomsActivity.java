package com.thealienobserver.nikhil.travon.controllers;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.adapters.AvailableRoomsAdapter;
import com.thealienobserver.nikhil.travon.apihandlers.AvailableRoomHandler;
import com.thealienobserver.nikhil.travon.models.AvailableRoomsModel;

import java.util.ArrayList;

public class AvailableRoomsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availablerooms_screen);
        final String urlWithBase = "https://mc-project.herokuapp.com/rooms?city=halifax";

        AvailableRoomHandler newsHandler = new AvailableRoomHandler(this) {
            @Override
            public void postFetchingAvailableRooms(ArrayList<AvailableRoomsModel> availableRooms) {
                AvailableRoomsActivity.this.setupRoomsCards(availableRooms);
            }
        };
        newsHandler.getAvailableRooms("");
    }

    private void setupRoomsCards(ArrayList<AvailableRoomsModel> newsArticles) {
        RecyclerView roomsRecyclerView = findViewById(R.id.availableRoomsRecyclerView);
        roomsRecyclerView.setAdapter(new AvailableRoomsAdapter(this, newsArticles));
        roomsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    }
