package com.example.lunchbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.App;
import com.example.adapter.CategoryMenuAdapter;
import com.example.adapter.product.ProductCartAdapter;
import com.example.model.CategoryMenu;
import com.example.service.network.CategoryMenuNetworkService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class CategoryMenuActivity extends AppCompatActivity {

    public static String EXTRA_DELIVERY_DATE = "CategoryMenuActivity.EXTRA_DELIVERY_DATE";

    CompositeDisposable disposable = new CompositeDisposable();
    private CategoryMenuAdapter adapter;

    public static void start(Context caller, String date) {
        Intent intent = new Intent(caller, CategoryMenuActivity.class);
        intent.putExtra(EXTRA_DELIVERY_DATE, date);

        EXTRA_DELIVERY_DATE = date;
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView listView = (RecyclerView) findViewById(R.id.list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        listView.setLayoutManager(layoutManager);
        adapter = new CategoryMenuAdapter();
        listView.setAdapter(adapter);

        Button cart = findViewById(R.id.footer_card);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartActivity.start(view.getContext());
            }
        });


        CategoryMenuNetworkService service = new CategoryMenuNetworkService();
        disposable.add(service.getService().getCategoryMenuByDeliveryDate(EXTRA_DELIVERY_DATE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<List<CategoryMenu>, Throwable>() {
                    @Override
                    public void accept(List<CategoryMenu> dates, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            Toast.makeText(CategoryMenuActivity.this, "Data loading error", Toast.LENGTH_SHORT).show();
                        } else {
                            adapter.setDates(dates);
                        }
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
