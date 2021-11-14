package com.lunchbox.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lunchbox.App;
import com.lunchbox.activity.CartActivity;
import com.lunchbox.activity.R;
import com.lunchbox.domain.model.ElementProductCart;
import com.lunchbox.domain.model.ProductCart;
import com.lunchbox.util.ImageUtil;
import com.lunchbox.view.ProductCartView;

import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

/**
 * @Authot: Albert Akimov
 * @Date: 13.09.2021
 * @Description:
 */
@Getter
@Setter
public class ProductCartAdapter extends ArrayAdapter<ElementProductCart> {

    private final Context mContext;
    private final int mResource;
    private ProductCart productCart;
    private TextView total;

    public ProductCartAdapter(@NonNull Context context, int resource, @NonNull List<ElementProductCart> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        productCart = ((App) context.getApplicationContext()).getProductCart();
        total = ((Activity) context).findViewById(R.id.totalProductCart);
        total.setText((String) productCart.getTotalSum().toString());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ProductCartView productCartView;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            productCartView = new ProductCartView();
            productCartView.setName(convertView.findViewById(R.id.productCartName));
            productCartView.setPrice(convertView.findViewById(R.id.productCartPrice));
            productCartView.setImage(convertView.findViewById(R.id.productCartImage));
            productCartView.setNumber(convertView.findViewById(R.id.productCartNumber));
            productCartView.setTotal(convertView.findViewById(R.id.totalProductCart));

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

        ImageButton increase = convertView.findViewById(R.id.increase);
        increase.setOnClickListener(v -> {
            productCart.changeItemQuantity(Objects.requireNonNull(getItem(position)), 1L);
            total.setText((String) productCart.getTotalSum().toString());
            notifyDataSetChanged();
        });

        ImageButton decrease = convertView.findViewById(R.id.decrease);
        decrease.setOnClickListener(v -> {
            productCart.changeItemQuantity(Objects.requireNonNull(getItem(position)), -1L);
            total.setText((String) productCart.getTotalSum().toString());
            notifyDataSetChanged();
        });

        return convertView;
    }
}
