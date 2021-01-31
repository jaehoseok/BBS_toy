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
public class ReplyCreateResponse {

    private LocalDateTime registeredAt;

    private String contents;

    private Long boardId;

    private Long userId;
}
