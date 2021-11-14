package com.lunchbox;

import android.app.Application;

import com.lunchbox.domain.model.ElementProductCart;
import com.lunchbox.domain.model.ProductCart;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @Authot: Albert Akimov
 * @Date: 11.09.2021
 * @Description:
 */

@Setter
@Getter
public class App extends Application {

    private ProductCart productCart;

    @Override
    public void onCreate() {
        super.onCreate();

        productCart = new ProductCart();

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)

                .memoryCache(new LruMemoryCache(20 * 1024 * 1024))
                .memoryCacheSize(20 * 1024 * 1024)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
