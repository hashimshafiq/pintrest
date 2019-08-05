package com.hashimshafiq.pintrestdemo.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileImageResponse implements Parcelable
{
	@SerializedName("small")
	@Expose
	private String small;
	@SerializedName("medium")
	@Expose
	private String medium;
	@SerializedName("large")
	@Expose
	private String large;
	
	protected ProfileImageResponse(Parcel in)
	{
		small = in.readString();
		medium = in.readString();
		large = in.readString();
	}
	
	public static final Creator<ProfileImageResponse> CREATOR = new Creator<ProfileImageResponse>()
	{
		@Override
		public ProfileImageResponse createFromParcel(Parcel in)
		{
			return new ProfileImageResponse(in);
		}
		
		@Override
		public ProfileImageResponse[] newArray(int size)
		{
			return new ProfileImageResponse[size];
		}
	};
	
	public String getSmall()
	{
		return small;
	}
	
	public void setSmall(String small)
	{
		this.small = small;
	}
	
	public String getMedium()
	{
		return medium;
	}
	
	public void setMedium(String medium)
	{
		this.medium = medium;
	}
	
	public String getLarge()
	{
		return large;
	}
	
	public void setLarge(String large)
	{
		this.large = large;
	}
	
	@Override
	public int describeContents()
	{
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int i)
	{
		parcel.writeString(small);
		parcel.writeString(medium);
		parcel.writeString(large);
	}
}