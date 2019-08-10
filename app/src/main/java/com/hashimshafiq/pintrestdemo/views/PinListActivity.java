package com.hashimshafiq.pintrestdemo.views;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.hashimshafiq.pintrestdemo.R;
import com.hashimshafiq.pintrestdemo.adapters.PintrestAdapter;
import com.hashimshafiq.pintrestdemo.interfaces.PinListPresenter;
import com.hashimshafiq.pintrestdemo.interfaces.PinListView;
import com.hashimshafiq.pintrestdemo.listeners.PinClickListener;
import com.hashimshafiq.pintrestdemo.models.ImageUrlsResponse;
import com.hashimshafiq.pintrestdemo.models.PinListResponse;
import com.hashimshafiq.pintrestdemo.models.UserResponse;
import com.hashimshafiq.pintrestdemo.utilities.SpacesItemDecoration;
import com.hashimshafiq.pintrestdemo.utilities.connection_monitor.NetworkSensingActivity;
import dagger.android.AndroidInjection;

import javax.inject.Inject;
import java.util.List;

public class PinListActivity extends NetworkSensingActivity implements PinClickListener, PinListView {

    @BindView(R.id.listView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    @Inject
    List<PinListResponse> mList;

    @Inject
    PintrestAdapter adapter;

    @Inject
    StaggeredGridLayoutManager manager;

    @Inject
    SpacesItemDecoration decoration;

    @Inject
    PinListPresenter pinListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_list);
        ButterKnife.bind(this);
        AndroidInjection.inject(this);
        mProgressBar.setVisibility(View.VISIBLE);
        adapter.setPinClickListener(this);
        pinListPresenter.setListingView(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(decoration);
        mProgressBar.setVisibility(View.VISIBLE);
        pinListPresenter.fetchPins();
    }


    @Override
    public void onClickPin(int position) {
        ImageUrlsResponse imageUrlsResponse = mList.get(position).getImageUrlsResponse();
        UserResponse userResponse = mList.get(position).getUser();
        Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
        intent.putExtra("user",userResponse);
        intent.putExtra("image",imageUrlsResponse);
        startActivity(intent);
    }

    @Override
    public void onClickHeartIcon(int position) {

    }

    @Override
    public void displayPins(List<PinListResponse> pinListResponses) {
        mList.addAll(pinListResponses);
        adapter.setList(mList);
        adapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
