package com.example.BBS.repository;

import com.example.BBS.BbsApplicationTests;
import com.example.BBS.model.entity.Board;
import com.example.BBS.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class BoardRepositoryTest extends BbsApplicationTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void create() {
        Board board = Board.builder()
                .inquiryNumber(0)
                .title("1")
                .updatedAt(LocalDateTime.now())
                .writing("1")
                .build();

        Board newBoard = boardRepository.save(board);
        // Assertions.assertNotNull(newUser);

    }
}
