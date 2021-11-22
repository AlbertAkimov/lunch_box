package com.lunchbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lunchbox.service.UserService;
import com.lunchbox.util.SHAUtil;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 16.10.2021
 * @Description:
 */

public class RecoveryPasswordActivity extends AppCompatActivity {

    TextView password;
    TextView confirmPassword;
    TextView recoveryCode;

    CompositeDisposable disposable = new CompositeDisposable();

    public static void start(Context caller)  {
        Intent intent = new Intent(caller, RecoveryPasswordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password);

        password        = findViewById(R.id.new_password);
        confirmPassword = findViewById(R.id.confirm_password);
        recoveryCode    = findViewById(R.id.recovery_code);

        Button send = findViewById(R.id.change_password);
        send.setOnClickListener(view -> {

            UserService service = new UserService(this);
            service.changePasswordByRecoveryCode(
                    SHAUtil.hashPassword(password.getText().toString()),
                    recoveryCode.getText().toString(),
                    disposable);

        });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
