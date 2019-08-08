package com.hashimshafiq.pintrestdemo.di;


import android.app.Application;
import com.hashimshafiq.pintrestdemo.AppController;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityModule.class,ListModule.class

})
public interface ListComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder listModule(ListModule listModule);



        ListComponent build();
    }

    void inject(AppController appController);
}
