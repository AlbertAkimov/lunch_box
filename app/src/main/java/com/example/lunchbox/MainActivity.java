package com.example.lunchbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.User;
import com.example.service.network.UserNetworkService;
import com.example.service.manager.AuthenticationDataLoadManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserNetworkService service = new UserNetworkService(getApplicationContext());

                Single<List<User>> single = service.
                        getService().login(username.getText().toString(), password.getText().toString());

                AuthenticationDataLoadManager test = new AuthenticationDataLoadManager(view.getContext(), disposable);
                test.execute(single);
/*                disposable.add(service.getService().login(username.getText().toString(), password.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((dates, throwable) -> {
                            if (throwable != null)
                                Toast.makeText(MainActivity.this, ((HttpException) throwable).message(), Toast.LENGTH_SHORT).show();
                             else
                                DeliveryDateActivity.start(MainActivity.this);
                        }));*/
                //findViewById(R.id.progress).setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}