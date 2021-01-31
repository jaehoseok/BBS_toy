package com.example.BBS.model.network.response.CategoryResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryUpdateResponse {
    private LocalDateTime registeredAt;

    private LocalDateTime updatedAt;

    private String type;
}
