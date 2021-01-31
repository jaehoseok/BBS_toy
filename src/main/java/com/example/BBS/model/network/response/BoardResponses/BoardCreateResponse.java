package com.example.BBS.model.network.response.BoardResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCreateResponse {
    private LocalDateTime registeredAt;

    private String contents;

    private String title;

    private Long categoryId;

    private Long userId;
}
