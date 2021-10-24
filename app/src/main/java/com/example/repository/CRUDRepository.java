package com.example.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.model.AbstractModel;

/**
 * @Authot: Albert Akimov
 * @Date: 24.10.2021
 * @Description:
 */

@Dao
public interface CRUDRepository<E extends AbstractModel> {

    @Insert
    void save(E entity);

    @Update
    void update(E entity);

    @Delete
    void delete(E entity);
}
