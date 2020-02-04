package com.hadi.apptemplate.networking;


import com.hadi.apptemplate.model.AdapterFactory;
import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = NetworkModule.class)
public abstract class ServiceModule {

    @Provides
    @Singleton
    static Moshi provideMoshi(){
         return new Moshi.Builder()
                 .add(AdapterFactory.create())
                 .build();
    }


    @Provides
    @Singleton
    static Retrofit provideRetrofit(Moshi moshi, Call.Factory callFactory, @Named("base_url") String baseUrl){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        return new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .callFactory(callFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .build();

    }
}
