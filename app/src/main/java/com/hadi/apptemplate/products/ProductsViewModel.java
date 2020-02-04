package com.hadi.apptemplate.products;

import android.util.Log;

import com.hadi.apptemplate.R;
import com.hadi.apptemplate.di.ScreenScope;
import com.hadi.apptemplate.model.CategoryProducts;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

@ScreenScope
class ProductsViewModel {
    private final BehaviorRelay<List<CategoryProducts>> categoryProductsRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    ProductsViewModel(){


    }

    //Exposing the BehaviourRelays to the views as observables

    Observable<List<CategoryProducts>> products(){
        return categoryProductsRelay;
    }

    Observable<Integer> error(){
        return errorRelay;
    }

    Observable<Boolean> loading(){
        return loadingRelay;
    }



    //Passing on data to our presenters

    Consumer<List<CategoryProducts>> productsUpdated(){
        errorRelay.accept(-1);
        Timber.e("LOGGG", "Loaded");
       // Log.e("UPDATED", categoryProductsRelay.toString());
        return categoryProductsRelay;

    }

    Consumer<Boolean> loadingUpdated(){

       // Log.e("LOADING", categoryProductsRelay.toString());
        Timber.e("LOGGG", "Loading");
        return loadingRelay;

    }

    Consumer<Throwable> onError(){

       // Log.e("ERROR", categoryProductsRelay.toString());
        return throwable -> {

            Timber.e(throwable, "Error loading products");
            errorRelay.accept(R.string.relay_error_int);

        };
    }
}
