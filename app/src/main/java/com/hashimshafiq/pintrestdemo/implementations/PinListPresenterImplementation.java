package com.hashimshafiq.pintrestdemo.implementations;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;
import com.google.gson.Gson;
import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver;
import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload;
import com.hashimshafiq.asyncimageloader.models.ServiceJsonDownload;
import com.hashimshafiq.asyncimageloader.utilities.ContentTypeServiceDownload;
import com.hashimshafiq.pintrestdemo.interfaces.PinListPresenter;
import com.hashimshafiq.pintrestdemo.interfaces.PinListView;
import com.hashimshafiq.pintrestdemo.models.PinListResponse;
import com.hashimshafiq.pintrestdemo.utilities.CONSTANTS;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class PinListPresenterImplementation implements PinListPresenter {


    private PinListView listingView;
    private Context context;
    private ContentTypeServiceDownload mProvider;
    private ArrayList<PinListResponse> pins;


    public PinListPresenterImplementation(Context context){
        this.listingView = null;
        this.context = context;
        this.mProvider = ContentTypeServiceDownload.Companion.getInstance();
        pins = new ArrayList<>();
    }


    @Override
    public void fetchPins() {

        ServiceContentTypeDownload mDataTypeJson = new ServiceJsonDownload(CONSTANTS.API_URL, new ContentServiceObserver()
        {
            @Override
            public void onStart(ServiceContentTypeDownload mDownloadDataType)
            {

            }

            @Override
            public void onSuccess(final ServiceContentTypeDownload mDownloadDataType)
            {
                String response = new String(mDownloadDataType.getData(), StandardCharsets.UTF_8);
                PinListResponse[] pinListResponses = new Gson().fromJson(response, PinListResponse[].class);

                if (pinListResponses.length != 0)
                {
                    pins.clear();
                    Collections.addAll(pins, pinListResponses);
                }
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    if (listingView != null)
                    {
                        listingView.displayPins(pins);
                    }
                }, context.getResources().getInteger(android.R.integer.config_mediumAnimTime));

            }

            @Override
            public void onFailure(ServiceContentTypeDownload mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e)
            {
            }

            @Override
            public void onRetry(ServiceContentTypeDownload mDownloadDataType, int retryNo)
            {

            }
        });
        mProvider.getRequest(mDataTypeJson);

    }

    @Override
    public void setListingView(PinListView listView) {
        this.listingView = listView;
    }





}
