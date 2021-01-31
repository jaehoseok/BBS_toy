package com.example.BBS.model.network.response.ReplyResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyReadResponse {
    private LocalDateTime registeredAt;

    private LocalDateTime updatedAt;

    private String contents;

    private Long boardId;

    private Long userId;
}
