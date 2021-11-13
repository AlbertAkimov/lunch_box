package com.lunchbox.service;

import android.content.Context;

import com.lunchbox.database.AppDatabase;
import com.lunchbox.domain.model.AbstractModel;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;

/**
 * @Authot: Albert Akimov
 * @Date: 09.11.2021
 * @Description:
 */

@Data
public abstract class BaseService<C, R, T extends AbstractModel>
        implements BiConsumer<List<T>, Throwable> {

    protected C controller;
    protected R repository;
    protected Context context;

    public BaseService(Context context, Class<?> classController, Class<?> classRepository) {
        this.context = context;
        controller = WebServiceHelper.getInstance(context).create(classController);
        repository = AppDatabase.getInstance(context).getRepository(classRepository);
    }

    public Disposable execute(Single<List<T>> single) {

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }
}
