package com.lunchbox.service.manager;

import android.content.Context;

import com.lunchbox.domain.model.User;
import com.lunchbox.service.callback.AuthenticationCallBack;

import io.reactivex.disposables.CompositeDisposable;
import lombok.Getter;
import lombok.Setter;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */
@Getter
@Setter
public class AuthenticationDataLoadManager extends DataLoadManager<User> {

    public AuthenticationDataLoadManager(Context context, CompositeDisposable disposable) {
        super(context, disposable, new AuthenticationCallBack(context));
    }
}
