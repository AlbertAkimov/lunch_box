package com.example.view.product;

import android.widget.ImageView;
import android.widget.TextView;
import com.example.model.AbstractModel;
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

    TextView name;
    TextView description;
    TextView price;
    ImageView image;
}
