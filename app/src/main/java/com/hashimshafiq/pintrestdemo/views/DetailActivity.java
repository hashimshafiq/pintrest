package com.hashimshafiq.pintrestdemo.views;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hashimshafiq.pintrestdemo.R;
import com.hashimshafiq.pintrestdemo.interfaces.DetailPresenter;
import com.hashimshafiq.pintrestdemo.interfaces.DetailView;
import com.hashimshafiq.pintrestdemo.models.ImageUrlsResponse;
import com.hashimshafiq.pintrestdemo.models.UserResponse;
import com.hashimshafiq.pintrestdemo.utilities.connection_monitor.NetworkSensingActivity;
import dagger.android.AndroidInjection;

import javax.inject.Inject;
import java.util.List;

public class DetailActivity extends NetworkSensingActivity implements DetailView {

    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.image)
    ImageView mPinImage;

    @BindView(R.id.profile_image)
    ImageView mProfileImage;

    @BindView(R.id.nameText)
    TextView mNameText;

    UserResponse user = null;
    ImageUrlsResponse pinImages= null;

    @Inject
    List<String> pinImagesList;

    @Inject
    List<String> profileImagesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        AndroidInjection.inject(this);
        detailPresenter.setDetailView(this);
        if(getIntent().hasExtra("user") && getIntent().hasExtra("image")){
            user = getIntent().getParcelableExtra("user");
            pinImages = getIntent().getParcelableExtra("image");
        }

        if(user==null || pinImages==null){
            return;
        }



        convertPinImagesToList(pinImages);
        convertUserImagesToList(user);

        detailPresenter.fetchPinImage(pinImagesList.get(0));
        detailPresenter.fetchPinImage(pinImagesList.get(1));
        detailPresenter.fetchPinImage(pinImagesList.get(2));
        detailPresenter.fetchPinImage(pinImagesList.get(3));
        detailPresenter.fetchProfile(user.getName(),profileImagesList.get(0));
        detailPresenter.fetchProfile(user.getName(),profileImagesList.get(1));
        detailPresenter.fetchProfile(user.getName(),profileImagesList.get(2));
    }

    private void convertUserImagesToList(UserResponse userResponse){
        profileImagesList.add(userResponse.getProfileImageResponse().getSmall());
        profileImagesList.add(userResponse.getProfileImageResponse().getMedium());
        profileImagesList.add(userResponse.getProfileImageResponse().getLarge());
    }

    private void convertPinImagesToList(ImageUrlsResponse imageUrlsResponse){
        pinImagesList.add(imageUrlsResponse.getThumb());
        pinImagesList.add(imageUrlsResponse.getSmall());
        pinImagesList.add(imageUrlsResponse.getRegular());
        pinImagesList.add(imageUrlsResponse.getFull());
    }

    @OnClick(R.id.back) void onClickBack(){
        finish();
    }


    @Override
    public void displayPinImage(Object image) {
        if(image instanceof Drawable)
            mPinImage.setImageDrawable((Drawable) image);
        else if(image instanceof Bitmap)
            mPinImage.setImageBitmap((Bitmap) image);
    }

    @Override
    public void displayProfile(String name, Object image) {
        if(image instanceof Drawable)
            mProfileImage.setImageDrawable((Drawable) image);
        else if(image instanceof Bitmap)
            mProfileImage.setImageBitmap((Bitmap) image);

        mNameText.setText(name);
    }


}
