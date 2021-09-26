package com.example.service.network;

import android.content.Context;

import com.example.service.api.ProductService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authot: Albert Akimov
 * @Date: 12.09.2021
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductNetworkService extends AbstractNetworkService {

    protected ProductService service;

    public ProductNetworkService(Context context) {
        super(context);
        service = retrofit.create(ProductService.class);
    }
}
