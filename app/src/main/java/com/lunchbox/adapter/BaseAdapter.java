package com.lunchbox.adapter;

import com.lunchbox.domain.model.AbstractModel;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 29.11.2021
 * @Description:
 */

public interface BaseAdapter<T extends AbstractModel> {

    void setData(List<T> data);

    void updateData(T data);
}
