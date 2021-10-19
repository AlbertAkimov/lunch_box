package com.example.service.manager;

import android.content.Context;

import com.example.model.User;
import com.example.service.callback.AbstractCallBack;
import com.example.service.callback.RecoveryPasswordCallBack;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 16.10.2021
 * @Description:
 */

public class RecoveryPasswordDataLoadManager extends DataLoadManager<User>{

    public RecoveryPasswordDataLoadManager(Context context, CompositeDisposable disposable) {
        super(context, disposable, new RecoveryPasswordCallBack(context));
    }
}
