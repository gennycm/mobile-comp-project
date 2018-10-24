package com.thealienobserver.nikhil.travon.apihandlers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.thealienobserver.nikhil.travon.models.NewsArticle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public abstract class NewsHandler {
//    private static NewsHandler newsHandlerInstance;
    private Context applicationContext;

    private static final String NEWS_URL = "https://newsapi.org/v2/top-headlines?country=gb&apiKey=52a70f17e25a4aadb0e73d77d75667ea";

    public NewsHandler(Context context) {
        this.applicationContext = context;
    }

//
//    public static NewsHandler getInstance(Context context) {
//        if(newsHandlerInstance == null) {
//            newsHandlerInstance = new NewsHandler(context);
//        }
//        return newsHandlerInstance;
//    }

    public void getTopNewsHeadlines(String countryCode) {
        RequestQueue requestQueue = Volley.newRequestQueue(applicationContext);
        String url = NEWS_URL + "&country=" + countryCode;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("News Handler", response.toString());
                    try {
                        // Generate news article list from the api response
                        JSONArray newsArticles = response.getJSONArray("articles");
                        ArrayList<NewsArticle> topArticles = new ArrayList<>();
                        for(int newsItr=0; newsItr < newsArticles.length(); newsItr++) {
                            JSONObject newsArticle = newsArticles.getJSONObject(newsItr);
                            String title = newsArticle.getString("title");
                            String description = newsArticle.getString("description");
                            String articleUrl = newsArticle.getString("url");
                            String imageUrl = newsArticle.getString("urlToImage");
                            String content = newsArticle.getString("content");

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                            Date publishedAt =  sdf.parse(newsArticle.getString("publishedAt"));

                            NewsArticle article = new NewsArticle(title, description, imageUrl, articleUrl, content, publishedAt);
                            topArticles.add(article);

                            // Call the user's callback for post fetching news articles
                            NewsHandler.this.postFetchingNewsArticles(topArticles);
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

    public abstract void  postFetchingNewsArticles(ArrayList<NewsArticle> newsArticles);
}
