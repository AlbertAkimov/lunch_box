package com.example.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
@Entity(tableName = "user")
public class User extends AbstractModel {

    private String username;
    private String password;
    private String email;
    private String personalNumber;

}
