package com.thealienobserver.nikhil.travon.apihandlers;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

public abstract class EventsHandler {

    private Context applicationContext;

    // start of request url, need to append latitude, longitude, distance away, search query, page, and free/not.
    private static final String EVENT_START_URL = "https://www.eventbriteapi.com/v3/events/search/?sort_by=date&expand=venue&token=75YLW2PESU67Z3C2R7AM";

    public EventsHandler(Context context) { this.applicationContext = context; }

    public void getEventList(LatLng location, float distance, boolean freeonly, String searchQuery, int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(applicationContext);
        String url = EVENT_START_URL + "&location.latitude=" + location.latitude
    }
}
