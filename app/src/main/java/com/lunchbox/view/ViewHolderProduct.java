package com.lunchbox.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lunchbox.domain.model.Product;
import com.lunchbox.activity.R;
import com.lunchbox.util.ImageUtil;
import com.lunchbox.view.product.ProductView;

/**
 * @Authot: Albert Akimov
 * @Date: 10.11.2021
 * @Description:
 */

public class ViewHolderProduct extends RecyclerView.ViewHolder {

    Product product;
    ProductView productView;

    public ViewHolderProduct(@NonNull View itemView) {
        super(itemView);
        productView = new ProductView();
        productView.setProductName(itemView.findViewById(R.id.productName));
        productView.setProductPrice(itemView.findViewById(R.id.productPrice));
        productView.setProductDescription(itemView.findViewById(R.id.productDescription));
        productView.setProductImage(itemView.findViewById(R.id.productImage));
    }

    public void bind(@NonNull Product product) {
        this.product = product;
        productView.getProductName().setText(product.getProductName());
        productView.getProductPrice().setText((String) product.getProductPrice().toString());
        productView.getProductDescription().setText(product.getProductDescription());
        if (!product.getProductImage().isEmpty())
            productView.getProductImage().setImageBitmap(ImageUtil.getDecodeImage(product.getProductImage()));
    }
}
