package com.lunchbox.service.callback;

import android.content.Context;
import android.widget.Toast;

import com.lunchbox.activity.RecoveryPasswordActivity;
import com.lunchbox.domain.model.User;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 16.10.2021
 * @Description:
 */

public class AccessRecoveryCallBack extends AbstractCallBack<User> {

    public AccessRecoveryCallBack(Context context) {
        super(context);
    }

    @Override
    public void accept(List<User> result, Throwable throwable) throws Exception {
        if (throwable != null)
            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        else
            RecoveryPasswordActivity.start(context);
    }
}
