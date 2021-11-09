package com.example.service;

import android.content.Context;
import android.widget.TextView;

import com.example.database.AppDatabase;
import com.example.domain.model.User;
import com.example.repository.UserRepository;
import com.example.service.BaseService;
import com.example.controller.api.UserController;
import com.example.service.manager.AuthenticationDataLoadManager;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */

public class UserService
        extends BaseService<UserController, UserRepository> {

    public UserService(Context context) {
        super(context, UserController.class, UserRepository.class);
    }

    public void autoLogin(CompositeDisposable disposable, TextView username) {

        List<User> result = repository.getAll();

        if(result.size() > 0) {
            username.setText(result.get(0).getUsername());
            login(result.get(0).getUsername(), result.get(0).getPassword(), disposable);
        }
    }

    public void login(String username, String password, CompositeDisposable disposable) {

        AuthenticationDataLoadManager dataLoadManager = new AuthenticationDataLoadManager(context, disposable);
        dataLoadManager.execute(controller.login(username, password));
    }
}
