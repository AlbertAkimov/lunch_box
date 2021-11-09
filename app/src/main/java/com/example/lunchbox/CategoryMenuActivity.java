package com.example.lunchbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.CategoryMenuAdapter;
import com.example.domain.model.CategoryMenu;
import com.example.service.CategoryService;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

        RecyclerView listView = (RecyclerView) findViewById(R.id.list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);

        getCategoryMenu()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<CategoryMenu, ObservableSource<CategoryMenu>>() {
            @Override
            public ObservableSource<CategoryMenu> apply(@NonNull CategoryMenu categoryMenu) throws Exception {
                return getImageCategoryMenu(categoryMenu);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CategoryMenu>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(@NonNull CategoryMenu categoryMenu) {
                adapter.updateData(categoryMenu);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("1",e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

        Button cart = findViewById(R.id.footer_card);
        cart.setOnClickListener(view -> CartActivity.start(view.getContext()));

    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }

    private Observable<CategoryMenu> getCategoryMenu() {
        CategoryService service = new CategoryService(getApplicationContext());
        return service.getController()
                .getDataByDeliveryDate(Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_DELIVERY_DATE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<List<CategoryMenu>, ObservableSource<CategoryMenu>>) categoryMenus -> {
                    adapter.setDates(categoryMenus);
                    return Observable.fromIterable(categoryMenus).subscribeOn(Schedulers.io());
                });
    }

    private Observable<CategoryMenu> getImageCategoryMenu(final CategoryMenu menu) {
        CategoryService service = new CategoryService(getApplicationContext());
        return service.getController()
                .getImageCategoryMenuById(menu.getId())
                .map(categoryMenu -> {

                    int delay = ((new Random()).nextInt(5) + 1) * 1000;

                    Thread.sleep(delay);

                    menu.setCategoryMenuImage(categoryMenu.getCategoryMenuImage());

                    return menu;
                })
                .subscribeOn(Schedulers.io());
    }
}