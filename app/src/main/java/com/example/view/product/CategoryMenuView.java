package com.example.view.product;

import android.widget.ImageView;
import android.widget.TextView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authot: Albert Akimov
 * @Date: 14.09.2021
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryMenuView extends AbstractView {

    TextView name;
    ImageView image;
}
