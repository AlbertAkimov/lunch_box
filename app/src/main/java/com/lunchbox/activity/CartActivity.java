package com.lunchbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lunchbox.App;
import com.lunchbox.adapter.ProductCartAdapter;
import com.lunchbox.domain.model.ElementProductCart;

/**
 * @Authot: Albert Akimov
 * @Date: 13.09.2021
 * @Description:
 */

public class CartActivity extends AppCompatActivity {

    public ProductCartAdapter adapter;

    public static void start(Context caller)  {
        Intent intent = new Intent(caller, CartActivity.class);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart_list_view);

        App app = (App) getApplication();

        adapter = new ProductCartAdapter(
                CartActivity.this,
                R.layout.activity_product_cart,
                app.getProductCart().getElements());

        ListView listView = findViewById(R.id.listViewCart);
        listView.setAdapter(adapter);

    }
}
