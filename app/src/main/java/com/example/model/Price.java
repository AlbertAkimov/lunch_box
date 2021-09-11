package com.example.model;

import lombok.Data;

/**
 * @Authot: Albert Akimov
 * @Date: 04.09.2021
 * @Description:
 */

@Data
public class Price extends AbstractModel {

    private Double price;

    public Price(Long id, Double price) {
        super(id);
        this.price = price;
    }
}
