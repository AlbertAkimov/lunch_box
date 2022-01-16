package com.lunchbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lunchbox.adapter.ProductAdapter;
import com.lunchbox.service.ProductService;

import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Authot: Albert Akimov
 * @Date: 13.09.2021
 * @Description:
 */

public class ProductActivity extends AppCompatActivity {

    private static final String EXTRA_DELIVERY_DATE = "ProductActivity.EXTRA_DELIVERY_DATE";
    private static final String EXTRA_CATEGORY_MENU_ID = "ProductActivity.EXTRA_CATEGORY_MENU_ID";

    CompositeDisposable disposable = new CompositeDisposable();
    private final ProductAdapter adapter = new ProductAdapter();

    public static void start(Context caller, String date, String id) {
        Intent intent = new Intent(caller, ProductActivity.class);
        intent.putExtra(EXTRA_DELIVERY_DATE, date);
        intent.putExtra(EXTRA_CATEGORY_MENU_ID, id);

        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_view);

        RecyclerView listView = findViewById(R.id.listViewProduct);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ProductService service = new ProductService(this);
        service.execute(
                adapter,
                disposable,
                Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_DELIVERY_DATE),
                getIntent().getExtras().getString(EXTRA_CATEGORY_MENU_ID));
    }
}