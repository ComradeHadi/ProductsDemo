package com.hadi.apptemplate.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class Category {


    public abstract String name();

    public abstract String description();

    public abstract String id();

    @Json(name = "products")
    public abstract List<CategoryProducts> products();


    public static JsonAdapter<Category> jsonAdapter(Moshi moshi) {
        return new AutoValue_Category.MoshiJsonAdapter(moshi);
    }



}
