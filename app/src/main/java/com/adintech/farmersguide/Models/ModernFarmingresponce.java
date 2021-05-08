
package com.adintech.farmersguide.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModernFarmingresponce {

    @SerializedName("farmingmethod")
    @Expose
    private ArrayList<Farmingmethod> farmingmethod = null;

    public ArrayList<Farmingmethod> getFarmingmethod() {
        return farmingmethod;
    }

    public void setFarmingmethod(ArrayList<Farmingmethod> farmingmethod) {
        this.farmingmethod = farmingmethod;
    }

}
