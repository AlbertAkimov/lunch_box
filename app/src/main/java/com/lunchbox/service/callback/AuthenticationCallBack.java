package com.lunchbox.service.callback;

import android.content.Context;
import android.widget.Toast;

import com.lunchbox.database.AppDatabase;
import com.lunchbox.activity.DeliveryDateActivity;
import com.lunchbox.domain.model.User;

import java.util.List;

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

            User user = new User();
            user.setUsername(result.get(0).getUsername());
            user.setPassword(result.get(0).getPassword());

            AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
            database.userRepository().save(user);

            DeliveryDateActivity.start(context);
            //TODO - не работает
            //((Activity) context).finish();
        }
    }
}
