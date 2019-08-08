package com.hashimshafiq.pintrestdemo.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hashimshafiq.pintrestdemo.R;
import com.hashimshafiq.pintrestdemo.listeners.PinClickListener;

public class PinViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img)
    public ImageView mImageView;

    @BindView(R.id.likes)
    public TextView mLikesText;

    @BindView(R.id.heart)
    public ImageView mHeartImage;

    private PinClickListener mPinClickListener;

    public PinViewHolder(@NonNull View itemView, PinClickListener pinClickListener) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mPinClickListener = pinClickListener;

    }

    @OnClick(R.id.img)
    void onClickPin(){
        mPinClickListener.onClickPin(getAdapterPosition());
    }

    @OnClick(R.id.heart)
    void onClickHeart(){
        mPinClickListener.onClickHeartIcon(getAdapterPosition());
    }


}
