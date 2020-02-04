package com.hadi.apptemplate.data;

import com.hadi.apptemplate.model.Category;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class CategoryProductsRequester implements Runnable {

  private final EventBus eventBus;
  private final ProductsService productService;

  @Inject
  public CategoryProductsRequester(final ProductsService productService, final EventBus eventBus) {
    this.productService = productService;
    this.eventBus = eventBus;
  }

  @Override
  public void run() {
//    try {
//      final Response<List<Category>> categoryResponse = productService.getRemoteCategoryData().execute();
//      if (!categoryResponse.isSuccessful()) {
//        eventBus.postSticky(new CategoryProductDownloadError());
//      } else {
//        final List<Category> categories = categoryResponse.body();
//        eventBus.postSticky(categories == null ? Collections.<Category>emptyList() : categories);
//      }
//
//    } catch (IOException e) {
//      e.printStackTrace();
//      eventBus.postSticky(new CategoryProductDownloadError());
//    }
  }
}
