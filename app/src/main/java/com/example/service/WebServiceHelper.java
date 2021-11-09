package com.example.service;

import android.content.Context;

import com.example.util.PropertiesUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import lombok.SneakyThrows;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Authot: Albert Akimov
 * @Date: 11.09.2021
 * @Description:
 */

public class WebServiceHelper {

    private final Retrofit retrofit;
    private final String host;
    private final String baseUrl;
    private final String username;
    private final String password;

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
        //if(instance == null)
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
        return new Authenticator() {
            private int mCounter = 0;

            @Override
            public Request authenticate(Route route, @NotNull Response response) throws IOException {
                if (mCounter++ > 0) {
                    return null;
                } else {
                    String credential = Credentials.basic(username, password);
                    return response.request().newBuilder().header("Authorization", credential).build();
                }
            }
        };
    }
}
