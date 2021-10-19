package com.example.service.manager;

import android.content.Context;

import com.example.model.User;
import com.example.service.callback.AccessRecoveryCallBack;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 16.10.2021
 * @Description:
 */

public class AccessRecoveryDataLoadManager extends  DataLoadManager<User> {
    public AccessRecoveryDataLoadManager(Context context, CompositeDisposable disposable) {
        super(context, disposable, new AccessRecoveryCallBack(context));
    }
}
