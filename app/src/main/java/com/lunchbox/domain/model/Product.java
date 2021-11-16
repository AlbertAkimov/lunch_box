package com.lunchbox.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authot: Albert Akimov
 * @Date: 04.09.2021
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product extends AbstractModel {

    private String name;
    private Long price;
    private String image;
    private String description;

}
