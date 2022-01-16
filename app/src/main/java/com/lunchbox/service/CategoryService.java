package com.lunchbox.service;

import android.content.Context;

import com.lunchbox.adapter.CategoryMenuAdapter;
import com.lunchbox.controller.api.CategoryController;
import com.lunchbox.domain.model.CategoryMenu;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 14.09.2021
 * @Description:
 */

public class CategoryService extends BaseService<CategoryController, Object, CategoryMenu> {

    public CategoryService(Context context) {
        super(context, CategoryController.class, Object.class);
    }

    public void execute(CategoryMenuAdapter adapter, CompositeDisposable disposable, String date) {
        execute(adapter, disposable, controller.getCategoriesByDate(date));
    }

    @Override
    public void accept(List<CategoryMenu> categoryMenus, Throwable throwable) throws Exception {}
}
