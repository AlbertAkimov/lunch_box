package com.lunchbox.controller.api;

import com.lunchbox.domain.model.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Authot: Albert Akimov
 * @Date: 11.09.2021
 * @Description:
 */
public interface ProductController {

    @GET("production_plan")
    Observable<List<Product>> getProductsByCategoryId(
            @Query("date") String date, @Query("category_id") String categoryId);

    @GET("goods")
    Observable<Product> getProductById(@Query("id") Long id);
}
