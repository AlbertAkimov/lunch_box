package com.example.service.network;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.product.ProductListAdapter;
import com.example.lunchbox.ProductActivity;
import com.example.lunchbox.R;
import com.example.model.AbstractModel;
import com.example.model.Product;
import com.example.util.PropertiesUtil;

import java.util.List;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
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
public abstract class AbstractDisposable<T extends AbstractModel> {

    protected Context context;
    protected List<T> data;
    protected boolean isSuccessfully;

    public AbstractDisposable(Context context) {
        this.context = context;

    }

    public Disposable subscribe(Single<List<T>> single) {

        return single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<List<T>, Throwable>() {
                    @Override
                    public void accept(List<T> result, Throwable throwable) throws Exception {

                        if (throwable != null) {
                            Toast.makeText(
                                    context,
                                    PropertiesUtil.getProperty("network.error.loading.data", context),
                                    Toast.LENGTH_SHORT).show();
                            isSuccessfully = false;
                        } else {
                            isSuccessfully = true;
                            data = result;
                        }
                    }
                });
    }
}
