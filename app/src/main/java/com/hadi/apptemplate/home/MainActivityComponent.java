package com.hadi.apptemplate.home;


import com.hadi.apptemplate.di.ActivityScope;
import com.hadi.apptemplate.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {MainScreenBindingModule.class,
        NavigationModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
