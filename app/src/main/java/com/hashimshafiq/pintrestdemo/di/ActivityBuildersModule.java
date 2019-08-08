package com.hashimshafiq.pintrestdemo.di;


import com.hashimshafiq.pintrestdemo.di.main.MainModule;
import com.hashimshafiq.pintrestdemo.di.main.MainScope;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
            modules = { MainModule.class})
    abstract MainModule contributeMainActivity();




}
