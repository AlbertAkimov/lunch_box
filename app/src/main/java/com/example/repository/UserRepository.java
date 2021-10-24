package com.example.repository;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.model.User;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 24.10.2021
 * @Description:
 */

@Dao
public interface UserRepository extends CRUDRepository<User> {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(Long id);
}
