package com.example.model;

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
public class ProductCart {

    private Product product;
    private Long number;

}
