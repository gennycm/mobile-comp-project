package com.thealienobserver.nikhil.travon.models;

public class CostOfLivingItem {
    private String item_name;
    private String lowest_price;
    private String average_price;
    private String highest_price;


    public CostOfLivingItem(String item_name, String lowest_price, String average_price, String highest_price) {
        this.item_name = item_name;
        this.lowest_price = lowest_price;
        this.average_price = average_price;
        this.highest_price = highest_price;
    }

    public String getItemName() {
        return item_name;
    }

    public String getLowestPrice() {
        return lowest_price;
    }

    public String getAveragePrice() {
        return average_price;
    }

    public String getHighestPrice() {
        return highest_price;
    }

}

