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

    @Test
    public void create() {
        User user = User.builder()
                .name("admin")
                .password("admin")
                .registeredAt(LocalDateTime.now())
                .lastLoginAt(LocalDateTime.now())
                .phoneNumber("010-0000-0000")
                .build();

        User newUser = userRepository.save(user);
       // Assertions.assertNotNull(newUser);

    }

    @Test
    public void read() {
        Long id = 1L;
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser
                .ifPresentOrElse(
                        user -> {
                            System.out.println("ID : " + user.getId());
                            System.out.println("NAME : " + user.getName());
                            System.out.println("REGISTERED AT : " + user.getRegisteredAt());
                            System.out.println("PHONE NUMBER : " + user.getPhoneNumber());
                        },
                        () -> System.out.println("데이터 없음"));

       // Assertions.assertNotNull(optionalUser);
    }


    @Test
    public void update() {
        Long id = 1L;
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser
                .ifPresentOrElse(
                        user -> {
                            user.setLastLoginAt(LocalDateTime.now());
                            System.out.println("수정 완료");
                        },
                        () -> System.out.println("데이터 없음"));
    }

    @Test
    public void delete() {
        Long id = 1L;
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser
                .ifPresentOrElse(
                        user -> {
                            userRepository.deleteById(id);
                            System.out.println("삭제 완료");
                        },
                        () -> System.out.println("데이터 없음")
                );
    }
}
