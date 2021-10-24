package com.example.service.network;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.example.database.AppDatabase;
import com.example.lunchbox.R;
import com.example.model.User;
import com.example.service.api.UserService;
import com.example.service.manager.AuthenticationDataLoadManager;
import com.example.util.SHAUtil;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNetworkService extends AbstractNetworkService {

    protected UserService service;

    public UserNetworkService(Context context) {
        super(context);
        service = retrofit.create(UserService.class);
    }

    public void autoLogin(CompositeDisposable disposable, TextView username) {

        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        List<User> result = database.userRepository().getAll();

        if(result.size() > 0) {
            username.setText(result.get(0).getUsername());
            login(result.get(0).getUsername(), result.get(0).getPassword(), disposable);
        }
    }

    public void login(String username, String password, CompositeDisposable disposable) {

        AuthenticationDataLoadManager dataLoadManager = new AuthenticationDataLoadManager(context, disposable);
        dataLoadManager.execute(service.login(username, password));
    }
}
