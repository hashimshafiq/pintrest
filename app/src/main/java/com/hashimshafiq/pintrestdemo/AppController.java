package com.hashimshafiq.pintrestdemo;

import android.app.Application;
import com.hashimshafiq.pintrestdemo.di.DaggerListComponent;
import com.hashimshafiq.pintrestdemo.di.DetailModule;
import com.hashimshafiq.pintrestdemo.di.ListModule;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

import javax.inject.Inject;

public class AppController extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;



    @Override
    public void onCreate() {
        super.onCreate();
        DaggerListComponent.builder().application(this).listModule(new ListModule()).detailModule(new DetailModule()).build().inject(this);

    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
