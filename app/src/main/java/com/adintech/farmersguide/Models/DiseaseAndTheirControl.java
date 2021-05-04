package com.adintech.farmersguide.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiseaseAndTheirControl {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("control")
    @Expose
    private String control;

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

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

}