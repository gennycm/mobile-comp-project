package com.thealienobserver.nikhil.travon.controllers.CostOfLiving;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.adapters.CostOfLivingAdapter;
import com.thealienobserver.nikhil.travon.apihandlers.CostOfLivingHandler;
import com.thealienobserver.nikhil.travon.models.CostOfLivingItem;

import java.util.ArrayList;

public class CostOfLivingDetailsScreen extends AppCompatActivity {
    public static final String CITY = "CITY";
    public static final String BG_COLOR = "BG_COLOR";
    public static final String CATEGORY_TITLE = "CATEGORY_TITLE";

    private String city;
    private int bgcolor;
    private String categoryTitle;

    private TextView categoryTitleTv, nameTV, avgCost, range, lastUpdated;
    private RelativeLayout categoryLayout;
    private CostOfLivingAdapter adapter;
    private ArrayList<CostOfLivingItem> costList;
    CostOfLivingHandler costOfLivingHandlerInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_of_living_details);


       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

       // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        costOfLivingHandlerInstance = CostOfLivingHandler.getInstance(getApplicationContext());

        city = getIntent().getStringExtra(CITY);

        bgcolor = getIntent().getIntExtra(BG_COLOR, 0);
        categoryTitle = getIntent().getStringExtra(CATEGORY_TITLE);
        costList = new ArrayList<>();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(bgcolor));
        getSupportActionBar().setElevation(0);

        ListView lvCostItems = findViewById(R.id.costItems);

        categoryTitleTv = findViewById(R.id.categoryTitleTextView);
        nameTV = findViewById(R.id.nameTV);
        avgCost = findViewById(R.id.avgCost);
        range = findViewById(R.id.range);
        categoryLayout = findViewById(R.id.categoryLayout);
        lastUpdated = findViewById(R.id.lastUpdatedTv);

        categoryTitleTv.setText(categoryTitle);
        //setTitle(categoryTitle + " costs in " + city);

        lastUpdated.setText(costOfLivingHandlerInstance.getLastUpdated());

        categoryLayout.setBackgroundColor(bgcolor);
        nameTV.setTextColor(bgcolor);
        avgCost.setTextColor(bgcolor);
        avgCost.setText(avgCost.getText() + "(" + costOfLivingHandlerInstance.getCurrency() + ")");
        range.setTextColor(bgcolor);


        adapter = new CostOfLivingAdapter(this, R.layout.view_cost_items, costList);
        lvCostItems.setAdapter(adapter);


        switch (categoryTitle) {
            case "Transport":
                ArrayList transport = costOfLivingHandlerInstance.getTransportation();
                setCostsListOnView(transport);
                break;
            case "Food":
                ArrayList food = costOfLivingHandlerInstance.getFood();
                setCostsListOnView(food);
                break;
            case "Utilities":
                ArrayList utilities = costOfLivingHandlerInstance.getUtilities();
                setCostsListOnView(utilities);
                break;
            case "Room":
                ArrayList room = costOfLivingHandlerInstance.getRoom();
                setCostsListOnView(room);
                break;
            case "Childcare":
                ArrayList childcare = costOfLivingHandlerInstance.getChildcare();
                setCostsListOnView(childcare);
                break;
            case "Clothing":
                ArrayList clothing = costOfLivingHandlerInstance.getClothing();
                setCostsListOnView(clothing);
                break;
        }

    }

    public void setCostsListOnView(ArrayList results) {
        costList.clear();

        // get and set items data ArrayList
        for (int i = 0; i < results.size(); i++) {
            CostOfLivingItem item = (CostOfLivingItem) results.get(i);
            costList.add(item);
        }
        adapter.notifyDataSetChanged();
    }
}
