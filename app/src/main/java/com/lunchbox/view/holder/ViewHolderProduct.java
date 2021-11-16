package com.lunchbox.view.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lunchbox.App;
import com.lunchbox.activity.R;
import com.lunchbox.domain.model.ElementProductCart;
import com.lunchbox.domain.model.Product;
import com.lunchbox.domain.model.ProductCart;
import com.lunchbox.util.ImageUtil;
import com.lunchbox.view.ProductView;

/**
 * @Authot: Albert Akimov
 * @Date: 10.11.2021
 * @Description:
 */

public class ViewHolderProduct extends RecyclerView.ViewHolder {

    Product product;
    ProductView productView;
    ProductCart productCart;

    public ViewHolderProduct(@NonNull View itemView) {
        super(itemView);
        productCart = ((App) itemView.getContext().getApplicationContext()).getProductCart();
        productView = new ProductView();
        productView.setProductName(itemView.findViewById(R.id.productName));
        productView.setProductPrice(itemView.findViewById(R.id.productPrice));
        productView.setProductDescription(itemView.findViewById(R.id.productDescription));
        productView.setProductImage(itemView.findViewById(R.id.productImage));

        itemView.findViewById(R.id.addToBasket).setOnClickListener(view -> {

            ElementProductCart elem = new ElementProductCart();
            elem.setProduct(product);
            elem.setNumber(1L);

            productCart.addElement(elem);

        });
    }

    public void bind(@NonNull Product product) {
        this.product = product;
        productView.getProductName().setText(product.getName());
        productView.getProductPrice().setText((String) product.getPrice().toString());
        productView.getProductDescription().setText(product.getDescription());
        if (!product.getImage().isEmpty())
            productView.getProductImage().setImageBitmap(ImageUtil.getDecodeImage(product.getImage()));
    }
}
