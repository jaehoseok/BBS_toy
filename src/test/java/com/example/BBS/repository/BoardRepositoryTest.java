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

}
