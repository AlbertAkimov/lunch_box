package com.lunchbox.view.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lunchbox.activity.ProductActivity;
import com.lunchbox.activity.R;
import com.lunchbox.domain.model.CategoryMenu;
import com.lunchbox.util.ImageUtil;
import com.lunchbox.view.CategoryMenuView;

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
        itemView.setOnClickListener(view -> ProductActivity.start(view.getContext(), categoryMenu.getDate(), categoryMenu.getId().toString()));
    }

    public void bind(@NonNull CategoryMenu categoryMenu) {
        this.categoryMenu = categoryMenu;
        categoryMenuView.getName().setText(categoryMenu.getName());
        if (!categoryMenu.getImage().isEmpty())
            categoryMenuView.getImage().setImageBitmap(ImageUtil.getDecodeImage(categoryMenu.getImage()));
    }
}
