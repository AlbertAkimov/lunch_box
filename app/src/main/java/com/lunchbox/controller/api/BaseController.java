package com.lunchbox.controller.api;

import com.lunchbox.domain.model.AbstractModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Authot: Albert Akimov
 * @Date: 23.12.2021
 * @Description:
 */

public interface BaseController {

    @GET
    Observable<? extends AbstractModel> getById(@Query("id") Long id);
}
