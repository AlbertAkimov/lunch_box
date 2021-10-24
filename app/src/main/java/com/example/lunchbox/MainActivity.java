package com.example.lunchbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.User;
import com.example.service.manager.AuthenticationDataLoadManager;
import com.example.service.network.UserNetworkService;
import com.example.util.SHAUtil;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 13.09.2021
 * @Description:
 */

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    CompositeDisposable disposable = new CompositeDisposable();

    public static void start(Context caller)  {
        Intent intent = new Intent(caller, MainActivity.class);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        //TODO - Возможно нужно вынести слой сервиса в менеджер загрузки данных.
        UserNetworkService service = new UserNetworkService(getApplicationContext());

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Button login = findViewById(R.id.login);

        login.setOnClickListener(view -> {

            //TODO - Возможно нужно вынести слой сервиса в менеджер загрузки данных.
            Single<List<User>> single = null;
            single = service.getService().login(username.getText().toString(), SHAUtil.hashPassword(password.getText().toString()));

            AuthenticationDataLoadManager dataLoadManager = new AuthenticationDataLoadManager(view.getContext(), disposable);
            dataLoadManager.execute(single);

        });

        Button restorePassword = findViewById(R.id.restore_password);
        restorePassword.setOnClickListener(view -> {
            AccessRecoveryActivity.start(view.getContext());
            finishAffinity();
        });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}