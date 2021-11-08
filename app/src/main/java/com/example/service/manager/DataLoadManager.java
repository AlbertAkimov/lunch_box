package com.example.service.manager;

import android.content.Context;

import com.example.model.AbstractModel;
import com.example.service.callback.AbstractCallBack;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;

/**
 * @Authot: Albert Akimov
 * @Date: 30.09.2021
 * @Description:
 */

@Getter
@Setter
public abstract class DataLoadManager<T extends AbstractModel> {

    protected Context context;
    protected CompositeDisposable disposable;
    protected AbstractCallBack<T> callBack;

    public DataLoadManager(Context context, CompositeDisposable disposable, AbstractCallBack<T> abstractCallBack) {
        this.context    = context;
        this.disposable = disposable;
        this.callBack = abstractCallBack;
    }

    public void execute(Single<List<T>> single) {

        disposable.add(single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack));
    }
}
