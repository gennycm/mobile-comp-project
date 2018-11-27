package com.thealienobserver.nikhil.travon.apihandlers;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thealienobserver.nikhil.travon.models.RecommendedPlace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public abstract class RecommendedPlacesHandler {

    private Context applicationContext;

    private ArrayList<RecommendedPlace> recomendedPlaces;


    private static final String RecomendedPlaces_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";

    private static final String RecommendedPLaces_Photo_Url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";

    private String api_key = "&key=AIzaSyDCywJBYgafoLew81-vpeGTN03_2vBB7jk";

    private static final String Recommended_Place_Details_Url = "https://maps.googleapis.com/maps/api/place/details/json?placeid=";

    private static final String Recommended_place_desc_url = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles=";

    private RequestQueue requestQueue;
    private String reference;

    private final HashMap<String, JSONObject> recommendedPlacesJsonObjectList = new HashMap<>();

    public RecommendedPlacesHandler(Context context) {
        this.applicationContext = context;
    }


    public void getTopRecomendedPlaces(final String location, final String placeType) {
        requestQueue = Volley.newRequestQueue(applicationContext);
        String url = RecomendedPlaces_URL + placeType.toLowerCase() + "%20in%20" + location +
                "&sensor=false" + api_key;
        url = url.replaceAll(" ", "%20");

        recomendedPlaces = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Places Handler", response.toString());
                try {
                    // Generate places article list from the api response
                    JSONArray recommendedPlaces = response.getJSONArray("results");

                    for (int i = 0; i < recommendedPlaces.length(); i++) {
                        final JSONObject recommendedPlacesJSONObject = recommendedPlaces.getJSONObject(i);


                        String url2 = Recommended_Place_Details_Url + recommendedPlacesJSONObject.getString("place_id") + "&fields=name,reference,international_phone_number,formatted_address" + api_key;
                        url2 = url2.replaceAll(" ", "%20");
                        recommendedPlacesJsonObjectList.put(recommendedPlacesJSONObject.getString("place_id"), recommendedPlacesJSONObject);

                        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Places Handler", response.toString());
                                try {

                                    String formatted_address = "";
                                    String formatted_phone_number = "";
                                    String name = "";

                                    if (response.has("result")) {
                                        if (response.getString("status").equalsIgnoreCase("ok")) {


                                            JSONObject result = response.getJSONObject("result");

                                            if (result.has("formatted_address")) {
                                                formatted_address = result.getString("formatted_address");

                                            }
                                            if (result.has("international_phone_number")) {
                                                formatted_phone_number = result.getString("international_phone_number");
                                            } else {
                                                formatted_phone_number = "Not Available";
                                            }
                                            if (result.has("name")) {
                                                name = result.getString("name");
                                            }
                                            if (result.has("reference")) {
                                                reference = result.getString("reference");
                                            }

                                            JSONArray Photos = recommendedPlacesJsonObjectList.get(reference).getJSONArray("photos");

                                            for (int j = 0; j < Photos.length(); j++) {
                                                reference = RecommendedPLaces_Photo_Url + Photos.getJSONObject(j).getString("photo_reference") + "&sensor=false" + api_key;
                                                Log.d("Places photo", reference);

                                            }


                                            getTopRecomendedDescription(name, reference, location, placeType, formatted_address, formatted_phone_number);
                                        }

                                    }


                                } catch (JSONException e) {
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


    public void getTopRecomendedDescription(final String place, final String reference, final String city, final String placeType, final String formatted_address, final String formatted_phone_number) {

        String url = Recommended_place_desc_url + place;

        url = url.replaceAll(" ", "%20");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Places Handler", response.toString());
                try {
                    JSONObject query = response.getJSONObject("query");
                    JSONObject pages = query.getJSONObject("pages");
                    Iterator page = pages.keys();
                    JSONObject jsonObject_Page = pages.getJSONObject(page.next().toString());
                    String description = "";

                    RecommendedPlace recommededPlace = new RecommendedPlace();
                    recommededPlace.setName(place);
                    recommededPlace.setImage_ref(reference);

                    recommededPlace.setFormattedAddress(formatted_address);
                    recommededPlace.setFormattedPhoneNumber(formatted_phone_number);
                    if (jsonObject_Page.has("extract")) {
                        description = jsonObject_Page.getString("extract") != null ? jsonObject_Page.getString("extract") : "";

                        recommededPlace.setDescription(description);

                    } else {
                        switch (placeType) {
                            case "Attractions":
                                description = place + " is a Tourist Attraction in " + city;
                                break;
                            case "Hospitals":
                                description = place + " is a Hospital in " + city;
                                break;
                            case "Universities":
                                description = place + " is a University in " + city;
                                break;
                            case "Restaurants":
                                description = place + " is a  Restaurant in " + city;
                                break;
                        }

                        recommededPlace.setDescription(description);

                    }
                    recomendedPlaces.add(recommededPlace);

                    RecommendedPlacesHandler.this.postFetchingRecomendedPlaces(recomendedPlaces);
                    recommededPlace.setDescription(description);

                    recomendedPlaces.add(recommededPlace);
                    postFetchingRecomendedPlaces(recomendedPlaces);
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
