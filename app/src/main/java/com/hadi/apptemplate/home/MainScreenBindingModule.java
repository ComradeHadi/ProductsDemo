package com.hadi.apptemplate.home;

import com.bluelinelabs.conductor.Controller;
import com.hadi.apptemplate.di.ControllerKey;
import com.hadi.apptemplate.products.ProductsComponent;
import com.hadi.apptemplate.products.ProductsController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ProductsComponent.class})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(ProductsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindCategoryProductsInjector(ProductsComponent.Builder builder);
}
