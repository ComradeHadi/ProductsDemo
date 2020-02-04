package com.hadi.apptemplate.products;


import com.hadi.apptemplate.data.ProductsRequester;
import com.hadi.apptemplate.di.ScreenScope;
import com.hadi.apptemplate.model.CategoryProducts;


import javax.inject.Inject;

@ScreenScope
class ProductsPresenter implements ProductAdapter.ProductClickedListener {

    private final ProductsViewModel viewModel;
    private final ProductsRequester productsRequester;

    @Inject
    public ProductsPresenter(ProductsViewModel viewModel, ProductsRequester productsRequester) {
        this.viewModel = viewModel;
        this.productsRequester = productsRequester;

        loadProducts();
    }

    private void loadProducts() {

        productsRequester.getRemoteCategoryProducts()
                .doOnSubscribe(__->viewModel.loadingUpdated().accept(true))
                .doOnEvent((d,t)->viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.productsUpdated(), viewModel.onError());
    }

    @Override
    public void onRepoClicked(CategoryProducts categoryProducts) {

    }
}
