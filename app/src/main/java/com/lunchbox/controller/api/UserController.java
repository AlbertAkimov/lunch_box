package com.lunchbox.controller.api;

import com.lunchbox.domain.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */


public interface UserController extends BaseController {

    @GET("login/{username}/{password}")
    Single<List<User>> login(@Path("username") String username, @Path("password") String password);

    @GET("restore/password/{email}")
    Single<List<User>> restorePasswordByEmail(@Path("email") String email);

    @POST("change/password/{password}/{recoveryCode}")
    Single<List<User>> changePasswordByRecoveryCode(@Path("password") String password, @Path("recoveryCode") String recoveryCode);

    @GET
    @Override
    Observable<User> getById(@Query("id") Long id);
}
