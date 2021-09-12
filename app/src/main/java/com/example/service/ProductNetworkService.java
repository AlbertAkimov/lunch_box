package com.example.service;

import com.example.service.interfaces.ProductService;
import lombok.Data;

/**
 * @Authot: Albert Akimov
 * @Date: 12.09.2021
 * @Description:
 */

@Data
public class ProductNetworkService extends AbstractNetworkService {

    protected ProductService service;

    public ProductNetworkService() {
        super();
        service = retrofit.create(ProductService.class);
    }
}
