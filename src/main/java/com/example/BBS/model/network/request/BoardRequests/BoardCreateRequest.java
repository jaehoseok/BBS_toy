package com.example.BBS.model.network.request.BoardRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCreateRequest {

    private String title;

    private String contents;

    private Long userId;

    private Long categoryId;

}
