package com.example.lunchbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.adapter.product.ProductCartAdapter;
import com.example.adapter.product.ProductListAdapter;
import com.example.model.Product;
import com.example.service.ProductNetworkService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();
    private ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        Button productCart = findViewById(R.id.footer_card);
        productCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Работает", Toast.LENGTH_SHORT).show();
                ProductCartAdapter productCartAdapter =
                        new ProductCartAdapter(MainActivity.this, R.layout.activity_product_cart, adapter.getBasketList());
                listView.setAdapter(productCartAdapter);
            }
        });

        ProductNetworkService service = new ProductNetworkService();
        disposable.add(service.getService().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<List<Product>, Throwable>() {
                    @Override
                    public void accept(List<Product> dates, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            Toast.makeText(MainActivity.this, "Data loading error", Toast.LENGTH_SHORT).show();
                        } else {
                            adapter = new ProductListAdapter(MainActivity.this, R.layout.activity_product, dates);
                            listView.setAdapter(adapter);
                        }
                    }
                }));

    }
}