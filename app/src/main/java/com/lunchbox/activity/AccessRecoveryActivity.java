package com.lunchbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lunchbox.service.UserService;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 16.10.2021
 * @Description:
 */

public class AccessRecoveryActivity extends AppCompatActivity {

    TextView email;
    CompositeDisposable disposable = new CompositeDisposable();

    public static void start(Context caller) {
        Intent intent = new Intent(caller, AccessRecoveryActivity.class);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_recovery);

        email = findViewById(R.id.email);

        Button restore = findViewById(R.id.send_email);
        restore.setOnClickListener(view -> {

            UserService service = new UserService(getApplicationContext());
            service.restorePasswordByEmail(email.getText().toString(), disposable);

        });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
