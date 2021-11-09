package com.example.service;

import android.content.Context;

import com.example.database.AppDatabase;

import lombok.Data;

/**
 * @Authot: Albert Akimov
 * @Date: 09.11.2021
 * @Description:
 */

@Data
public abstract class BaseService<C, R> {

    protected C controller;
    protected R repository;
    protected Context context;

    public BaseService(Context context, Class<?> classController, Class<?> classRepository) {
        this.context = context;
        controller = WebServiceHelper.getInstance(context).create(classController);
        repository = AppDatabase.getInstance(context).getRepository(classRepository);
    }
}
