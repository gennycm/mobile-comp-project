package com.thealienobserver.nikhil.travon.apihandlers;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.thealienobserver.nikhil.travon.models.WeatherModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public abstract class WeatherHandler {
    private static final String TAG = "WeatherHandler";
    private Context applicationContext;

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/find?APPID=eb866e903a87bc24b5178943f993718e&lat=";

    public WeatherHandler(Context context) {
        this.applicationContext = context;
    }

    public void getFiveDaysWeather(String latitude,String longitude) {
        RequestQueue requestQueue = Volley.newRequestQueue(applicationContext);
        Float lat=Float.parseFloat(latitude);
        Float lon=Float.parseFloat(longitude);
        latitude=String.format("%.2f", lat);
        longitude=String.format("%.2f", lon);
        String url = WEATHER_URL.concat(TextUtils.isEmpty(latitude) ? "44.649963&lon=-63.5802565" : (latitude + "&lon=" + longitude+"&cnt=5"));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Weather Handler", response.toString());
                try {
                    // Generate news article list from the api response
                    JSONArray weatherlistItem = response.getJSONArray("list");
                    ArrayList<WeatherModel> nextFiveDays = new ArrayList<>();
                    JSONObject weatherfirstjsonobj=new JSONObject();// To get elements of weatherlistItem
                    for(int weatherItr=0; weatherItr < weatherlistItem.length(); weatherItr++) {
                        JSONObject weatherItemLoop = weatherlistItem.getJSONObject(weatherItr);
                        String cityname = weatherItemLoop.getString("name");


                        JSONObject weathermainjsonobj=new JSONObject();
                        weathermainjsonobj=weatherItemLoop.getJSONObject("main");
                        double humidity=Double.parseDouble(String.valueOf(weathermainjsonobj.get("humidity")));

                        //Clouds
                        JSONObject cloudsJSON=new JSONObject();
                        cloudsJSON=weatherItemLoop.getJSONObject("clouds");
                        double clouds=Double.parseDouble(String.valueOf(cloudsJSON.get("all")));

                        Double temprature=Double.parseDouble(String.valueOf(weathermainjsonobj.get("temp")));
                        Double minimum_temprature=Double.parseDouble(String.valueOf(weathermainjsonobj.get("temp_min")));
                        Double maximum_temprature=Double.parseDouble(String.valueOf(weathermainjsonobj.get("temp_max")));

                        JSONArray weatherlistArray=new JSONArray();
                        weatherlistArray=weatherItemLoop.getJSONArray("weather");

                        JSONObject weatherlistFirstElement=new JSONObject();
                        weatherlistFirstElement=(JSONObject)weatherlistArray.get(0);

                        String main=String.valueOf(weatherlistFirstElement.get("main"));
                        String description=String.valueOf(weatherlistFirstElement.get("description"));


                        String imageUrl ="http://openweathermap.org/img/w/"+ weatherlistFirstElement.getString("icon")+".png";
                        Log.d("ImageUrl",imageUrl);

                        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                     //   Date newdate=new Date(weatherItemLoop.getString("dt"));
                      //  SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                        //        Locale.ENGLISH);
                       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                       // Date weatherdate =  sdf.parse(weatherItemLoop.getString("dt"));
                       /* Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
                        String strDate = "Current Date : " + mdformat.format(calendar.getTime());
                        //Date weatherdate=new Date(strDate);
                        Date weatherdate=new SimpleDateFormat("dd/MM/yyyy").parse(strDate);*/

                        Date weatherdate = new Date(Long.parseLong(weatherItemLoop.getString("dt")) * 1000);

                       //Date weatherdate=null;
                        WeatherModel article = new WeatherModel(cityname, temprature, humidity, minimum_temprature, maximum_temprature, main,description,imageUrl,weatherdate);
                        Log.d(TAG, "onResponse: city "+ cityname +"humidity"+humidity+"clouds"+clouds+"temprature"+temprature+"minimum_temprature"+minimum_temprature+"maximum_temprature"+maximum_temprature+"main"+main+"description"+description+"weatherdate"+weatherdate);
                        nextFiveDays.add(article);
                        Log.d("Reached here",""+article);
                        // Call the user's callback for post fetching news articles
                        WeatherHandler.this.postFetchingWeather(nextFiveDays);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.d("News Handler", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public abstract void  postFetchingWeather(ArrayList<WeatherModel> weatherModel);

}
