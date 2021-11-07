package com.example.service.api;

import com.example.model.Product;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Authot: Albert Akimov
 * @Date: 11.09.2021
 * @Description:
 */
public interface ProductService {

    @GET("getProductsByCategoryMenuAndDeliveryDate/{deliveryDate}/{categoryMenuId}")
    Single<List<Product>> getProductByCategoryMenuAndDeliveryDate(
            @Path("deliveryDate") String date, @Path("categoryMenuId") String categoryMenuId);

    @GET("getImageProductByCategoryMenuAndDeliveryDate/{deliveryDate}/{categoryMenuId}")
    Single<List<Product>> getImageProductByCategoryMenuAndDeliveryDate(
            @Path("deliveryDate") String date, @Path("categoryMenuId") String categoryMenuId
    );
}
