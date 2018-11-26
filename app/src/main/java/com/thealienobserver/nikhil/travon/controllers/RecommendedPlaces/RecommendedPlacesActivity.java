
package com.thealienobserver.nikhil.travon.controllers.RecommendedPlaces;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.controllers.MenuScreen;

import static com.thealienobserver.nikhil.travon.controllers.MenuScreen.LATITUDE;
import static com.thealienobserver.nikhil.travon.controllers.MenuScreen.LONGITUDE;


public class RecommendedPlacesActivity extends AppCompatActivity  {

    private String mLatLong;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomended_places_screen);

        mLatLong = getIntent().getStringExtra(LATITUDE) + "," + getIntent().getStringExtra(LONGITUDE);
        city = getIntent().getStringExtra(MenuScreen.CITY);

        setTitle("Places in " + city);

        ViewPager viewPager= findViewById(R.id.viewpager);
        viewPager.setAdapter(new RecommendedFragmentPagerAdapter(getSupportFragmentManager(), city));

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //fetching places
//        RecommendedPlacesHandler recommendedPlacesHandler = new RecommendedPlacesHandler(this) {
//            @Override
//            public void postFetchingRecomendedPlaces(ArrayList<RecommendedPlace> recomendedPlaces) {
//                RecommendedPlacesActivity.this.setupRecomendedPlaces(recomendedPlaces);
//
//            }
//
//
//        };
//        recommendedPlacesHandler.getTopRecomendedPlaces(city);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

//    private void setupRecomendedPlaces(ArrayList<RecommendedPlace> recomendedPlacesArrayList) {
//
//        RecyclerView newsRecyclerView = findViewById(R.id.newsRecyclerView);
//        newsRecyclerView.setAdapter(new RecommendedPlacesAdapter(this, recomendedPlacesArrayList));
//        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
//

    @Override
    protected void onResume() {
        super.onResume();
    }


}
