package com.hashimshafiq.pintrestdemo.di.main;


import com.hashimshafiq.pintrestdemo.adapters.PintrestAdapter;
import com.hashimshafiq.pintrestdemo.listeners.PinClickListerner;
import com.hashimshafiq.pintrestdemo.models.PinListResponse;
import dagger.Module;
import dagger.Provides;

import java.util.ArrayList;
import java.util.List;

@Module
public class MainModule {

    @MainScope
    @Provides
    static PintrestAdapter providesAdapter(List<PinListResponse> responses, PinClickListerner listerner){
        return new PintrestAdapter(responses,listerner);
    }

    @MainScope
    @Provides
    static List<PinListResponse> providesList(){
        return new ArrayList<>();
    }

}
