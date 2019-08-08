package com.hashimshafiq.pintrestdemo.interfaces;

public interface DetailPresenter {

    void fetchPinImage(String imageURL);
    void fetchProfile(String name,String imageURL);
    void setDetailView(DetailView detailView);
}
