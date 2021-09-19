package com.example.lunchbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.App;
import com.example.adapter.product.ProductListAdapter;
import com.example.model.Product;
import com.example.service.network.ProductNetworkService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class ProductActivity extends AppCompatActivity {

    private static String EXTRA_DELIVERY_DATE = "ProductActivity.EXTRA_DELIVERY_DATE";
    private static String EXTRA_CATEGORY_MENU_ID = "ProductActivity.EXTRA_CATEGORY_MENU_ID";

    CompositeDisposable disposable = new CompositeDisposable();
    private ProductListAdapter adapter;

    public static void start(Context caller, String date, String id) {
        Intent intent = new Intent(caller, ProductActivity.class);
        intent.putExtra(EXTRA_DELIVERY_DATE, date);
        intent.putExtra(EXTRA_CATEGORY_MENU_ID, id);

        EXTRA_DELIVERY_DATE = date;
        EXTRA_CATEGORY_MENU_ID = id;

        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_view);

        ListView listView = (ListView) findViewById(R.id.listView);

        App app = (App) getApplication();

        ProductNetworkService service = new ProductNetworkService();
        disposable.add(service.getService().getProductByCategoryMenuAndDeliveryDate(
               EXTRA_DELIVERY_DATE,
                EXTRA_CATEGORY_MENU_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<List<Product>, Throwable>() {
                    @Override
                    public void accept(List<Product> dates, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            Toast.makeText(ProductActivity.this, "Data loading error", Toast.LENGTH_SHORT).show();
                        } else {
                            adapter = new ProductListAdapter(ProductActivity.this, R.layout.activity_product, dates, app.getBasketList());
                            listView.setAdapter(adapter);
                        }
                    }
                }));
    }
}