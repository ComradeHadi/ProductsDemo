package com.hadi.apptemplate.products;



import androidx.recyclerview.widget.DiffUtil;

import com.hadi.apptemplate.model.CategoryProducts;


import java.util.List;



public class ProductDiffCallback extends DiffUtil.Callback {

    private final List<CategoryProducts> oldList;
    private final List<CategoryProducts> newList;

    public ProductDiffCallback(List<CategoryProducts> oldList, List<CategoryProducts> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id() == newList.get(newItemPosition).id();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
