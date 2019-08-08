package com.hashimshafiq.pintrestdemo.implementations;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver;
import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload;
import com.hashimshafiq.asyncimageloader.models.ServiceImageDownload;
import com.hashimshafiq.asyncimageloader.utilities.ContentTypeServiceDownload;
import com.hashimshafiq.pintrestdemo.interfaces.DetailPresenter;
import com.hashimshafiq.pintrestdemo.interfaces.DetailView;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class DetailPresenterImplementation implements DetailPresenter {

    private DetailView detailView;
    private ContentTypeServiceDownload mProvider;

    @Inject
    Drawable placeHolderImage;


    public DetailPresenterImplementation() {
        this.detailView = null;
        mProvider = ContentTypeServiceDownload.Companion.getInstance();
    }


    @Override
    public void fetchPinImage(String imageURL) {

            ServiceContentTypeDownload serviceImageDownload = new ServiceImageDownload(imageURL, new ContentServiceObserver() {
                @Override
                public void onStart(@NotNull ServiceContentTypeDownload mDownloadDataType) {
                    detailView.displayPinImage(placeHolderImage);
                }

                @SuppressLint("DefaultLocale")
                @Override
                public void onSuccess(@NotNull ServiceContentTypeDownload mDownloadDataType) {
                    detailView.displayPinImage(((ServiceImageDownload) mDownloadDataType).getImageBitmap());
                }

                @Override
                public void onFailure(@NotNull ServiceContentTypeDownload mDownloadDataType, int statusCode, @NotNull byte[] errorResponse, Throwable e) {
                    detailView.displayPinImage(placeHolderImage);
                }

                @Override
                public void onRetry(@NotNull ServiceContentTypeDownload mDownloadDataType, int retryNo) {

                }
            });
            mProvider.getRequest(serviceImageDownload);




    }

    @Override
    public void fetchProfile(String name, String imageURL) {
        ServiceContentTypeDownload serviceImageDownload = new ServiceImageDownload(imageURL, new ContentServiceObserver() {
            @Override
            public void onStart(@NotNull ServiceContentTypeDownload mDownloadDataType) {
                detailView.displayProfile(name,placeHolderImage);
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onSuccess(@NotNull ServiceContentTypeDownload mDownloadDataType) {
                detailView.displayProfile(name,((ServiceImageDownload) mDownloadDataType).getImageBitmap());
            }

            @Override
            public void onFailure(@NotNull ServiceContentTypeDownload mDownloadDataType, int statusCode, @NotNull byte[] errorResponse, @NotNull Throwable e) {
                detailView.displayProfile(name,placeHolderImage);
            }

            @Override
            public void onRetry(@NotNull ServiceContentTypeDownload mDownloadDataType, int retryNo) {

            }
        });
        mProvider.getRequest(serviceImageDownload);


    }

    @Override
    public void setDetailView(DetailView detailView) {
        this.detailView = detailView;
    }
}
