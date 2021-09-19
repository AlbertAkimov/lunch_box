package com.example.lunchbox;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.CategoryMenuAdapter;
import com.example.adapter.product.ProductCartAdapter;
import com.example.model.CategoryMenu;
import com.example.service.network.CategoryMenuNetworkService;

import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public String deliveryDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_date);

        DatePicker datePicker = findViewById(R.id.deliveryDate);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                   deliveryDate  = String.valueOf(i) + "0" + i1 + i2;
                }
            });
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