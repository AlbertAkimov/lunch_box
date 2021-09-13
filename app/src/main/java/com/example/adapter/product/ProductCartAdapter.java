package com.example.adapter.product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.lunchbox.R;
import com.example.model.ProductCart;
import com.example.view.product.ProductCartView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

        String name = getItem(position).getProduct().getName();
        Long price = getItem(position).getProduct().getPrice();
        Long number = getItem(position).getNumber();

        byte[] decodedString  = Base64.decode(getItem(position).getProduct().getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

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

        productCartView.getImage().setImageBitmap(decodedByte);

        productCartView.getName().setText(name);
        productCartView.getPrice().setText(price.toString());
        productCartView.getNumber().setText(number.toString());

        return convertView;
    }
}
