package com.lunchbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.lunchbox.util.DatePickerUtil;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */

public class DeliveryDateActivity extends AppCompatActivity {

    public String deliveryDate = "";

    public static void start(Context caller) {
        Intent intent = new Intent(caller, DeliveryDateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_date);

        DatePicker datePicker = findViewById(R.id.deliveryDate);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener((datePicker1, i, i1, i2) -> deliveryDate = DatePickerUtil.buildDateString(i, i1, i2));
        }

        Button productCart = findViewById(R.id.makeAnOrder);
        productCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryMenuActivity.start(v.getContext(), deliveryDate);
            }
        });
    }
}
