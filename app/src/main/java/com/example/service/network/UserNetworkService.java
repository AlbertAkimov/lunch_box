package com.example.service.network;

import android.content.Context;

import com.example.service.api.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Authot: Albert Akimov
 * @Date: 07.10.2021
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNetworkService extends AbstractNetworkService {

    protected UserService service;

    public UserNetworkService(Context context) {
        super(context);
        service = retrofit.create(UserService.class);
    }
}
