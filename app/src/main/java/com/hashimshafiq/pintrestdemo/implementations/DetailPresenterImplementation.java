package com.hashimshafiq.pintrestdemo.implementations;

import android.annotation.SuppressLint;
import android.content.Context;
import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver;
import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload;
import com.hashimshafiq.asyncimageloader.models.ServiceImageDownload;
import com.hashimshafiq.asyncimageloader.utilities.ContentTypeServiceDownload;
import com.hashimshafiq.pintrestdemo.R;
import com.hashimshafiq.pintrestdemo.interfaces.DetailPresenter;
import com.hashimshafiq.pintrestdemo.interfaces.DetailView;

public class DetailPresenterImplementation implements DetailPresenter {

    private DetailView detailView;
    private Context context;
    private ContentTypeServiceDownload mProvider;

    public DetailPresenterImplementation(DetailView detailView, Context context) {
        this.detailView = detailView;
        this.context = context;
        this.mProvider = ContentTypeServiceDownload.Companion.getInstance();
    }

    @Override
    public void fetchPinImage(String imageURL) {

            ServiceContentTypeDownload mDataTypeImageCancel = new ServiceImageDownload(imageURL, new ContentServiceObserver() {
                @Override
                public void onStart(ServiceContentTypeDownload mDownloadDataType) {
                    detailView.displayPinImage(R.drawable.place_holder);
                }

                @SuppressLint("DefaultLocale")
                @Override
                public void onSuccess(ServiceContentTypeDownload mDownloadDataType) {
                    detailView.displayPinImage(((ServiceImageDownload) mDownloadDataType).getImageBitmap());
                }

                @Override
                public void onFailure(ServiceContentTypeDownload mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e) {
                    detailView.displayPinImage(R.drawable.place_holder);
                }

                @Override
                public void onRetry(ServiceContentTypeDownload mDownloadDataType, int retryNo) {

                }
            });
            mProvider.getRequest(mDataTypeImageCancel);




    }

    @Override
    public void fetchProfile(String name, String imageURL) {
        ServiceContentTypeDownload mDataTypeImageCancel = new ServiceImageDownload(imageURL, new ContentServiceObserver() {
            @Override
            public void onStart(ServiceContentTypeDownload mDownloadDataType) {
                detailView.displayProfile(name,R.drawable.place_holder);
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onSuccess(ServiceContentTypeDownload mDownloadDataType) {
                detailView.displayProfile(name,((ServiceImageDownload) mDownloadDataType).getImageBitmap());
            }

            @Override
            public void onFailure(ServiceContentTypeDownload mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e) {
                detailView.displayProfile(name,R.drawable.place_holder);
            }

            @Override
            public void onRetry(ServiceContentTypeDownload mDownloadDataType, int retryNo) {

            }
        });
        mProvider.getRequest(mDataTypeImageCancel);


    }
}
