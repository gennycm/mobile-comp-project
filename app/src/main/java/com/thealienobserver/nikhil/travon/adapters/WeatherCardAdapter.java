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
        Log.d(TAG, "bind view called");
        WeatherModel weatherElement = this.weatherModel.get(i);
        viewHolder.CityName.setText(weatherElement.getCity());
        viewHolder.Temprature.setText(String.valueOf(weatherElement.getTemprature()));
        viewHolder.Humidity.setText(String.valueOf(weatherElement.getHumidity()));
        viewHolder.Minimum_Temprature.setText(String.valueOf(weatherElement.getMinimum_Temprature()));
        viewHolder.Maximum_Temprature.setText(String.valueOf(weatherElement.getMaximum_Temprature()));
        viewHolder.Main.setText(weatherElement.getMain());
        viewHolder.Description.setText(weatherElement.getDescription());
        viewHolder.ForecastDate.setText(String.valueOf(weatherElement.getWeather_Date()));
        Log.d("ImageeeeeeeeUrl",weatherElement.getImage_Url());
        Glide.with(callerContext).load(weatherElement.getImage_Url()).into(viewHolder.weatherImage);
    }

    @Override
    public int getItemCount() {
        return this.weatherModel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherImage;
        TextView Temprature, Humidity,Minimum_Temprature,Maximum_Temprature,Main,Description,CityName,ForecastDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherImage = itemView.findViewById(R.id.weatherImage);
            CityName = itemView.findViewById(R.id.lblCityName);
            Temprature = itemView.findViewById(R.id.lblTempeature);
            Maximum_Temprature = itemView.findViewById(R.id.lblMaximumValue);
            Minimum_Temprature = itemView.findViewById(R.id.lblMinimumValue);
            Main = itemView.findViewById(R.id.lblMain);
            Description = itemView.findViewById(R.id.lblDescription);
            ForecastDate=itemView.findViewById(R.id.lblDate);
            Humidity=itemView.findViewById(R.id.lblHumidityValue);
        }
    }

}
