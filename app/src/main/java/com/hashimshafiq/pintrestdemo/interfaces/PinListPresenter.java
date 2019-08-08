package com.hashimshafiq.pintrestdemo.interfaces;

import android.content.Context;

public interface PinListPresenter {

    void fetchPins();
    void setListingView(PinListView listView);
    void setContext(Context context);
}
