package com.lunchbox.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.lunchbox.domain.model.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @Authot: Albert Akimov
 * @Date: 24.10.2021
 * @Description:
 */

@Dao
public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(Long id);

    @Query("SELECT * FROM user")
    Flowable<List<User>> getAll2();
}
