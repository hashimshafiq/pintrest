package com.hashimshafiq.pintrestdemo.interfaces;

import com.hashimshafiq.pintrestdemo.models.PinListResponse;

import java.util.List;

public interface PinListView {

    void displayPins(List<PinListResponse> pinListResponses);
}
