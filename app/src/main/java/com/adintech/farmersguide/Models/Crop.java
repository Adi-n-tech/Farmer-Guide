
package com.adintech.farmersguide.Models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Crop {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("relatedImages")
    @Expose
    private ArrayList<String> relatedImages = null;
    @SerializedName("soil")
    @Expose
    private String soil;
    @SerializedName("landPrepration")
    @Expose
    private String landPrepration;
    @SerializedName("timeOfSowing")
    @Expose
    private String timeOfSowing;
    @SerializedName("pestAndTheirControl")
    @Expose
    private ArrayList<PestAndTheirControl> pestAndTheirControl = null;
    @SerializedName("diseaseAndTheirControl")
    @Expose
    private ArrayList<DiseaseAndTheirControl> diseaseAndTheirControl = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<String> getRelatedImages() {
        return relatedImages;
    }

    public void setRelatedImages(ArrayList<String> relatedImages) {
        this.relatedImages = relatedImages;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getLandPrepration() {
        return landPrepration;
    }

    public void setLandPrepration(String landPrepration) {
        this.landPrepration = landPrepration;
    }

    public String getTimeOfSowing() {
        return timeOfSowing;
    }

    public void setTimeOfSowing(String timeOfSowing) {
        this.timeOfSowing = timeOfSowing;
    }

    public ArrayList<PestAndTheirControl> getPestAndTheirControl() {
        return pestAndTheirControl;
    }

    public void setPestAndTheirControl(ArrayList<PestAndTheirControl> pestAndTheirControl) {
        this.pestAndTheirControl = pestAndTheirControl;
    }

    public ArrayList<DiseaseAndTheirControl> getDiseaseAndTheirControl() {
        return diseaseAndTheirControl;
    }

    public void setDiseaseAndTheirControl(ArrayList<DiseaseAndTheirControl> diseaseAndTheirControl) {
        this.diseaseAndTheirControl = diseaseAndTheirControl;
    }

}
