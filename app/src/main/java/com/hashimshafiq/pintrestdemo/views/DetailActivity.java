package com.hashimshafiq.pintrestdemo.views;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import com.hashimshafiq.pintrestdemo.implementations.DetailPresenterImplementation;
import com.hashimshafiq.pintrestdemo.interfaces.DetailPresenter;
import com.hashimshafiq.pintrestdemo.interfaces.DetailView;
import com.hashimshafiq.pintrestdemo.interfaces.PinListView;
import kotlin.BuilderInference;

public class DetailActivity extends AppCompatActivity implements DetailView {

    DetailPresenter detailPresenter;

    @BindView(R.id.image)
    ImageView mPinImage;

    @BindView(R.id.profile_image)
    ImageView mProfileImage;

    @BindView(R.id.nameText)
    TextView mNameText;

    String name,profile,pinImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        detailPresenter = new DetailPresenterImplementation(this,getApplicationContext());


        if(getIntent().hasExtra("name") && getIntent().hasExtra("profile") && getIntent().hasExtra("pinImage")){
            name = getIntent().getStringExtra("name");
            profile = getIntent().getStringExtra("profile");
            pinImage = getIntent().getStringExtra("pinImage");


        }

        detailPresenter.fetchPinImage(pinImage);
        detailPresenter.fetchProfile(name,profile);


    }

    @OnClick(R.id.back) void onClickBack(){
        finish();
    }



    @Override
    public void displayPinImage(Bitmap image) {
        mPinImage.setImageBitmap(image);
    }

    @Override
    public void displayPinImage(int image) {
        mPinImage.setImageResource(image);
    }

    @Override
    public void displayProfile(String name, Bitmap image) {
        mProfileImage.setImageBitmap(image);
        mNameText.setText(name);
    }

    @Override
    public void displayProfile(String name, int image) {
        mProfileImage.setImageResource(image);
        mNameText.setText(name);
    }
}
