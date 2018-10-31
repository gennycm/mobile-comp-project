package com.thealienobserver.nikhil.travon.models;
import java.util.Date;

public class WeatherModel {
    String City;
    Double Temprature;
    Double Humidity;
    Double Minimum_Temprature;
    Double Maximum_Temprature;
    String Main;
    String Description;
    String Image_Url;
    Date Weather_Date;

    public WeatherModel(String City,Double Temprature, Double Humidity, Double Minimum_Temprature, Double Maximum_Temprature, String Main,String Description,String Image_Url, Date Weather_Date) {
        this.City=City;
        this.Temprature = Temprature;
        this.Humidity = Humidity;
        this.Minimum_Temprature = Minimum_Temprature;
        this.Maximum_Temprature = Maximum_Temprature;
        this.Main = Main;
        this.Description = Description;
        this.Image_Url=Image_Url;
        this.Weather_Date=Weather_Date;
    }
    public String getCity(){return City;}

    public Double getTemprature() {
        return Temprature;
    }

    public Double getHumidity() {
        return Humidity;
    }

    public Double getMinimum_Temprature() {
        return Minimum_Temprature;
    }

    public Double getMaximum_Temprature() {
        return Maximum_Temprature;
    }

    public String getMain() {
        return Main;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage_Url() {
        return Image_Url;
    }

    public Date getWeather_Date(){return Weather_Date;}

}
