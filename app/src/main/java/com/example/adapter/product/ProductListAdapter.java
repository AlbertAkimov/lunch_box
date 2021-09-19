package com.example.adapter.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.App;
import com.example.lunchbox.R;
import com.example.model.Product;
import com.example.model.ProductCart;
import com.example.util.ImageUtil;
import com.example.view.product.ProductView;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Authot: Albert Akimov
 * @Date: 04.09.2021
 * @Description:
 */

@Getter
@Setter
public class ProductListAdapter extends ArrayAdapter<Product> {

    private final Context mContext;
    private final int mResource;
    private List<ProductCart> basketList;

    public ProductListAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects, List<ProductCart> cart) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        basketList = cart;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ProductView productView;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            productView = new ProductView();
            productView.setProductName(convertView.findViewById(R.id.productName));
            productView.setProductDescription(convertView.findViewById(R.id.productDescription));
            productView.setProductPrice(convertView.findViewById(R.id.productPrice));
            productView.setProductImage(convertView.findViewById(R.id.productImage));

            convertView.setTag(productView);
        }
        else{
            productView = (ProductView) convertView.getTag();
        }

        productView.getProductImage().setImageBitmap(ImageUtil.getDecodeImage(Objects.requireNonNull(getItem(position)).getProductImage()));

        productView.getProductName().setText(Objects.requireNonNull(getItem(position)).getProductName());
        productView.getProductDescription().setText(Objects.requireNonNull(getItem(position)).getProductDescription());
        productView.getProductPrice().setText((String) Objects.requireNonNull(getItem(position)).getProductPrice().toString());

        ImageButton buttonAddToBasket = convertView.findViewById(R.id.addToBasket);
        buttonAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                basketList.add(
                        new ProductCart(ProductListAdapter.this.getItem(position), 1L));
            }
        });

        return convertView;
    }
}
