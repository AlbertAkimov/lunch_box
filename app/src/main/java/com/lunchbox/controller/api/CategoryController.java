package com.lunchbox.controller.api;

import com.lunchbox.domain.model.CategoryMenu;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Authot: Albert Akimov
 * @Date: 14.09.2021
 * @Description:
 */

public interface CategoryController {

    @GET("category_menu/{deliveryDate}")
    Observable<List<CategoryMenu>> getDataByDeliveryDate(@Path("deliveryDate") String date);

    @GET("category_menu_image/{id}")
    Observable<CategoryMenu> getImageCategoryMenuById(@Path("id") Long id);
}
