/**
 * Created by Nihkil Tyagi
 */
package com.thealienobserver.nikhil.travon.apihandlers;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thealienobserver.nikhil.travon.models.NewsArticle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public abstract class NewsHandler {
    //    private static NewsHandler newsHandlerInstance;
    private Context applicationContext;

    private static final String TOP_NEWS_URL = "https://newsapi.org/v2/top-headlines?apiKey=52a70f17e25a4aadb0e73d77d75667ea";
    private static final String EVERY_NEWS_URL = "https://newsapi.org/v2/everything?apiKey=52a70f17e25a4aadb0e73d77d75667ea&pageSize=10";


    public NewsHandler(Context context) {
        this.applicationContext = context;
    }

    /**
     * Sends the API request to retrieve the news list
     * @param city
     * @param country
     */

    public void getNewsArticles(String city, String country) {
        RequestQueue requestQueue = Volley.newRequestQueue(applicationContext);
//        String url = TOP_NEWS_URL + "&country=" + countryCode;
        String url = EVERY_NEWS_URL + "&q=" + city + "%20" + country;
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
                    Toast.makeText(applicationContext, "There was an error. Please try again later.", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.d("News Handler", new String(error.networkResponse.data));
                Toast.makeText(applicationContext, "There was an error. Please try again later.", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public abstract void  postFetchingNewsArticles(ArrayList<NewsArticle> newsArticles);
}