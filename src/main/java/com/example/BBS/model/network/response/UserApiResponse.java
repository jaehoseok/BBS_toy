package com.example.BBS.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {

    private Long id;

    private String name;

    private String password;

    private LocalDateTime registeredAt;

    private LocalDateTime lastLoginAt;

    private String phoneNumber;

}
