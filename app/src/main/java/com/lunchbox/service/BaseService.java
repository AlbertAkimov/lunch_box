package com.lunchbox.service;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.lunchbox.adapter.BaseAdapter;
import com.lunchbox.controller.api.BaseController;
import com.lunchbox.database.AppDatabase;
import com.lunchbox.domain.model.AbstractModel;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;

/**
 * @Authot: Albert Akimov
 * @Date: 09.11.2021
 * @Description:
 */

@Data
public abstract class BaseService<C extends BaseController, R, T extends AbstractModel>
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

    protected void execute(BaseAdapter<T> adapter, CompositeDisposable disposable, Observable<List<T>> observable) {

        getData(observable, adapter)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<T, ObservableSource<T>>)
                        this::getDataById)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull T object) {
                        adapter.updateData(object);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ActivityManager am = (ActivityManager)context.getSystemService(ACTIVITY_SERVICE);
                        List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);
                        taskInfo.get(0).topActivity.getClassName();
                        Log.e( taskInfo.get(0).topActivity.getClassName() + " ---- ", e.getMessage());
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    private Observable<T> getData(Observable<List<T>> observable, BaseAdapter<T> adapter) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<List<T>, ObservableSource<T>>) data -> {
                    adapter.setData(data);
                    return Observable.fromIterable(data).subscribeOn(Schedulers.io());
                });
    }

    private Observable<T> getDataById(T object) {
        return controller.getById(object.getId()).map(obj -> {

/*            int delay = ((new Random()).nextInt(5) + 1) * 2000;
            Thread.sleep(delay);*/

            try {
                Field field = object.getClass().getDeclaredField("image");
                Field field1 = obj.getClass().getDeclaredField("image");
                field.setAccessible(true);
                field1.setAccessible(true);
                field.set(object, field1.get(obj));


            } catch (NoSuchFieldException e) {
                ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
                List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                taskInfo.get(0).topActivity.getClassName();
                Log.e(taskInfo.get(0).topActivity.getClassName() + " ---- ", e.getMessage());
            }
            return object;

        }).subscribeOn(Schedulers.io());
    }
}
