package com.example.model;

import lombok.Data;

import java.net.URL;

/**
 * @Authot: Albert Akimov
 * @Date: 04.09.2021
 * @Description:
 */

@Data
public class Product extends AbstractModel {

    private String nameProduct;
    private String description;
    private Price price;
    private String image;
}
