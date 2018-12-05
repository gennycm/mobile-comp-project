package com.thealienobserver.nikhil.travon.apihandlers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainMenuHandler {

    private Context applicationContext;
    private ImageView cityImageView;
    private static String api_key = "&key=AIzaSyDLrW7zOAD6qRghpih8gnxa0xIABryLyOw";
    private RequestQueue requestQueue;

    public MainMenuHandler(Context context, ImageView cityImageView) {
        this.applicationContext = context;
        this.cityImageView = cityImageView;
    }

    /**
     * Gets the id related to the selected city according to its latitude and longitude/
     * @param longitude
     * @param latitude
     */

    public void getPlaceID(String longitude, String latitude) {
        requestQueue = Volley.newRequestQueue(applicationContext);
        String getPlaceIDURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&radius=500" + api_key;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getPlaceIDURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    JSONObject cityJSON = results.getJSONObject(0);
                    String placeID = cityJSON.getString("id");
                    JSONArray photoArray = cityJSON.getJSONArray("photos");
                    String photoReference = photoArray.getJSONObject(0).getString("photo_reference");
                    Log.d("photoReference", photoReference);
                    getPlacePhoto(photoReference);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(applicationContext, "There was an error. Please try again later.", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.d("Results", error.toString());
                Toast.makeText(applicationContext, "There was an error. Please try again later.", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * Get the photo for the selected city
     * @param photoReference
     */

    public void getPlacePhoto(String photoReference) {
        requestQueue = Volley.newRequestQueue(applicationContext);
        String getPlacePhotoURL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoReference + api_key;

        ImageRequest imageRequest = new ImageRequest(getPlacePhotoURL, new Response.Listener<Bitmap>() {

            @Override
            public void onResponse(Bitmap response) {
                cityImageView.setImageBitmap(response);

            }
        }, 0, 0, null, null);

        requestQueue.add(imageRequest);



    }


}


