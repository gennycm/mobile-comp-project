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

    public static final String COUNTRY_CODE_PARAM = "COUNTRY_CODE_PARAM";
    public static final String LAT_LON_PARAM = "LAT_LON_PARAM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_screen);

        //fetching News
        String countryCodeParam = getIntent().getStringExtra(COUNTRY_CODE_PARAM);
        NewsHandler newsHandler = new NewsHandler(this) {
            @Override
            public void postFetchingNewsArticles(ArrayList<NewsArticle> newsArticles) {
                NewsScreen.this.setupNewsCards(newsArticles);
            }
        };
        newsHandler.getTopNewsHeadlines(countryCodeParam);
    }

    private void setupNewsCards(ArrayList<NewsArticle> newsArticles) {
//        ArrayList newsArticles = new ArrayList();
//        newsArticles.add(new NewsArticle("This is awesome news", "This is awesome news's description", "", "","", new Date()));
//        newsArticles.add(new NewsArticle("This is awesome news1", "This is awesome news1's description", "", "","", new Date()));
//        newsArticles.add(new NewsArticle("This is awesome news2", "This is awesome news2's description", "", "","", new Date()));
//        newsArticles.add(new NewsArticle("This is awesome news3", "This is awesome news3's description", "", "","", new Date()));
//        newsArticles.add(new NewsArticle("This is awesome news4", "This is awesome news4's description", "", "","", new Date()));
//        newsArticles.add(new NewsArticle("This is awesome news5", "This is awesome news5's description", "", "","", new Date()));


        RecyclerView newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setAdapter(new NewsCardsAdapter(this, newsArticles));
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
