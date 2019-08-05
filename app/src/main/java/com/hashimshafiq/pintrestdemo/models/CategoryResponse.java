package com.hashimshafiq.pintrestdemo.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Category model
 * @author SandeepD
 */
public class CategoryResponse implements Parcelable
{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("photo_count")
    @Expose
    private int photoCount;
    @SerializedName("links")
    @Expose
    private LinkResponse links;
    @SerializedName("download")
    @Expose
    private String download;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getPhotoCount()
    {
        return photoCount;
    }

    public void setPhotoCount(int photoCount)
    {
        this.photoCount = photoCount;
    }

    public LinkResponse getLinks()
    {
        return links;
    }

    public void setLinks(LinkResponse links)
    {
        this.links = links;
    }

    public String getDownload()
    {
        return download;
    }

    public void setDownload(String download)
    {
        this.download = download;
    }

    protected CategoryResponse(Parcel in)
    {
        id = in.readInt();
        title = in.readString();
        photoCount = in.readInt();
        links = in.readParcelable(LinkResponse.class.getClassLoader());
        download = in.readString();
    }

    public static final Creator<CategoryResponse> CREATOR = new Creator<CategoryResponse>()
    {
        @Override
        public CategoryResponse createFromParcel(Parcel in)
        {
            return new CategoryResponse(in);
        }

        @Override
        public CategoryResponse[] newArray(int size)
        {
            return new CategoryResponse[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(photoCount);
        parcel.writeParcelable(links, i);
        parcel.writeString(download);
    }
}