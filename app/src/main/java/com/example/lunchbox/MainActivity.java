package com.example.lunchbox;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.adapter.product.ProductListAdapter;
import com.example.model.Product;
import com.example.service.NetworkService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 /*       ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Test 1", "123", new Price(1L, 15.0), "drawable://" + R.drawable.test_1));
        products.add(new Product(1L, "Test 2", "123", new Price(1L, 25.0), "drawable://" + R.drawable.test_1));
        products.add(new Product(1L, "Test 3", "123", new Price(1L, 35.0), "drawable://" + R.drawable.test_1));
        products.add(new Product(1L, "Test 4", "123", new Price(1L, 45.0), "drawable://" + R.drawable.test_1));
        products.add(new Product(1L, "Test 5", "123", new Price(1L, 55.0), "drawable://" + R.drawable.test_1));
        products.add(new Product(1L, "Test 6", "123", new Price(1L, 65.0), "drawable://" + R.drawable.test_1));
        products.add(new Product(1L, "Test 7", "123", new Price(1L, 75.0), "drawable://" + R.drawable.test_1));

        ProductListAdapter adapter = new ProductListAdapter(this, R.layout.activity_product, products);
        listView.setAdapter(adapter);*/
        ListView listView = (ListView) findViewById(R.id.listView);

        NetworkService service = new NetworkService();
        disposable.add(service.getService().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<List<Product>, Throwable>() {
                    @Override
                    public void accept(List<Product> dates, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            Toast.makeText(MainActivity.this, "Data loading error", Toast.LENGTH_SHORT).show();
                        } else {
                            ProductListAdapter adapter = new ProductListAdapter(MainActivity.this, R.layout.activity_product, dates);
                            listView.setAdapter(adapter);
                        }
                    }
                }));

    }
}