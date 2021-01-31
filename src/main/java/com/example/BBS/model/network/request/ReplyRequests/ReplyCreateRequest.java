package com.example.BBS.model.network.request.ReplyRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyCreateRequest {
    private String contents;

    private Long userId;

    private Long boardId;
}
