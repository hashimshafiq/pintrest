package com.hashimshafiq.pintrestdemo.views;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver;
import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload;
import com.hashimshafiq.asyncimageloader.models.ServiceImageDownload;
import com.hashimshafiq.asyncimageloader.utilities.ContentTypeServiceDownload;
import com.hashimshafiq.pintrestdemo.R;
import kotlin.BuilderInference;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView mPinImage;

    @BindView(R.id.profile_image)
    ImageView mProfileImage;

    @BindView(R.id.nameText)
    TextView mNameText;

    String name,profile,pinImage;
    private ContentTypeServiceDownload mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mProvider = ContentTypeServiceDownload.Companion.getInstance();

        if(getIntent().hasExtra("name") && getIntent().hasExtra("profile") && getIntent().hasExtra("pinImage")){
            name = getIntent().getStringExtra("name");
            profile = getIntent().getStringExtra("profile");
            pinImage = getIntent().getStringExtra("pinImage");

            bindViews();
        }
    }

    @OnClick(R.id.back) void onClickBack(){
        finish();
    }

    private void bindViews() {
        mNameText.setText(name);

        ServiceContentTypeDownload mDataTypeImageCancel = new ServiceImageDownload(pinImage, new ContentServiceObserver()
        {
            @Override
            public void onStart(ServiceContentTypeDownload mDownloadDataType)
            {
                mPinImage.setImageResource(R.drawable.place_holder);
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onSuccess(ServiceContentTypeDownload mDownloadDataType)
            {
                mPinImage.setImageBitmap(((ServiceImageDownload) mDownloadDataType).getImageBitmap());
            }

            @Override
            public void onFailure(ServiceContentTypeDownload mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e)
            {
                mPinImage.setImageResource(R.drawable.place_holder);
            }

            @Override
            public void onRetry(ServiceContentTypeDownload mDownloadDataType, int retryNo)
            {

            }
        });
        mProvider.getRequest(mDataTypeImageCancel);

        ServiceContentTypeDownload mDataTypeImageCancel1 = new ServiceImageDownload(profile, new ContentServiceObserver()
        {
            @Override
            public void onStart(ServiceContentTypeDownload mDownloadDataType)
            {

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onSuccess(ServiceContentTypeDownload mDownloadDataType)
            {
                mProfileImage.setImageBitmap(((ServiceImageDownload) mDownloadDataType).getImageBitmap());
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
        mProvider.getRequest(mDataTypeImageCancel1);

    }
}
