package com.example.model;

import androidx.room.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractModel {

    private String username;
    private String email;
    private String personalNumber;

}
