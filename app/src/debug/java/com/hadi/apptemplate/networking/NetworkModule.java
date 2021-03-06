package com.hadi.apptemplate.networking;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;

@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Call.Factory provideOkHttP(){
        return new OkHttpClient.Builder().build();
    }


    @Provides
    @Named("base_url")
    static String provideBaseUrl(){
        return "http://mobcategories.s3-website-eu-west-1.amazonaws.com";
    }
}
