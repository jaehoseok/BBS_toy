package com.example.BBS.repository;

import com.example.BBS.BbsApplicationTests;
import com.example.BBS.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends BbsApplicationTests {

    @Autowired
    private UserRepository userRepository;

}
