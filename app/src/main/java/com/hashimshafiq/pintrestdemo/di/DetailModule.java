package com.hashimshafiq.pintrestdemo.di;

import android.app.Application;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.hashimshafiq.pintrestdemo.R;
import com.hashimshafiq.pintrestdemo.implementations.DetailPresenterImplementation;
import com.hashimshafiq.pintrestdemo.interfaces.DetailPresenter;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Module
public class DetailModule {

    @Provides
    @Singleton
    Drawable providePlaceHolderImage(Application application){
        return ContextCompat.getDrawable(application, R.drawable.place_holder);
    }


    @Provides
    @Singleton
    DetailPresenter provideDetailPresenter(Application application){
        return new DetailPresenterImplementation(application);
    }

    @Provides
    @Singleton
    List<String> provideStringList(){
        return new ArrayList<>();
    }

}
