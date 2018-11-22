package com.thealienobserver.nikhil.travon.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.models.CostOfLivingItem;

import java.util.ArrayList;

public class CostOfLivingAdapter extends ArrayAdapter<CostOfLivingItem> {

    private ArrayList<CostOfLivingItem> costItemsList;

    public CostOfLivingAdapter(Context context, int textViewResourceId, ArrayList<CostOfLivingItem> costItemsList) {
        super(context, textViewResourceId, costItemsList);
        this.costItemsList = costItemsList;
        Log.d("SHEEEEEEEEEEELLLO", "" );

        costItemsList = new ArrayList<>();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.view_cost_items, null);
        }

        CostOfLivingItem i = costItemsList.get(position);

        if (i != null) {
            TextView nameTv = v.findViewById(R.id.tvName);
            TextView avgCostTv = v.findViewById(R.id.tvAvgCost);
            TextView rangeTv = v.findViewById(R.id.tvRange);

            nameTv.setText(i.getItemName());
            avgCostTv.setText("$" + String.valueOf(i.getAveragePrice()));
            String pricesRange = String.valueOf(i.getLowestPrice()) + " - " + String.valueOf(i.getHighestPrice());
            rangeTv.setText(pricesRange);
        }


        return v;
    }
}
