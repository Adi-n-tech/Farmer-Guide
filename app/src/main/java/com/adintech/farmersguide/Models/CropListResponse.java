
package com.adintech.farmersguide.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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
