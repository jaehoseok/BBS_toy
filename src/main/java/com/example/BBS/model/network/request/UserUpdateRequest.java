package com.example.BBS.model.network.request;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String name;
    private String phoneNumber;
    private String password;
}
