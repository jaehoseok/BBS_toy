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
public class UserReadResponse {
    private LocalDateTime registeddAt;

    private LocalDateTime updatedAt;

    private String email;

    private String name;
}
