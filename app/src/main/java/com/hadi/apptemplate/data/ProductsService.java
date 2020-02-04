package com.hadi.apptemplate.data;

import com.hadi.apptemplate.model.Category;
import com.hadi.apptemplate.model.CategoryProducts;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface ProductsService {

    @GET(".")
    Single<CategoryProductsResponse> getRemoteProductsData();


    @GET("/")
    Call<List<Category>> getRemoteProductsDataII();


//    @GET("/")
//    Call<List<Category>> getRemoteCategoryData();




}
