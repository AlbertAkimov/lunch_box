package com.example.service.api;

import com.example.model.CategoryMenu;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 14.09.2021
 * @Description:
 */

public interface CategoryMenuService {

    @GET("category_menu/{deliveryDate}")
    Single<List<CategoryMenu>> getCategoryMenuByDeliveryDate(@Path("deliveryDate") String date);
}
