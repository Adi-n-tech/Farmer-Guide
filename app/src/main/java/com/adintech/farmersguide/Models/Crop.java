
package com.adintech.farmersguide.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Crop implements Parcelable {

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
    @SerializedName("diseaseList")
    @Expose
    private ArrayList<Disease> diseaseList;


    protected Crop(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        relatedImages = in.createStringArrayList();
        soil = in.readString();
        landPrepration = in.readString();
        timeOfSowing = in.readString();
        diseaseList = in.createTypedArrayList(Disease.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeStringList(relatedImages);
        dest.writeString(soil);
        dest.writeString(landPrepration);
        dest.writeString(timeOfSowing);
        dest.writeTypedList(diseaseList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Crop> CREATOR = new Creator<Crop>() {
        @Override
        public Crop createFromParcel(Parcel in) {
            return new Crop(in);
        }

        @Override
        public Crop[] newArray(int size) {
            return new Crop[size];
        }
    };

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

    public ArrayList<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(ArrayList<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

}
