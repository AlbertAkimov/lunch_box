package com.lunchbox.controller.api;

import com.lunchbox.domain.model.CategoryMenu;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Authot: Albert Akimov
 * @Date: 14.09.2021
 * @Description:
 */

public interface CategoryController extends BaseController {

    @GET("production_plan")
    Observable<List<CategoryMenu>> getCategoriesByDate(@Query("date") String date);

    @Override
    @GET("category")
    Observable<CategoryMenu> getById(@Query("id") Long id);
}
