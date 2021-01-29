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
public class BoardApiResponse {
    private Long id;

    private String title;

    private String writing;

    private Integer inquiryNumber;

    private LocalDateTime updatedAt;

    private Long userId;

    private Long categoryId;
}
