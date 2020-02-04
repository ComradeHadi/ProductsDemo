package com.hadi.apptemplate.home;

import com.bluelinelabs.conductor.Controller;
import com.hadi.apptemplate.R;
import com.hadi.apptemplate.base.BaseActivity;
import com.hadi.apptemplate.products.ProductsController;


public class MainActivity extends BaseActivity {


    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialController() {
        return new ProductsController();
    }
}
