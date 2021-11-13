package com.lunchbox.service;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.lunchbox.activity.DeliveryDateActivity;
import com.lunchbox.activity.MainActivity;
import com.lunchbox.activity.RecoveryPasswordActivity;
import com.lunchbox.controller.api.CallBackType;
import com.lunchbox.domain.model.User;
import com.lunchbox.repository.UserRepository;
import com.lunchbox.controller.api.UserController;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */

public class UserService
        extends BaseService<UserController, UserRepository, User> {

    private CallBackType callBackType;

    public UserService(Context context) {
        super(context, UserController.class, UserRepository.class);
        callBackType = CallBackType.NONE;
    }

    public void autoLogin(CompositeDisposable disposable, TextView username) {

        List<User> result = repository.getAll();

        if(result.size() > 0) {
            username.setText(result.get(0).getUsername());
            login(result.get(0).getUsername(), result.get(0).getPassword(), disposable);
        }
    }

    public void login(String username, String password, CompositeDisposable disposable) {
        callBackType = CallBackType.LOGIN;
        disposable.add(execute(controller.login(username, password)));
    }

    public void restorePasswordByEmail(String email, CompositeDisposable disposable) {
        callBackType = CallBackType.RESTORE_PASSWORD_BY_EMAIL;
        disposable.add(execute(controller.restorePasswordByEmail(email)));
    }

    public void changePasswordByRecoveryCode(String password, String recoveryCode, CompositeDisposable disposable) {
        callBackType = CallBackType.CHANGE_PASSWORD_BY_RECOVERY_CODE;
        disposable.add(execute(controller.changePasswordByRecoveryCode(password, recoveryCode)));
    }

    @Override
    public void accept(List<User> users, Throwable throwable) {

        if(throwable != null)
            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        else {

            switch (callBackType) {
                case LOGIN:
                    if(users.isEmpty())
                        break;
                    User user = repository.getById(users.get(0).getId());
                    if(user != null) {
                        if(!user.equals(users.get(0))) {
                            repository.save(users.get(0));
                        }
                    }
                    DeliveryDateActivity.start(context);
                    break;
                    //TODO - don't work
                    //((Activity) context).finish();

                case RESTORE_PASSWORD_BY_EMAIL:
                    RecoveryPasswordActivity.start(context);
                    break;

                case CHANGE_PASSWORD_BY_RECOVERY_CODE:
                    MainActivity.start(context);
                    break;
            }

        }
    }
}
