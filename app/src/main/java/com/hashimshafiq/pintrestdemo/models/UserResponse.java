package com.hashimshafiq.pintrestdemo.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse implements Parcelable
{
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("profile_image")
	@Expose
	private ProfileImageResponse profileImageResponse;
	@SerializedName("links")
	@Expose
	private LinkResponse linkResponse;
	
	protected UserResponse(Parcel in)
	{
		id = in.readString();
		username = in.readString();
		name = in.readString();
		profileImageResponse = in.readParcelable(ProfileImageResponse.class.getClassLoader());
		linkResponse = in.readParcelable(LinkResponse.class.getClassLoader());
	}
	
	public static final Creator<UserResponse> CREATOR = new Creator<UserResponse>()
	{
		@Override
		public UserResponse createFromParcel(Parcel in)
		{
			return new UserResponse(in);
		}
		
		@Override
		public UserResponse[] newArray(int size)
		{
			return new UserResponse[size];
		}
	};
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public ProfileImageResponse getProfileImageResponse()
	{
		return profileImageResponse;
	}
	
	public void setProfileImageResponse(ProfileImageResponse profileImageResponse)
	{
		this.profileImageResponse = profileImageResponse;
	}
	
	public LinkResponse getLinkResponse()
	{
		return linkResponse;
	}
	
	public void setLinkResponse(LinkResponse linkResponse)
	{
		this.linkResponse = linkResponse;
	}
	
	@Override
	public int describeContents()
	{
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int i)
	{
		parcel.writeString(id);
		parcel.writeString(username);
		parcel.writeString(name);
		parcel.writeParcelable(profileImageResponse, i);
		parcel.writeParcelable(linkResponse, i);
	}
}