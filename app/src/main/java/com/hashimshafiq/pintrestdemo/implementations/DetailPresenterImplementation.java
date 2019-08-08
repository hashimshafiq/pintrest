package com.hashimshafiq.pintrestdemo.implementations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver;
import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload;
import com.hashimshafiq.asyncimageloader.models.ServiceImageDownload;
import com.hashimshafiq.asyncimageloader.utilities.ContentTypeServiceDownload;
import com.hashimshafiq.pintrestdemo.interfaces.DetailPresenter;
import com.hashimshafiq.pintrestdemo.interfaces.DetailView;

import javax.inject.Inject;

public class DetailPresenterImplementation implements DetailPresenter {

    private DetailView detailView;
    private Context context;
    private ContentTypeServiceDownload mProvider;

    @Inject
    Drawable placeHolderImage;


    public DetailPresenterImplementation(Context context) {
        this.detailView = null;
        this.context = context;
        this.mProvider = ContentTypeServiceDownload.Companion.getInstance();
    }


    @Override
    public void fetchPinImage(String imageURL) {

            ServiceContentTypeDownload serviceImageDownload = new ServiceImageDownload(imageURL, new ContentServiceObserver() {
                @Override
                public void onStart(ServiceContentTypeDownload mDownloadDataType) {
                    detailView.displayPinImage(placeHolderImage);
                }

                @SuppressLint("DefaultLocale")
                @Override
                public void onSuccess(ServiceContentTypeDownload mDownloadDataType) {
                    detailView.displayPinImage(((ServiceImageDownload) mDownloadDataType).getImageBitmap());
                }

                @Override
                public void onFailure(ServiceContentTypeDownload mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e) {
                    detailView.displayPinImage(placeHolderImage);
                }

                @Override
                public void onRetry(ServiceContentTypeDownload mDownloadDataType, int retryNo) {

                }
            });
            mProvider.getRequest(serviceImageDownload);




    }

    @Override
    public void fetchProfile(String name, String imageURL) {
        ServiceContentTypeDownload serviceImageDownload = new ServiceImageDownload(imageURL, new ContentServiceObserver() {
            @Override
            public void onStart(ServiceContentTypeDownload mDownloadDataType) {
                detailView.displayProfile(name,placeHolderImage);
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onSuccess(ServiceContentTypeDownload mDownloadDataType) {
                detailView.displayProfile(name,((ServiceImageDownload) mDownloadDataType).getImageBitmap());
            }

            @Override
            public void onFailure(ServiceContentTypeDownload mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e) {
                detailView.displayProfile(name,placeHolderImage);
            }

            @Override
            public void onRetry(ServiceContentTypeDownload mDownloadDataType, int retryNo) {

            }
        });
        mProvider.getRequest(serviceImageDownload);


    }

    @Override
    public void setDetailView(DetailView detailView) {
        this.detailView = detailView;
    }
}
