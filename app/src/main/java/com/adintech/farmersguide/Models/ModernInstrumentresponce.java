
package com.adintech.farmersguide.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModernInstrumentresponce {

    @SerializedName("modernInstruments")
    @Expose
    private ArrayList<ModernInstrument> modernInstruments = null;

    public ArrayList<ModernInstrument> getModernInstruments() {
        return modernInstruments;
    }

    public void setModernInstruments(ArrayList<ModernInstrument> modernInstruments) {
        this.modernInstruments = modernInstruments;
    }

}
