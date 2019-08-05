package com.hashimshafiq.pintrestdemo.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkResponse implements Parcelable
{
    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("html")
    @Expose
    private String html;
    @SerializedName("photos")
    @Expose
    private String photos;
    @SerializedName("likes")
    @Expose
    private String likes;

    protected LinkResponse(Parcel in)
    {
        self = in.readString();
        html = in.readString();
        photos = in.readString();
        likes = in.readString();
    }

    public static final Creator<LinkResponse> CREATOR = new Creator<LinkResponse>()
    {
        @Override
        public LinkResponse createFromParcel(Parcel in)
        {
            return new LinkResponse(in);
        }

        @Override
        public LinkResponse[] newArray(int size)
        {
            return new LinkResponse[size];
        }
    };

    public String getSelf()
    {
        return self;
    }

    public void setSelf(String self)
    {
        this.self = self;
    }

    public String getHtml()
    {
        return html;
    }

    public void setHtml(String html)
    {
        this.html = html;
    }

    public String getPhotos()
    {
        return photos;
    }

    public void setPhotos(String photos)
    {
        this.photos = photos;
    }

    public String getLikes()
    {
        return likes;
    }

    public void setLikes(String likes)
    {
        this.likes = likes;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(self);
        parcel.writeString(html);
        parcel.writeString(photos);
        parcel.writeString(likes);
    }
}