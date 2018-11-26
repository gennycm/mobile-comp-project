package com.thealienobserver.nikhil.travon.models;
import java.util.Date;

public class WeatherModel {
    Double Temprature;
    String Description;
    String Image_Url;
    Date Weather_Date;

    public WeatherModel(Double Temprature,String Description,String Image_Url, Date Weather_Date) {
        this.Temprature = Temprature;
        this.Description = Description;
        this.Image_Url=Image_Url;
        this.Weather_Date=Weather_Date;
    }
    public Double getTemprature() {
        return Temprature;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage_Url() {
        return Image_Url;
    }

    public Date getWeather_Date(){return Weather_Date;}
}
