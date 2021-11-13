package com.lunchbox.service;

import android.content.Context;

import com.lunchbox.util.PropertiesUtil;

import lombok.Data;
import lombok.SneakyThrows;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Authot: Albert Akimov
 * @Date: 11.09.2021
 * @Description:
 */

@Data
public class WebServiceHelper {

    private Retrofit retrofit;
    private String host;
    private String baseUrl;
    private String username;
    private String password;

    private static WebServiceHelper instance;

    @SneakyThrows
    public WebServiceHelper(Context context) {
        host     = PropertiesUtil.getProperty("network.host", context);
        baseUrl  = PropertiesUtil.getProperty("network.baseUrl", context);
        username = PropertiesUtil.getProperty("network.auth.username", context);
        password = PropertiesUtil.getProperty("network.auth.password", context);
        retrofit = createRetrofit();
    }

    public static WebServiceHelper getInstance(Context context) {
        if(instance == null)
            instance = new WebServiceHelper(context);
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> clazz) {
        return (T) retrofit.create(clazz);
    }

    @SneakyThrows
    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(host + baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient createOkHttpClient() {

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .authenticator(getBasicAuth(username, password));

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        return httpClient.build();
    }

    private Authenticator getBasicAuth(final String username, final String password) {
        return (route, response) -> {
            String credential = Credentials.basic(username, password);
            return response.request().newBuilder().header("Authorization", credential).build();
        };
    }
}

