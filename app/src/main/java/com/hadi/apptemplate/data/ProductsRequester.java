package com.hadi.apptemplate.data;

import android.util.Log;

import com.hadi.apptemplate.model.Category;
import com.hadi.apptemplate.model.CategoryProducts;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ProductsRequester implements Runnable {

    private final ProductsService service;

    @Inject
    ProductsRequester(ProductsService service){
        this.service = service;
    }

//    public Single<List<CategoryProducts>> getRemoteCategoryProducts(){
//
//
//
//
//        Single<List<CategoryProducts>> products  =
//
//        //service.getRemoteProductsData().onResponse(Call<List<Category>>);
//               // .map(CategoryProductsResponse::products)
//               // .subscribeOn(Schedulers.io());
//
//
//
////                .map((ResponseBody t) -> CategoryProductsResponse.jsonAdapter(getRemoteCategoryProducts()))
////                .subscribeOn(Schedulers.io())
////                ;
//
//
//       // products.subscribe(name -> System.out.println("onNext: " + name));
//       // return products;
//        return new Single<List<CategoryProducts>>;
//    };

    @Override
    public void run() {
        try {
            final Response<List<Category>> categoryResponse = service.getRemoteProductsDataII().execute();
            if (!categoryResponse.isSuccessful()) {
            } else {
                final List<Category> categories = categoryResponse.body();
                for(Category cat: categories){
                    Log.e("CATAMONY", cat.description());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Single<List<CategoryProducts>> getRemoteCategoryProducts(){


        return service.getRemoteProductsData()
                .map(CategoryProductsResponse::products)
                .subscribeOn(Schedulers.io());
    };

}
