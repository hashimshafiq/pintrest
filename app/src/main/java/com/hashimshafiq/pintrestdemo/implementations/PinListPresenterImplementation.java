package com.hashimshafiq.pintrestdemo.implementations;

import android.content.Context;
import android.os.Handler;
import com.google.gson.Gson;
import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver;
import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload;
import com.hashimshafiq.asyncimageloader.models.ServiceJsonDownload;
import com.hashimshafiq.asyncimageloader.utilities.ContentTypeServiceDownload;
import com.hashimshafiq.pintrestdemo.interfaces.PinListPresenter;
import com.hashimshafiq.pintrestdemo.interfaces.PinListView;
import com.hashimshafiq.pintrestdemo.models.PinListResponse;
import com.hashimshafiq.pintrestdemo.utilities.CONSTANTS;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
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
        mProvider = ContentTypeServiceDownload.Companion.getInstance();
        pins = new ArrayList<>();
        this.context = context;
    }


    @Override
    public void fetchPins() {

        ServiceContentTypeDownload mDataTypeJson = new ServiceJsonDownload(CONSTANTS.API_URL, new ContentServiceObserver()
        {
            @Override
            public void onStart(@NotNull ServiceContentTypeDownload mDownloadDataType)
            {

            }

            @Override
            public void onSuccess(@NotNull final ServiceContentTypeDownload mDownloadDataType)
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
            public void onFailure(@NotNull ServiceContentTypeDownload mDownloadDataType, int statusCode, @NotNull byte[] errorResponse, @NotNull Throwable e)
            {
            }

            @Override
            public void onRetry(@NotNull ServiceContentTypeDownload mDownloadDataType, int retryNo)
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
