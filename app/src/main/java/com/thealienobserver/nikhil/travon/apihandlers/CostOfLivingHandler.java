package com.thealienobserver.nikhil.travon.apihandlers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thealienobserver.nikhil.travon.models.CostOfLivingItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CostOfLivingHandler {
    private static CostOfLivingHandler mInstance;
    private Context applicationContext;
    private static String api_key = "api_key=rkouvmmc5fm0zj";
    private RequestQueue requestQueue;


    private ArrayList<CostOfLivingItem> food = new ArrayList();
    private ArrayList<CostOfLivingItem> transportation = new ArrayList();
    private ArrayList<CostOfLivingItem> utilities = new ArrayList();
    private ArrayList<CostOfLivingItem> room = new ArrayList();
    private ArrayList<CostOfLivingItem> clothing = new ArrayList();
    private ArrayList<CostOfLivingItem> childcare = new ArrayList();

    //https://www.numbeo.com/api/city_prices?api_key=rkouvmmc5fm0zj&query=Halifax%20Canada

    public CostOfLivingHandler(Context context) {
        this.applicationContext = context;
    }

    public static synchronized CostOfLivingHandler getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new CostOfLivingHandler(context.getApplicationContext());
        }
        return mInstance;
    }

    public void getCostOfLivingFromCity(String city, String country) {
        requestQueue = Volley.newRequestQueue(applicationContext);
        String getCostOfLivingURL = "https://www.numbeo.com/api/city_prices?" + api_key + "&query=" + city + "%20" + country;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getCostOfLivingURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray prices = response.getJSONArray("prices");
                    classifyResults(prices);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.d("Results", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

    }


    public void classifyResults(JSONArray prices) throws JSONException {
        for (int i = 0; i < prices.length(); i++) {
            JSONObject itemJSON = prices.getJSONObject(i);

            String item_name = itemJSON.getString("item_name");
            double lowest_price = itemJSON.getDouble("lowest_price");
            double average_price = itemJSON.getDouble("average_price");
            double highest_price = itemJSON.getDouble("highest_price");
            if (item_name.contains("Restaurants") || item_name.contains("Market")) {
                food.add(new CostOfLivingItem(item_name, lowest_price, average_price, highest_price));
            } else {
                if (item_name.contains("Transportation")) {
                    transportation.add(new CostOfLivingItem(item_name, lowest_price, average_price, highest_price));
                } else {
                    if (item_name.contains("Utilities")) {
                        utilities.add(new CostOfLivingItem(item_name, lowest_price, average_price, highest_price));
                    } else {
                        if (item_name.contains("Rent Per Month") || item_name.contains("Buy Apartment Price")) {
                            room.add(new CostOfLivingItem(item_name, lowest_price, average_price, highest_price));
                        } else {
                            if (item_name.contains("Clothing And Shoes")) {
                                clothing.add(new CostOfLivingItem(item_name, lowest_price, average_price, highest_price));
                            } else {
                                if (item_name.contains("Childcare")) {
                                    childcare.add(new CostOfLivingItem(item_name, lowest_price, average_price, highest_price));
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public ArrayList getFood() {
        return food;
    }

    public ArrayList getTransportation() {
        return transportation;
    }

    public ArrayList<CostOfLivingItem> getUtilities() {
        return utilities;
    }

    public ArrayList<CostOfLivingItem> getRoom() {
        return room;
    }

    public ArrayList<CostOfLivingItem> getClothing() {
        return clothing;
    }

    public ArrayList<CostOfLivingItem> getChildcare() {
        return childcare;
    }
}
