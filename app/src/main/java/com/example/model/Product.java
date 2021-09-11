package com.example.model;

import lombok.Data;

/**
 * @Authot: Albert Akimov
 * @Date: 04.09.2021
 * @Description:
 */

public class Product extends AbstractModel {

    private String name;
    private Long price;
    private String image;
    private String description;

    public Product(Long id) {
        super(id);
    }

    public Product(Long id, String name, String description, Long price, String image) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
