package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Authot: Albert Akimov
 * @Date: 04.09.2021
 * @Description:
 */

public class AbstractModel {

    private Long id;

    public AbstractModel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractModel{" +
                "id=" + id +
                '}';
    }
}
