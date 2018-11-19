package com.thealienobserver.nikhil.travon.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.adapters.NewsCardsAdapter;
import com.thealienobserver.nikhil.travon.apihandlers.NewsHandler;
import com.thealienobserver.nikhil.travon.models.NewsArticle;

import java.util.ArrayList;

public class NewsScreen extends AppCompatActivity {
    private static final String TAG = "NewsScreen";

    public static final String COUNTRY_PARAM = "COUNTRY_CODE_PARAM";
    public static final String CITY_PARAM = "CITY_PARAM";
//    public static final String LAT_LON_PARAM = "LAT_LON_PARAM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_screen);

        //fetching News
        String countryParam = getIntent().getStringExtra(NewsScreen.COUNTRY_PARAM);
        String cityParam = getIntent().getStringExtra(NewsScreen.CITY_PARAM);
        NewsHandler newsHandler = new NewsHandler(this) {
            @Override
            public void postFetchingNewsArticles(ArrayList<NewsArticle> newsArticles) {
                NewsScreen.this.setupNewsCards(newsArticles);
            }
        };
        newsHandler.getNewsArticles(cityParam, countryParam);
    }

    private void setupNewsCards(ArrayList<NewsArticle> newsArticles) {
        RecyclerView newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setAdapter(new NewsCardsAdapter(this, newsArticles));
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
