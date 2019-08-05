package com.hashimshafiq.pintrestdemo.interfaces;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public interface DetailView {

    void displayPinImage(Bitmap image);
    void displayPinImage(int image);
    void displayProfile(String name,Bitmap image);
    void displayProfile(String name,int image);
}
