package com.thealienobserver.nikhil.travon.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.models.WeatherModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WeatherCardAdapter extends RecyclerView.Adapter<WeatherCardAdapter.ViewHolder> {
    private static final String TAG = "WeatherCardAdapter";

    private Context callerContext;
    ArrayList<WeatherModel> weatherModel;

    public WeatherCardAdapter(Context context, ArrayList<WeatherModel> weatherModel) {
        this.callerContext = context;
        this.weatherModel = weatherModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_weather_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WeatherModel weatherElement = this.weatherModel.get(i);
        viewHolder.Temprature.setText(String.valueOf(weatherElement.getTemprature()));
        viewHolder.Description.setText(weatherElement.getDescription());
        viewHolder.ForecastDate.setText(String.valueOf(weatherElement.getWeather_Date()));
        Glide.with(callerContext).load(weatherElement.getImage_Url()).into(viewHolder.weatherImage);
        DateFormat outTimeDf = new SimpleDateFormat("h:mm a"); //"HH:mm:ss");
        DateFormat outDateDf = new SimpleDateFormat("EEE, MMM d, ''yy"); //"dd-MM-yyyy");

        viewHolder.ForecastDate.setText(outDateDf.format(weatherElement.getWeather_Date()));
        viewHolder.ForecastTime.setText(outTimeDf.format(weatherElement.getWeather_Date()));

    }

    @Override
    public int getItemCount() {
        return this.weatherModel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherImage;
        TextView Temprature,Description,ForecastDate,ForecastTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherImage = itemView.findViewById(R.id.weatherImage);
            //CityName = itemView.findViewById(R.id.lblCityName);
            Temprature = itemView.findViewById(R.id.lblTempeature);
            //Maximum_Temprature = itemView.findViewById(R.id.lblMaximumValue);
            //Minimum_Temprature = itemView.findViewById(R.id.lblMinimumValue);
            //Main = itemView.findViewById(R.id.lblMain);
            Description = itemView.findViewById(R.id.lblDescription);
            ForecastDate=itemView.findViewById(R.id.lblDate);
            ForecastTime=itemView.findViewById(R.id.lblTime);
            //Humidity=itemView.findViewById(R.id.lblHumidityValue);
        }
    }

}
