package com.adintech.farmersguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Weather {

    @SerializedName("data")
    @Expose
    private ArrayList<WeatherInfo> data = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public ArrayList<WeatherInfo> getWeatherData() {
        return data;
    }

    public void setData(ArrayList<WeatherInfo> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
