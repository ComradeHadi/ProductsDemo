package com.hadi.apptemplate.products;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.apptemplate.R;
import com.hadi.apptemplate.base.BaseController;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ProductsController extends BaseController {

    @Inject ProductsPresenter productsPresenter;
    @Inject ProductsViewModel productsViewModel;


    @BindView(R.id.repo_list) RecyclerView repoList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.erro_tv) TextView errorText;


    @Override
    protected void onViewBound(View view) {
        repoList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        repoList.setAdapter(new ProductAdapter(productsPresenter));
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                productsViewModel.loading()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    repoList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),

                productsViewModel.products()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(((ProductAdapter)repoList.getAdapter())::setData),

                productsViewModel.error()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(erroRes->{
                    if(erroRes == -1){
                        errorText.setText(null);
                        errorText.setVisibility(View.GONE);

                    }

                    else {
                        errorText.setVisibility(View.VISIBLE);
                        repoList.setVisibility(View.GONE);
                        errorText.setText(erroRes);
                    }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_repos;
    }
}
