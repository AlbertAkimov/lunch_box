package com.lunchbox.domain.model;

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
public class ElementProductCart {

    private Product product;
    private Long number;

}
