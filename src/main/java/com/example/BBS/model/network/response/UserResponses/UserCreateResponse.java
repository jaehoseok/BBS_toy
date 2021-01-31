package com.example.BBS.model.network.response.UserResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateResponse {

    private LocalDateTime registeddAt;

    private String email;

    private String name;

    private String phoneNumber;
}
