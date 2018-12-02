
package com.thealienobserver.nikhil.travon.controllers.RecommendedPlaces;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.controllers.MenuScreen;

import static com.thealienobserver.nikhil.travon.controllers.MenuScreen.LATITUDE;
import static com.thealienobserver.nikhil.travon.controllers.MenuScreen.LONGITUDE;


public class RecommendedPlacesActivity extends AppCompatActivity {

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomended_places_screen);

       //Setting the tool bar title

        city = getIntent().getStringExtra(MenuScreen.CITY);
        setTitle("Places in " + city);


        // Creating the  reference for viewpager and adding adapter to it

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new RecommendedFragmentPagerAdapter(getSupportFragmentManager(), city));


        //Creating the reference for tablayout and adding viewpager to it

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


}
