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
public class CategoryCreateResponse {

    private LocalDateTime registeredAt;

    private String type;
}
