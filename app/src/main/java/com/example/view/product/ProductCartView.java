package com.example.view.product;

import android.widget.ImageView;
import android.widget.TextView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authot: Albert Akimov
 * @Date: 13.09.2021
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartView extends AbstractView {

    TextView name;
    TextView price;
    ImageView image;
    TextView number;
    TextView orderPrice;
    TextView total;
}
