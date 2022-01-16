package com.lunchbox.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lunchbox.activity.R;
import com.lunchbox.domain.model.Product;
import com.lunchbox.view.holder.ViewHolderProduct;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @Authot: Albert Akimov
 * @Date: 04.09.2021
 * @Description:
 */

@Getter
@Setter
public class ProductAdapter extends RecyclerView.Adapter<ViewHolderProduct> implements BaseAdapter<Product> {

    private ArrayList<Product> products = new ArrayList<>();

    @Override
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Product> data) {
        this.products.clear();
        this.products.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void updateData(Product data) {
        products.set(products.indexOf(data), data);
        notifyItemChanged(products.indexOf(data));
    }

    @NonNull
    @Override
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolderProduct(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_product, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct viewHolder, int i) {
        viewHolder.bind(products.get(i));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

}
