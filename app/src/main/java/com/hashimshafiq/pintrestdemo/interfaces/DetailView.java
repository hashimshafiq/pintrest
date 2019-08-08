package com.hashimshafiq.pintrestdemo.interfaces;

public interface DetailView<T> {

    void displayPinImage(T image);
    void displayProfile(String name,T image);

}
