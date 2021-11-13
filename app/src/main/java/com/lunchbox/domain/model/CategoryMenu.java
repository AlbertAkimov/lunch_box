package com.lunchbox.domain.model;

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
public class CategoryMenu extends AbstractModel {

    private String categoryMenuName;
    private String categoryMenuImage;
    private String date;
}
