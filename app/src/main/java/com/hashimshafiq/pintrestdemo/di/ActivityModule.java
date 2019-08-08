package com.hashimshafiq.pintrestdemo.di;

import com.hashimshafiq.pintrestdemo.views.DetailActivity;
import com.hashimshafiq.pintrestdemo.views.PinListActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract PinListActivity contributePinListActivity();

    @ContributesAndroidInjector()
    abstract DetailActivity contributeDetailActivity();

}
