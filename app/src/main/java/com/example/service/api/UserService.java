package com.example.service.api;

import com.example.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */


public interface UserService  {

    @GET("login/{username}/{password}")
    Single<List<User>> login(@Path("username") String username, @Path("password") String password);
}
