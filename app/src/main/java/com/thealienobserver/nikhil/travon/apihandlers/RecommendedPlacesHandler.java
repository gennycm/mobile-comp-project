package com.thealienobserver.nikhil.travon.apihandlers;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thealienobserver.nikhil.travon.models.RecommendedPlace;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class RecommendedPlacesHandler {

    private Context applicationContext;

    private ArrayList<RecommendedPlace> recomendedPlaces;


    private static final String RecomendedPlaces_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=attractions+in+";

    private static final String RecommendedPLaces_Photo_Url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";

    private String api_key = "&key=AIzaSyDCywJBYgafoLew81-vpeGTN03_2vBB7jk";

    private static final String  Recommended_place_desc_url="https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles=";

    private RequestQueue requestQueue;

    public RecommendedPlacesHandler(Context context) {
        this.applicationContext = context;
    }


    public void getTopRecomendedPlaces(final String location) {
        requestQueue = Volley.newRequestQueue(applicationContext);
        String url = RecomendedPlaces_URL + location +
                "&sensor=false" + api_key;

        recomendedPlaces= new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Places Handler", response.toString());
                try {
                    // Generate places article list from the api response
                    JSONArray recommendedPlaces = response.getJSONArray("results");

                    for (int i = 0; i < recommendedPlaces.length(); i++) {
                        JSONObject recommendedPlacesJSONObject = recommendedPlaces.getJSONObject(i);
                        String name = recommendedPlacesJSONObject.getString("name");

                        JSONArray Photos = recommendedPlacesJSONObject.getJSONArray("photos");
                        for (int j = 0; j< Photos.length(); j++) {

                            String reference = RecommendedPLaces_Photo_Url + Photos.getJSONObject(j).getString("photo_reference")+"&sensor=false" + api_key;
                            Log.d("Places photo", reference);

                            getTopRecomendedDescription(name, reference, location);


                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.d("Place Handler", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void getTopRecomendedDescription(final String place,final String reference, final String city) {

        String url = Recommended_place_desc_url + place ;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Places Handler", response.toString());
                try {
                    JSONObject query = response.getJSONObject("query");
                    JSONObject pages = query.getJSONObject("pages");
                    Iterator page= pages.keys();
                    JSONObject jsonObject_Page= pages.getJSONObject(page.next().toString());
                    String description= jsonObject_Page.getString("extract");

                    RecommendedPlace recommededPlace = new RecommendedPlace();
                    recommededPlace.setName(place);
                    recommededPlace.setImage_ref(reference);
                    recommededPlace.setDescription(description);
                    recomendedPlaces.add(recommededPlace);



                    RecommendedPlacesHandler.this.postFetchingRecomendedPlaces(recomendedPlaces);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.d("Place Handler", error.toString());
            }
        });
        jsonObjectRequest.setTag(place);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                600000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonObjectRequest);
    }





    public abstract void postFetchingRecomendedPlaces(ArrayList<RecommendedPlace> recommendedPlaceArrayList);
}