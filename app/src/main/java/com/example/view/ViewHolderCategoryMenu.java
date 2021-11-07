package com.example.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lunchbox.ProductActivity;
import com.example.lunchbox.R;
import com.example.model.CategoryMenu;
import com.example.util.ImageUtil;
import com.example.view.product.CategoryMenuView;

/**
 * @Authot: Albert Akimov
 * @Date: 13.09.2021
 * @Description:
 */

public class ViewHolderCategoryMenu extends RecyclerView.ViewHolder {

    CategoryMenu categoryMenu;

    CategoryMenuView categoryMenuView;

    public ViewHolderCategoryMenu(@NonNull View itemView) {
        super(itemView);
        categoryMenuView = new CategoryMenuView();
        categoryMenuView.setName(itemView.findViewById(R.id.categoryMenuName));
        categoryMenuView.setImage(itemView.findViewById(R.id.categoryMenuImage));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductActivity.start(view.getContext(), categoryMenu.getDate(), categoryMenu.getId().toString());
            }
        });
    }

    public void bind(@NonNull CategoryMenu categoryMenu) {
        this.categoryMenu = categoryMenu;
        categoryMenuView.getName().setText(categoryMenu.getCategoryMenuName());
        if (!categoryMenu.getCategoryMenuImage().isEmpty())
            categoryMenuView.getImage().setImageBitmap(ImageUtil.getDecodeImage(categoryMenu.getCategoryMenuImage()));
    }
}
