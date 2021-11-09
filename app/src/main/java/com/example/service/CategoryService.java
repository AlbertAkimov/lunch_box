package com.example.service;

import android.content.Context;

import com.example.service.BaseService;
import com.example.controller.api.CategoryController;

/**
 * @Authot: Albert Akimov
 * @Date: 14.09.2021
 * @Description:
 */

public class CategoryService extends BaseService<CategoryController, Object> {

    public CategoryService(Context context) {
        super(context, CategoryController.class, Object.class);
    }
}
