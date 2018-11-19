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
import com.thealienobserver.nikhil.travon.models.Event;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private Context callerContext;
    ArrayList<Event> eventList;

    public EventsAdapter(Context context, ArrayList<Event> eventList) {
        this.callerContext = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_event_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Event currentEvent = this.eventList.get(i);
        viewHolder.eventDescription.setText(currentEvent.getDescription());
        viewHolder.eventName.setText(currentEvent.getName());
        Glide.with(callerContext).load(currentEvent.getImageUrl()).into(viewHolder.eventImage);

    }

    @Override
    public int getItemCount() { return this.eventList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage;
        TextView eventName, eventDescription;

        public ViewHolder(@NonNull View itemView) {
            // TODO: Add new items
            super(itemView);
            eventImage = itemView.findViewById(R.id.eventImage);
            eventName = itemView.findViewById(R.id.eventName);
            eventDescription = itemView.findViewById(R.id.eventDescription);
        }
    }
}
