package com.lunchbox.view.product;

import android.widget.TextView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authot: Albert Akimov
 * @Date: 12.09.2021
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractView {

    protected TextView id;
}
