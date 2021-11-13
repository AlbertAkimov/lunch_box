package com.lunchbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lunchbox.App;
import com.lunchbox.adapter.ProductCartAdapter;
import com.lunchbox.domain.model.ProductCart;

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

        TextView total = findViewById(R.id.totalProductCart);

        //TODO - Вынести из этого класс
        Long totalSum = Long.valueOf(0);

        for(ProductCart cart: app.getBasketList()) {
            totalSum += cart.getNumber() * cart.getProduct().getProductPrice();
        }

        total.setText(totalSum.toString() + " руб.");
        //TODO - Вынести из этого класс

        adapter = new ProductCartAdapter(CartActivity.this, R.layout.activity_product_cart, app.getBasketList());

        ListView listView = findViewById(R.id.listViewCart);
        listView.setAdapter(adapter);

    }
}
