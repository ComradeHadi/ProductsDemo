package com.hadi.apptemplate.products;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.apptemplate.R;
import com.hadi.apptemplate.model.CategoryProducts;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final ProductClickedListener listener;
    private final List<CategoryProducts> data = new ArrayList<>();

    ProductAdapter(ProductClickedListener listener) {
        this.listener = listener;
        setHasStableIds(true);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_list_item, parent, false);
        return new ProductViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(data.get(position).id());
    }

    void setData(List<CategoryProducts> products) {
        if (products != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ProductDiffCallback(data, products));
            data.clear();
            data.addAll(products);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    static final class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_product_name) TextView productNameText;
        @BindView(R.id.tv_product_description) TextView productDescriptionText;
        @BindView(R.id.iv_product_thumbnail) ImageView productThumbnail;

        private CategoryProducts product;

        ProductViewHolder(View itemView, ProductClickedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (product != null) {
                    listener.onRepoClicked(product);
                }
            });
        }

        void bind(CategoryProducts category_product) {
            this.product = category_product;
            productNameText.setText(category_product.name());
            productDescriptionText.setText(category_product.description());
           // productThumbnail.setImageURI(category_product.url());

        }
    }

    interface ProductClickedListener {

        void onRepoClicked(CategoryProducts repo);
    }
}
