package com.thealienobserver.nikhil.travon.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.models.ImmigrationItem;

import java.util.ArrayList;

public class ImmigrationAdapter extends ArrayAdapter<ImmigrationItem> {

    private static final String TAG = "ImmigrationAdapter";
    private ArrayList<ImmigrationItem> immigrationItems;

    public ImmigrationAdapter(Context context, int textViewResourceId, ArrayList<ImmigrationItem> immigrationItems) {
        super(context, textViewResourceId, immigrationItems);
        this.immigrationItems = immigrationItems;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.view_immigration_items, null);
        }

        ImmigrationItem item = immigrationItems.get(position);

        if (item != null) {
            TextView immigrationName = v.findViewById(R.id.immigrationName);
            TextView immigrationDescription = v.findViewById(R.id.immigrationDescription);

            immigrationName.setText(item.getName());
            immigrationDescription.setText(item.getDescription());
        } else {
            //Log.e(TAG, String.format(res.getString(R.string.null_item)));

        }
        return v;
    }
}


