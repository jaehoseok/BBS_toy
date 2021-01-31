package com.example.BBS.model.network.request.UserRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {

    private String email;

    private String name;

    private String password;

    private String phoneNumber;
}
