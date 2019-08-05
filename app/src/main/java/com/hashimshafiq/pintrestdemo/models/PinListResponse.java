package com.hashimshafiq.pintrestdemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PinListResponse {
    @SerializedName("id")
    @Expose
    private String  id;
    @SerializedName("created_at")
    @Expose
    private String  createdAt;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("color")
    @Expose
    private String  color;
    @SerializedName("likes")
    @Expose
    private int likes;
    @SerializedName("liked_by_user")
    @Expose
    private boolean likedByUser;
    @SerializedName("user")
    @Expose
    private UserResponse user;
    @SerializedName("current_user_collections")
    @Expose
    private List<Object> currentUserCollections;
    @SerializedName("urls")
    @Expose
    private ImageUrlsResponse imageUrlsResponse;
    @SerializedName("categories")
    @Expose
    private List<CategoryResponse> categoriesResponse;
    @SerializedName("links")
    @Expose
    private LinkResponse linksResponse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public List<Object> getCurrentUserCollections() {
        return currentUserCollections;
    }

    public void setCurrentUserCollections(List<Object> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }

    public ImageUrlsResponse getImageUrlsResponse() {
        return imageUrlsResponse;
    }

    public void setImageUrlsResponse(ImageUrlsResponse imageUrlsResponse) {
        this.imageUrlsResponse = imageUrlsResponse;
    }

    public List<CategoryResponse> getCategoriesResponse() {
        return categoriesResponse;
    }

    public void setCategoriesResponse(List<CategoryResponse> categoriesResponse) {
        this.categoriesResponse = categoriesResponse;
    }

    public LinkResponse getLinksResponse() {
        return linksResponse;
    }

    public void setLinksResponse(LinkResponse linksResponse) {
        this.linksResponse = linksResponse;
    }

    @Override
    public String toString() {
        return "PinListResponse{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", color='" + color + '\'' +
                ", likes=" + likes +
                ", likedByUser=" + likedByUser +
                ", user=" + user +
                ", currentUserCollections=" + currentUserCollections +
                ", imageUrlsResponse=" + imageUrlsResponse +
                ", categoriesResponse=" + categoriesResponse +
                ", linksResponse=" + linksResponse +
                '}';
    }
}
