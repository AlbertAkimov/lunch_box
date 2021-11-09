package com.example.lunchbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.service.UserService;
import com.example.util.SHAUtil;

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

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        UserService service = new UserService(getApplicationContext());
        service.autoLogin(disposable, username);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(view -> {

            service.login(
                    username.getText().toString(),
                    SHAUtil.hashPassword(password.getText().toString()),
                    disposable);
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