package com.lunchbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lunchbox.adapter.CategoryMenuAdapter;
import com.lunchbox.service.CategoryService;

import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 13.09.2021
 * @Description:
 */

public class CategoryMenuActivity extends AppCompatActivity {

    public static String EXTRA_DELIVERY_DATE = "CategoryMenuActivity.EXTRA_DELIVERY_DATE";

    CompositeDisposable disposable = new CompositeDisposable();
    private final CategoryMenuAdapter adapter = new CategoryMenuAdapter();

    public static void start(Context caller, String date) {
        Intent intent = new Intent(caller, CategoryMenuActivity.class);
        intent.putExtra(EXTRA_DELIVERY_DATE, date);

        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView listView = findViewById(R.id.list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);

        CategoryService service = new CategoryService(this);
        service.getCategoriesByDate(
                adapter,
                disposable,
                Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_DELIVERY_DATE));

        Button cart = findViewById(R.id.footer_card);
        Button profile = findViewById(R.id.footer_my_profile);
        cart.setOnClickListener(view -> CartActivity.start(view.getContext()));
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }

}