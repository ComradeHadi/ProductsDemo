package com.hadi.apptemplate.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class CategoryProducts {


    @Json(name = "salePrice")
    public abstract CategoryProductsSalePrice salePrice();

    public abstract String name();

    public abstract String description();

    public abstract String id();

    public abstract String categoryId();

    public abstract String url();

    public static JsonAdapter<CategoryProducts> jsonAdapter(Moshi moshi) {
        return new AutoValue_CategoryProducts.MoshiJsonAdapter(moshi);
    }



}
