package com.lunchbox.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.lunchbox.adapter.CategoryMenuAdapter;
import com.lunchbox.controller.api.CategoryController;
import com.lunchbox.domain.model.CategoryMenu;

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
 * @Date: 14.09.2021
 * @Description:
 */

public class CategoryService extends BaseService<CategoryController, Object> {

    public CategoryService(Context context) {
        super(context, CategoryController.class, Object.class);
    }

    public void execute(CategoryMenuAdapter adapter, CompositeDisposable disposable, String date) {

        getCategoryMenu(adapter, date)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<CategoryMenu, ObservableSource<CategoryMenu>>() {
                    @Override
                    public ObservableSource<CategoryMenu> apply(@NonNull CategoryMenu categoryMenu) throws Exception {
                        return getImageCategoryMenu(categoryMenu);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryMenu>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(@NonNull CategoryMenu categoryMenu) {
                adapter.updateData(categoryMenu);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("CategoryMenuActivity - ", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private Observable<CategoryMenu> getCategoryMenu(CategoryMenuAdapter adapter, String date) {

        return controller
                .getDataByDeliveryDate(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<List<CategoryMenu>, ObservableSource<CategoryMenu>>) categoryMenus -> {
                    adapter.setData(categoryMenus);
                    return Observable.fromIterable(categoryMenus).subscribeOn(Schedulers.io());
                });
    }

    private Observable<CategoryMenu> getImageCategoryMenu(final CategoryMenu menu) {

        return controller
                .getImageCategoryMenuById(menu.getId())
                .map(categoryMenu -> {
                    int delay = ((new Random()).nextInt(5) + 1) * 1000;
                    Thread.sleep(delay);
                    menu.setCategoryMenuImage(categoryMenu.getCategoryMenuImage());
                    return menu;
                })
                .subscribeOn(Schedulers.io());
    }
}
