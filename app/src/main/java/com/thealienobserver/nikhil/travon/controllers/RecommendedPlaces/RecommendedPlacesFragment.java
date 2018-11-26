package com.thealienobserver.nikhil.travon.controllers.RecommendedPlaces;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.adapters.RecommendedPlacesAdapter;
import com.thealienobserver.nikhil.travon.apihandlers.RecommendedPlacesHandler;
import com.thealienobserver.nikhil.travon.models.RecommendedPlace;

import java.util.ArrayList;
import java.util.Objects;


public class RecommendedPlacesFragment extends Fragment implements
        ActivityCompat.OnRequestPermissionsResultCallback {


    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String CITY = "City";
    private String places[] = new String[]{"Attractions", "Hospitals", "Universities", "Restaurants"};

    private int mPage;
    private String mCity;

    public static RecommendedPlacesFragment newInstance(int page, String city) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(CITY, city);
        RecommendedPlacesFragment fragment = new RecommendedPlacesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
            mCity = getArguments().getString(CITY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recommended_places, container, false);


        //fetching places
        RecommendedPlacesHandler recommendedPlacesHandler = new RecommendedPlacesHandler(getActivity()) {
            @Override
            public void postFetchingRecomendedPlaces(ArrayList<RecommendedPlace> recomendedPlaces) {
                RecommendedPlacesFragment.this.setupRecomendedPlaces(recomendedPlaces, view);
            }
        };
        recommendedPlacesHandler.getTopRecomendedPlaces(mCity, places[mPage - 1]);

        return view;
    }

    private void setupRecomendedPlaces(ArrayList<RecommendedPlace> recomendedPlacesArrayList, View view) {

        RecyclerView newsRecyclerView = view.findViewById(R.id.rv_recommended_places);
        TextView tv_no_results = view.findViewById(R.id.tv_no_results);
        ProgressBar pb_loading = view.findViewById(R.id.pb_loading);
        pb_loading.setVisibility(View.GONE);
        if (recomendedPlacesArrayList.size() == 0) {
            tv_no_results.setVisibility(View.VISIBLE);
            return;
        }
        newsRecyclerView.setAdapter(new RecommendedPlacesAdapter(getActivity(), recomendedPlacesArrayList));
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
