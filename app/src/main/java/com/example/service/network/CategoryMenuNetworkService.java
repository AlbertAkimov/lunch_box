package com.example.service.network;

import android.content.Context;

import com.example.service.api.CategoryMenuService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authot: Albert Akimov
 * @Date: 14.09.2021
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryMenuNetworkService extends AbstractNetworkService{

    protected CategoryMenuService service;

    public CategoryMenuNetworkService(Context context) {
        super(context);
        service = retrofit.create(CategoryMenuService.class);
    }
}
