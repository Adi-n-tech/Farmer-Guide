
package com.adintech.farmersguide.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Disease implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("control")
    @Expose
    private String control;

    protected Disease(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readString();
        control = in.readString();
    }

    public static final Creator<Disease> CREATOR = new Creator<Disease>() {
        @Override
        public Disease createFromParcel(Parcel in) {
            return new Disease(in);
        }

        @Override
        public Disease[] newArray(int size) {
            return new Disease[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(control);
    }
}
