package com.hashimshafiq.pintrestdemo.di;

import android.app.Application;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.hashimshafiq.pintrestdemo.R;
import com.hashimshafiq.pintrestdemo.adapters.PintrestAdapter;
import com.hashimshafiq.pintrestdemo.implementations.PinListPresenterImplementation;
import com.hashimshafiq.pintrestdemo.interfaces.PinListPresenter;
import com.hashimshafiq.pintrestdemo.models.PinListResponse;
import com.hashimshafiq.pintrestdemo.utilities.SpacesItemDecoration;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Module
public class ListModule {


    @Provides
    @Singleton
    List<PinListResponse> provideList(){
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    PintrestAdapter provideAdapter(){
        return new PintrestAdapter();
    }


    @Provides
    @Singleton
    StaggeredGridLayoutManager provideStaggeredLayoutManager(){
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }


    @Provides
    @Singleton
    SpacesItemDecoration provideSpaceItemDecoration(){
        return new SpacesItemDecoration(16);
    }

    @Provides
    @Singleton
    PinListPresenter providePinListPresenter(Application application){
        return new PinListPresenterImplementation(application);
    }

    @Provides
    @Singleton
    Drawable providePlaceHolderImage(Application application){
        return ContextCompat.getDrawable(application, R.drawable.place_holder);
    }
}
