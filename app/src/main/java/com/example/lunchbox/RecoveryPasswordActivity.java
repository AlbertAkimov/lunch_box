package com.example.lunchbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.User;
import com.example.service.manager.RecoveryPasswordDataLoadManager;
import com.example.service.network.UserNetworkService;
import com.example.util.SHAUtil;

import java.util.List;

import io.reactivex.Single;
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

            UserNetworkService service = new UserNetworkService(getApplicationContext());

            Single<List<User>> single = service.
                    getService().changePasswordByRecoveryCode(SHAUtil.hashPassword(password.getText().toString()), recoveryCode.getText().toString());

            RecoveryPasswordDataLoadManager loadManager = new RecoveryPasswordDataLoadManager(view.getContext(), disposable);
            loadManager.execute(single);
        });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
