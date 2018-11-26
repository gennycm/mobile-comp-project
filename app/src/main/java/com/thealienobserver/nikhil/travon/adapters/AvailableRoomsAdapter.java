package com.thealienobserver.nikhil.travon.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.models.AvailableRoomsModel;
import java.util.ArrayList;

public class AvailableRoomsAdapter extends RecyclerView.Adapter<AvailableRoomsAdapter.ViewHolder>{
    private static final String TAG = "AvailableRoomsAdapter";

    private Context callerContext;
    ArrayList<AvailableRoomsModel> availableRoomsModel;

    public AvailableRoomsAdapter(Context context, ArrayList<AvailableRoomsModel> availableRoomsModel) {
        this.callerContext = context;
        this.availableRoomsModel = availableRoomsModel;
    }

    @NonNull
    @Override
    public AvailableRoomsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_availablerooms_items, parent, false);
        AvailableRoomsAdapter.ViewHolder holder = new AvailableRoomsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableRoomsAdapter.ViewHolder viewHolder, int i) {
        AvailableRoomsModel availableroomsElement = this.availableRoomsModel.get(i);
        Glide.with(callerContext).load(availableroomsElement.getImg1()).into(viewHolder.roomsImage);
        viewHolder.rent.setText(String.valueOf(availableroomsElement.getRent()));
        //viewHolder.city.setText(availableroomsElement.getLocation());
    }

    @Override
    public int getItemCount() {
        return this.availableRoomsModel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView roomsImage;
        TextView rent,city;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomsImage = itemView.findViewById(R.id.roomsImage);
            rent = itemView.findViewById(R.id.rent);
            city=itemView.findViewById(R.id.city);
        }
    }

}