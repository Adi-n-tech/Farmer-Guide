package com.adintech.farmersguide.Models;

import java.util.ArrayList;;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CropListResponse {

    @SerializedName("crops")
    @Expose
    private ArrayList<Crop> crops = null;

    public ArrayList<Crop> getCrops() {
        return crops;
    }

    public void setCrops(ArrayList<Crop> crops) {
        this.crops = crops;
    }

}
