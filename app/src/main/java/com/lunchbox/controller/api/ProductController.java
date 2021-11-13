package com.lunchbox.controller.api;

import com.lunchbox.domain.model.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Authot: Albert Akimov
 * @Date: 11.09.2021
 * @Description:
 */
public interface ProductController {

    @GET("getProductsByCategoryMenuAndDeliveryDate/{deliveryDate}/{categoryMenuId}")
    Observable<List<Product>> getProductByCategoryMenuAndDeliveryDate(
            @Path("deliveryDate") String date, @Path("categoryMenuId") String categoryMenuId);

    @GET("product_image/{id}")
    Observable<Product> getImageProductById(@Path("id") Long id);
}
