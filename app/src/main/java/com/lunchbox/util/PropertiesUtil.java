package com.lunchbox.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Authot: Albert Akimov
 * @Date: 26.09.2021
 * @Description:
 */

public class PropertiesUtil {

    public static String getProperty(String key, Context context) throws IOException {

        Properties properties = new Properties();;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("application.properties");
        properties.load(inputStream);
        return properties.getProperty(key);

    }
}
