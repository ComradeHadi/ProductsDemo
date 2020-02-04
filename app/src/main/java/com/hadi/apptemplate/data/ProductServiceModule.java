package com.hadi.apptemplate.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class ProductServiceModule {


    @Provides
    @Singleton
    static ProductsService provideRepoService(Retrofit retrofit){
        return retrofit.create(ProductsService.class);
    }
}
