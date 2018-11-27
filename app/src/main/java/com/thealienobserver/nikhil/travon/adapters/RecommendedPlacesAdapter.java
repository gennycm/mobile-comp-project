package com.thealienobserver.nikhil.travon.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.controllers.DetailActivity;
import com.thealienobserver.nikhil.travon.models.RecommendedPlace;

import java.util.ArrayList;

public class RecommendedPlacesAdapter extends RecyclerView.Adapter<RecommendedPlacesAdapter.ViewHolder> {
    private static final String TAG = "PlaceCardsAdapter";
    public static final String PLACE_ADDRESS = "address";
    public static final String PLACE_PHONE = "phone";

    private Context callerContext;
    ArrayList<RecommendedPlace> recommendedPlaces;

    public static final String PLACE_IMAGE = "placeImage";
    public static final String PLACE_TITLE = "placeTitle";
    public static final String PLACE_DESCRIPTION = "placeDescription";

    public RecommendedPlacesAdapter(Context context, ArrayList<RecommendedPlace> recommendedPlaces) {
        this.callerContext = context;
        this.recommendedPlaces = recommendedPlaces;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_places_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "bind view called");
        final RecommendedPlace currentArticle = this.recommendedPlaces.get(i);
        viewHolder.tv_description.setText(currentArticle.getDescription());
        viewHolder.tv_title.setText(currentArticle.getName());
        viewHolder.tv_address.setText(currentArticle.getFormattedAddress());
        viewHolder.tv_phone.setText(currentArticle.getFormattedPhoneNumber());

        CircularProgressDrawable loading = new CircularProgressDrawable(callerContext);
        loading.setStrokeWidth(5f);
        loading.setCenterRadius(30f);
        loading.start();

        Glide.with(callerContext).load(currentArticle.getImage_ref()).
                apply(new RequestOptions().placeholder(loading).error(loading)).into(viewHolder.iv_recommended_place);

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(callerContext, DetailActivity.class);
                intent.putExtra(PLACE_DESCRIPTION, currentArticle.getDescription());
                intent.putExtra(PLACE_IMAGE, currentArticle.getImage_ref());
                intent.putExtra(PLACE_TITLE, currentArticle.getName());
                intent.putExtra(PLACE_ADDRESS, currentArticle.getFormattedAddress());
                intent.putExtra(PLACE_PHONE, currentArticle.getFormattedPhoneNumber());
                callerContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.recommendedPlaces.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_recommended_place;
        TextView tv_description, tv_title, tv_phone, tv_address;
        CardView container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_recommended_place = itemView.findViewById(R.id.iv_recommended_place);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_title = itemView.findViewById(R.id.tv_title);
            container = itemView.findViewById(R.id.container);
            tv_phone = itemView.findViewById(R.id.tv_phone_number);
            tv_address = itemView.findViewById(R.id.tv_location);


        }
    }

}
