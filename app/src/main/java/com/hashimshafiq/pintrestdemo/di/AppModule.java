package com.hashimshafiq.pintrestdemo.di;

import android.app.Application;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.hashimshafiq.pintrestdemo.R;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {


    @Provides
    static Drawable providesHeart(Application application){
        return ContextCompat.getDrawable(application,R.drawable.ic_heart);
    }




}
