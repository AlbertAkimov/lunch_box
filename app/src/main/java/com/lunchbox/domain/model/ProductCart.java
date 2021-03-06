package com.lunchbox.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * @Authot: Albert Akimov
 * @Date: 14.11.2021
 * @Description:
 */

@Getter
public class ProductCart extends AbstractModel {

    private final List<ElementProductCart> elements;
    private Double totalSum;

    public ProductCart() {
        this.elements = new ArrayList<>();
        this.totalSum = 0D;
    }

    public void addElement(ElementProductCart element) {
        elements.add(element);
        totalSum += element.getNumber() * element.getProduct().getPrice();
    }

    public void changeItemQuantity(ElementProductCart element, Long count) {

        if(element.getNumber() + count <= 0)
            elements.remove(element);
        else {
            element.setNumber(element.getNumber() + count);
            elements.set(elements.indexOf(element), element);
        }

        totalSum = 0D;
        for(ElementProductCart elem : elements)
            totalSum += elem.getNumber() * elem.getProduct().getPrice();
    }

    @Override
    public List<String> getFieldsToAsyncUpdate() {
        return new ArrayList<>();
    }
}
