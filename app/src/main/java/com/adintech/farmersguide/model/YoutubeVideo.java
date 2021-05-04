
package com.adintech.farmersguide.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YoutubeVideo implements Parcelable {

    @SerializedName("videoThumbnail")
    @Expose
    private String videoThumbnail;
    @SerializedName("videoTitle")
    @Expose
    private String videoTitle;
    @SerializedName("videoDescription")
    @Expose
    private String videoDescription;
    @SerializedName("videoLink")
    @Expose
    private String videoLink;

    protected YoutubeVideo(Parcel in) {
        videoThumbnail = in.readString();
        videoTitle = in.readString();
        videoDescription = in.readString();
        videoLink = in.readString();
    }

    public static final Creator<YoutubeVideo> CREATOR = new Creator<YoutubeVideo>() {
        @Override
        public YoutubeVideo createFromParcel(Parcel in) {
            return new YoutubeVideo(in);
        }

        @Override
        public YoutubeVideo[] newArray(int size) {
            return new YoutubeVideo[size];
        }
    };

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(videoThumbnail);
        parcel.writeString(videoTitle);
        parcel.writeString(videoDescription);
        parcel.writeString(videoLink);
    }
}
