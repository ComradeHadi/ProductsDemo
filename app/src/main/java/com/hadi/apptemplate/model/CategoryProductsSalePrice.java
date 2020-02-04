package com.hadi.apptemplate.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class CategoryProductsSalePrice {

    public abstract String amount();
    public abstract String currency();

    public static JsonAdapter<CategoryProductsSalePrice> jsonAdapter(Moshi moshi) {
        return new AutoValue_CategoryProductsSalePrice.MoshiJsonAdapter(moshi);
    }


}
