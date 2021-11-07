package com.example.lunchbox;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.App;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */

public class AuthenticationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        App app = (App) getApplication();
    }
}
