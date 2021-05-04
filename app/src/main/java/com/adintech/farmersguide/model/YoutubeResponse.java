package com.adintech.farmersguide.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class YoutubeResponse implements Parcelable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("YoutubeVideoList")
    @Expose
    private ArrayList<YoutubeVideo> youtubeVideoList = null;

    protected YoutubeResponse(Parcel in) {
        message = in.readString();
        youtubeVideoList = in.createTypedArrayList(YoutubeVideo.CREATOR);
    }

    public static final Creator<YoutubeResponse> CREATOR = new Creator<YoutubeResponse>() {
        @Override
        public YoutubeResponse createFromParcel(Parcel in) {
            return new YoutubeResponse(in);
        }

        @Override
        public YoutubeResponse[] newArray(int size) {
            return new YoutubeResponse[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<YoutubeVideo> getYoutubeVideoList() {
        return youtubeVideoList;
    }

    public void setYoutubeVideoList(ArrayList<YoutubeVideo> youtubeVideoList) {
        this.youtubeVideoList = youtubeVideoList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
        parcel.writeTypedList(youtubeVideoList);
    }
}
