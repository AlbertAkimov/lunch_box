package com.lunchbox.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.lunchbox.adapter.ProductAdapter;
import com.lunchbox.controller.api.ProductController;
import com.lunchbox.domain.model.Product;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Authot: Albert Akimov
 * @Date: 12.09.2021
 * @Description:
 */


public class ProductService extends BaseService<ProductController, Object, Product> {

    public ProductService(Context context) {
        super(context, ProductController.class, Object.class);
    }

    public void execute(ProductAdapter adapter, CompositeDisposable disposable, String date, String categoryId) {
        execute(adapter, disposable, controller.getProductsByCategoryId(date, categoryId));
    }

    @Override
    public void accept(List<Product> products, Throwable throwable) throws Exception {}
}
