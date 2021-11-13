package com.lunchbox.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.lunchbox.adapter.ProductAdapter;
import com.lunchbox.controller.api.ProductController;
import com.lunchbox.domain.model.Product;

import java.util.List;
import java.util.Random;

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


public class ProductService extends BaseService<ProductController, Object> {

    public ProductService(Context context) {
        super(context, ProductController.class, Object.class);
    }

    public void execute(ProductAdapter adapter, CompositeDisposable disposable, String date, String categoryId) {

        getProduct(adapter, date, categoryId)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Product, ObservableSource<Product>>() {
                    @Override
                    public ObservableSource<Product> apply(@NonNull Product product) throws Exception {
                        return getImageProduct(product);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Product>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Product product) {
                        adapter.updateData(product);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("ProductActivity - ", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<Product> getProduct(ProductAdapter adapter, String date, String categoryId) {

        return controller
                .getProductByCategoryMenuAndDeliveryDate(date, categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<List<Product>, ObservableSource<Product>>) products -> {
                    adapter.setData(products);
                    return Observable.fromIterable(products).subscribeOn(Schedulers.io());
                });
    }

    private Observable<Product> getImageProduct(final Product product) {

        return controller
                .getImageProductById(product.getId())
                .map(products -> {
                    int delay = ((new Random()).nextInt(5) + 1) * 1000;
                    Thread.sleep(delay);
                    product.setProductImage(products.getProductImage());
                    return product;
                })
                .subscribeOn(Schedulers.io());
    }
}
