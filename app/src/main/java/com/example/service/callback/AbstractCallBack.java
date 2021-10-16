package com.example.service.callback;

import android.content.Context;

import com.example.model.AbstractModel;

import java.util.List;

import io.reactivex.functions.BiConsumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authot: Albert Akimov
 * @Date: 15.10.2021
 * @Description:
 */

public abstract class AbstractCallBack<T extends AbstractModel>
        implements BiConsumer<List<T>, Throwable> {

    protected Context context;

    public AbstractCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void accept(List<T> result, Throwable throwable) throws Exception {}
}
