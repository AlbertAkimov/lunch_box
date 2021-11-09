package com.example.service;

import android.content.Context;

import com.example.service.BaseService;
import com.example.controller.api.ProductController;

/**
 * @Authot: Albert Akimov
 * @Date: 12.09.2021
 * @Description:
 */


public class ProductService extends BaseService<ProductController, Object> {

    public ProductService(Context context) {
        super(context, ProductController.class, Object.class);
    }
}
