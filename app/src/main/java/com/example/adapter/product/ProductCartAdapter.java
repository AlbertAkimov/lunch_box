package com.example.adapter.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.lunchbox.R;
import com.example.model.ProductCart;
import com.example.util.ImageUtil;
import com.example.view.product.ProductCartView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * @Authot: Albert Akimov
 * @Date: 13.09.2021
 * @Description:
 */
@Getter
@Setter
public class ProductCartAdapter extends ArrayAdapter<ProductCart> {

    private final Context mContext;
    private final int mResource;

    public ProductCartAdapter(@NonNull Context context, int resource, @NonNull List<ProductCart> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ProductCartView productCartView;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            productCartView = new ProductCartView();
            productCartView.setName(convertView.findViewById(R.id.nameProduct_shopping_cart));
            productCartView.setPrice(convertView.findViewById(R.id.price_shopping_cart));
            productCartView.setImage(convertView.findViewById(R.id.image_shopping_cart));
            productCartView.setNumber(convertView.findViewById(R.id.number_shopping_cart));

            convertView.setTag(productCartView);
        }
        else{
            productCartView = (ProductCartView) convertView.getTag();
        }

        productCartView.getImage().setImageBitmap(ImageUtil.getDecodeImage(
                Objects.requireNonNull(getItem(position)).getProduct().getProductImage()));

        productCartView.getName().setText(Objects.requireNonNull(getItem(position)).getProduct().getProductName());
        productCartView.getPrice().setText((String) Objects.requireNonNull(getItem(position)).getProduct().getProductPrice().toString());
        productCartView.getNumber().setText((String) Objects.requireNonNull(getItem(position)).getNumber().toString());

        return convertView;
    }
}
