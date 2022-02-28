package com.lunchbox.domain.model;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> getFieldsToAsyncUpdate() {

        ArrayList<String> fields = new ArrayList<>();
        fields.add("image");

        return fields;
    }
}
