package com.lunchbox.domain.model;

import androidx.room.PrimaryKey;

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
public abstract class AbstractModel {

    @PrimaryKey(autoGenerate = false)
    private Long id;

    public abstract List<String> getFieldsToAsyncUpdate();
}
