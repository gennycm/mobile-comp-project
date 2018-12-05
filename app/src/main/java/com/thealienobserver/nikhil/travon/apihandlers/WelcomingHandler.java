package com.thealienobserver.nikhil.travon.apihandlers;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thealienobserver.nikhil.travon.models.CategoryScore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class to set values to describe the city which user as requested.
 */
public abstract class WelcomingHandler {

    private Context applicationContext;
    private RequestQueue requestQueue;
    private TextView descriptionTv;

    public WelcomingHandler(Context context, TextView descriptionTv) {
        this.applicationContext = context;
        this.descriptionTv = descriptionTv;
    }

    /**
     * Method to display the scores for different parameter in particular city
     *
     * @param cityName
     */
    public void getCityScores(String cityName) {
        requestQueue = Volley.newRequestQueue(applicationContext);
        String getCityScoresURL = "https://api.teleport.org/api/urban_areas/slug:" + cityName.toLowerCase() + "/scores/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getCityScoresURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray categories = response.getJSONArray("categories");
                    String description = response.getString("summary");
                    setDescription(description);

                    ArrayList<CategoryScore> categoryScores = new ArrayList<>();
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject category = categories.getJSONObject(i);
                        Log.d("---category", category.toString());
                        String categoryName = category.getString("name");
                        String color = category.getString("color");
                        Double score_out_of_10 = category.getDouble("score_out_of_10");

                        CategoryScore categoryScore = new CategoryScore(color, categoryName, score_out_of_10);
                        categoryScores.add(categoryScore);
                        Log.d("---category", categoryScores.size() + "");

                        WelcomingHandler.this.postFetchingCategoriesScores(categoryScores);
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
                Log.e("Error:", error.toString());
                Toast.makeText(applicationContext, "There was an error. Please try again later.", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * Method to set description for each parameter in scores
     *
     * @param description
     */

    public void setDescription(String description) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.descriptionTv.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));
        } else {
            this.descriptionTv.setText(Html.fromHtml(description));
        }
    }

    /**
     * Abstract method implemented in the WelcomingActivity Controller.
     *
     * @param categoryScores
     */
    public abstract void postFetchingCategoriesScores(ArrayList<CategoryScore> categoryScores);


}
