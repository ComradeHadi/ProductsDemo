package com.hadi.apptemplate.data;

import com.google.auto.value.AutoValue;
import com.hadi.apptemplate.model.CategoryProducts;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class CategoryProductsResponse {

    @Json(name = "products")
    public abstract List<CategoryProducts> products();

    public static JsonAdapter<CategoryProductsResponse> jsonAdapter(Moshi moshi){
        return new AutoValue_CategoryProductsResponse.MoshiJsonAdapter(moshi);
    }

}
