package com.example.BBS.model.network.request;

import com.example.BBS.model.entity.Category;
import com.example.BBS.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardApiRequest {

    private String title;

    private String contents;

    private Long userId;

    private Long categoryId;
}
