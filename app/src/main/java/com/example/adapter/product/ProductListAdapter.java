package com.example.adapter.product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.lunchbox.R;
import com.example.model.Product;
import com.example.model.ProductCart;
import com.example.view.product.ProductView;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private List<ProductCart> basketList = new ArrayList<>();

    public ProductListAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getName();
        String description = getItem(position).getDescription();
        Long price = getItem(position).getPrice();
        String image = getItem(position).getImage();
        Long id = getItem(position).getId();

        byte[] decodedString  = Base64.decode(getItem(position).getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        ProductView productView;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            productView = new ProductView();
            productView.setName(convertView.findViewById(R.id.nameProduct));
            productView.setDescription(convertView.findViewById(R.id.description));
            productView.setPrice(convertView.findViewById(R.id.price));
            productView.setImage(convertView.findViewById(R.id.image));

            convertView.setTag(productView);
        }
        else{
            productView = (ProductView) convertView.getTag();
        }

        productView.getImage().setImageBitmap(decodedByte);

        productView.getName().setText(name);
        productView.getDescription().setText(description);
        productView.getPrice().setText(price.toString());

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
