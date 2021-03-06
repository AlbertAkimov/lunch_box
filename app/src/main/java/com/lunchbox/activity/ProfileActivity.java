package com.lunchbox.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lunchbox.App;
import com.lunchbox.service.UserService;

/**
 * @Authot: Albert Akimov
 * @Date: 23.03.2022
 * @Description:
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UserService userService = new UserService(this);
        App app = (App) getApplication();

        TextView username = findViewById(R.id.profile_username);
        TextView email = findViewById(R.id.profile_email);
        TextView personalNumber = findViewById(R.id.profile_personalNumber);

        username.setText(app.getAuthUser().getUsername());
        email.setText(app.getAuthUser().getEmail());
        personalNumber.setText(app.getAuthUser().getPersonalNumber());

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            boolean isLogout = userService.logout(app.getAuthUser());
            if(isLogout)
                MainActivity.start(v.getContext());
            else {
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
