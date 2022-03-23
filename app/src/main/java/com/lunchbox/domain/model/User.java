package com.lunchbox.domain.model;

import androidx.room.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
@SuppressWarnings("All")
public class User extends AbstractModel {

    private String username;
    private String password;
    private String email;
    private String personalNumber;

    @Override
    public List<String> getFieldsToAsyncUpdate() {
        return new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return personalNumber != null ? personalNumber.equals(user.personalNumber) : user.personalNumber == null;
    }
}
