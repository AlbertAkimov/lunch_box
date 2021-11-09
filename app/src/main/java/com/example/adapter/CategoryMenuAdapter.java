package com.example.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lunchbox.R;
import com.example.domain.model.CategoryMenu;
import com.example.view.ViewHolderCategoryMenu;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @Authot: Albert Akimov
 * @Date: 14.09.2021
 * @Description:
 */

@Setter
@Getter
public class CategoryMenuAdapter extends RecyclerView.Adapter<ViewHolderCategoryMenu> {

    private ArrayList<CategoryMenu> categoryMenu = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setDates(List<CategoryMenu> categoryMenus) {
        this.categoryMenu.clear();
        this.categoryMenu.addAll(categoryMenus);
        notifyDataSetChanged();
    }

    public void updateData(CategoryMenu data) {
        categoryMenu.set(categoryMenu.indexOf(data), data);
        notifyItemChanged(categoryMenu.indexOf(data));
    }

    @NonNull
    @Override
    public ViewHolderCategoryMenu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolderCategoryMenu(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_category_menu, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCategoryMenu viewHolder, int i) {
        viewHolder.bind(categoryMenu.get(i));
    }

    @Override
    public int getItemCount() {
        return categoryMenu.size();
    }
}
