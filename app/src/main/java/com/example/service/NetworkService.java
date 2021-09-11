package com.example.service;

import lombok.Data;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @Authot: Albert Akimov
 * @Date: 11.09.2021
 * @Description:
 */

@Data
public class NetworkService {

    protected ProductService service;

    public NetworkService() {
        Retrofit retrofit = createRetrofit();
        service = retrofit.create(ProductService.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/test_01/hs/LunchBoxApi/V1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient createOkHttpClient() {

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .authenticator(getBasicAuth("admin", "123456"));

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        return httpClient.build();
    }

    protected Authenticator getBasicAuth(final String username, final String password) {
        return new Authenticator() {
            private int mCounter = 0;

            @Override
            public Request authenticate(Route route, Response response) throws IOException {
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

