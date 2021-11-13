package com.lunchbox.view;

import android.widget.ImageView;
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
@AllArgsConstructor
@NoArgsConstructor
public class ProductView extends AbstractView {

    TextView productName;
    TextView productDescription;
    TextView productPrice;
    ImageView productImage;
}
