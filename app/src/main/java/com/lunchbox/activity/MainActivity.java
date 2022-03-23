package com.lunchbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.lunchbox.App;
import com.lunchbox.service.UserService;
import com.lunchbox.util.SHAUtil;

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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        App app = (App) getApplication();

        UserService service = new UserService(this);

        if(app.getAuthUser() != null) {
            findViewById(R.id.progress_authentication).setVisibility(View.VISIBLE);
            service.autoLogin(disposable, username);
        }

        Button login = findViewById(R.id.login);
        login.setOnClickListener(view -> {

            findViewById(R.id.progress_authentication).setVisibility(View.VISIBLE);

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

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.progress_authentication).setVisibility(View.GONE);
    }
}