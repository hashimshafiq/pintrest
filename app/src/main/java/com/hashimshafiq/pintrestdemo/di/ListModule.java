package com.hashimshafiq.pintrestdemo.di;

import com.hashimshafiq.pintrestdemo.models.PinListResponse;
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
}
