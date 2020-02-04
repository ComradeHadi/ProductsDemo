package com.hadi.apptemplate.products;

import com.hadi.apptemplate.di.ScreenScope;

import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface ProductsComponent extends AndroidInjector<ProductsController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProductsController>{

    }
}
