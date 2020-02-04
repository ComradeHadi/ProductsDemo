package com.hadi.apptemplate.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.hadi.apptemplate.R;
import com.hadi.apptemplate.data.CategoryProductsResponse;
import com.hadi.apptemplate.data.ProductsService;
import com.hadi.apptemplate.di.Injector;
import com.hadi.apptemplate.di.ScreenInjector;
import com.hadi.apptemplate.model.CategoryProducts;
import com.hadi.apptemplate.ui.ScreenNavigator;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity extends AppCompatActivity {
    private String instanceId;

    private Router router;

    @Inject
    ScreenInjector screenInjector;

    @Inject
    ScreenNavigator screenNavigator;

    private static String INSTANCE_ID_KEY = "instance_id";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {



        if(savedInstanceState != null){
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        }
        else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        setContentView(layoutRes());

        ViewGroup screenContainer = findViewById(R.id.screen_container);

        if(screenContainer == null){
            throw new NullPointerException("The activity needs a view with id screen_container");

        }


        super.onCreate(savedInstanceState);

        router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
        screenNavigator.initialiseWithRouter(router, initialController());

        backstackOperation();
    }



    @LayoutRes
    protected abstract int layoutRes();

    protected abstract Controller initialController();

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(INSTANCE_ID_KEY, instanceId);
    }

    public String getInstanceId(){
        return instanceId;
    }


    @Override
    public void onBackPressed() {
       if(!screenNavigator.pop()){
           super.onBackPressed();
       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        screenNavigator.clear();
        if(isFinishing()){

            Injector.clearComponent(this);

        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }

    private void backstackOperation() {

        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(@Nullable Controller to, @Nullable Controller from, boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(@Nullable Controller to, @Nullable Controller from, boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {

                if(!isPush && from !=null){

                    Injector.inject(from);

                }
            }
        });
    }
}
