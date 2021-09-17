package com.example.service.network;

import com.example.service.api.CategoryMenuService;
import lombok.Data;

/**
 * @Authot: Albert Akimov
 * @Date: 14.09.2021
 * @Description:
 */

@Data
public class CategoryMenuNetworkService extends AbstractNetworkService{

    protected CategoryMenuService service;

    public CategoryMenuNetworkService() {
        super();
        service = retrofit.create(CategoryMenuService.class);
    }
}
