package com.example.service;

import com.example.model.Product;
import io.reactivex.Single;
import retrofit2.http.GET;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 11.09.2021
 * @Description:
 */
public interface ProductService {

    @GET("product")
    Single<List<Product>> getAll();
}
