package com.hashimshafiq.pintrestdemo.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver;
import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload;
import com.hashimshafiq.asyncimageloader.models.ServiceImageDownload;
import com.hashimshafiq.asyncimageloader.utilities.ContentTypeServiceDownload;
import com.hashimshafiq.pintrestdemo.R;
import com.hashimshafiq.pintrestdemo.listeners.PinClickListerner;
import com.hashimshafiq.pintrestdemo.models.PinListResponse;
import com.hashimshafiq.pintrestdemo.viewholders.PinViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PintrestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ContentTypeServiceDownload mProvider = null;
    private List<PinListResponse> mList;
    private PinClickListerner mPinClickListener;

    public PintrestAdapter(List<PinListResponse> list,PinClickListerner pinClickListerner ){
        mList = list;
        mProvider = ContentTypeServiceDownload.Companion.getInstance();
        this.mPinClickListener = pinClickListerner;
    }

    public PintrestAdapter(){
        mProvider = ContentTypeServiceDownload.Companion.getInstance();
        mPinClickListener = null;
        mList = new ArrayList<>();
    }

    public void setPinClickListener(PinClickListerner listener){
        this.mPinClickListener = listener;
    }

    public void setList(List<PinListResponse> list){
        this.mList = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pin_item, parent, false);
        return new PinViewHolder(layoutView,mPinClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PinListResponse response = mList.get(position);
        if(response==null)
            return;


        ServiceContentTypeDownload mDataTypeImageCancel = new ServiceImageDownload(response.getImageUrlsResponse().getRegular(), new ContentServiceObserver()
        {
            @Override
            public void onStart(ServiceContentTypeDownload mDownloadDataType)
            {
                ((PinViewHolder)holder).mImageView.setImageResource(R.drawable.place_holder);
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onSuccess(ServiceContentTypeDownload mDownloadDataType)
            {
                ((PinViewHolder)holder).mImageView.setImageBitmap(((ServiceImageDownload) mDownloadDataType).getImageBitmap());
                ((PinViewHolder)holder).mLikesText.setText(String.format("%d Likes", response.getLikes()));
                if(response.isLikedByUser()){
                    ((PinViewHolder) holder).mHeartImage.setImageResource(R.drawable.ic_heart);
                }else {
                    ((PinViewHolder)holder).mHeartImage.setImageResource(R.drawable.ic_heart_not_liked);
                }
            }

            @Override
            public void onFailure(ServiceContentTypeDownload mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e)
            {
                ((PinViewHolder)holder).mImageView.setImageResource(R.drawable.place_holder);
            }

            @Override
            public void onRetry(ServiceContentTypeDownload mDownloadDataType, int retryNo)
            {

            }
        });
        mProvider.getRequest(mDataTypeImageCancel);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
}
