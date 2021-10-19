package com.example.service.callback;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.lunchbox.DeliveryDateActivity;
import com.example.lunchbox.MainActivity;
import com.example.model.User;

import java.util.List;

import retrofit2.HttpException;

/**
 * @Authot: Albert Akimov
 * @Date: 16.10.2021
 * @Description:
 */

public class AuthenticationCallBack extends AbstractCallBack<User> {

    public AuthenticationCallBack(Context context) {
        super(context);
    }
    @Override
    public void accept(List<User> result, Throwable throwable) throws Exception {
        if(throwable != null)
            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        else {
            DeliveryDateActivity.start(context);
            ((Activity) context).finish();
        }
    }
}
