package com.example.BBS.service;

import com.example.BBS.model.entity.User;
import com.example.BBS.model.network.request.UserApiRequest;
import com.example.BBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApiLogicService {

    private final UserRepository userRepository;


    public ResponseEntity create(UserApiRequest request) {

        if(userRepository.findByPhoneNumber(request.getPhoneNumber())==null) {

            User user = User.builder()
                    .name(request.getName())
                    .password(request.getPassword())
                    .registeredAt(LocalDateTime.now())
                    .lastLoginAt(LocalDateTime.now())
                    .phoneNumber(request.getPhoneNumber())
                    .build();

            User newUser = userRepository.save(user);

            return ResponseEntity.ok(newUser);
        }
        else return ResponseEntity.ok("ERROR : 중복된 NAME");
    }

    public ResponseEntity read(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser
                .map(user -> user.setLastLoginAt(LocalDateTime.now()));
        return ResponseEntity.ok(optionalUser);
    }

    public ResponseEntity update(UserApiRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getId());

        if(optionalUser.isPresent()) {
            if(request.getId().equals(optionalUser.get().getId())) {
                optionalUser
                        .map(user -> {
                            user.setName(request.getName())
                                    .setPassword(request.getPassword())
                                    .setPhoneNumber(request.getPhoneNumber());
                            return user;
                        })
                        .map(user -> userRepository.save(user));

                return ResponseEntity.ok(optionalUser);
            } else return ResponseEntity.ok("ERROR : 수정권한 없음");
        }
        else return ResponseEntity.ok("ERROR : 없는 유저");
    }

    public ResponseEntity delete(UserApiRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getId());

        if(optionalUser.isPresent()) {
            if(request.getId().equals(optionalUser.get().getId())) {
                userRepository.deleteById(request.getId());
                return ResponseEntity.ok("삭제 완료");
            } else return ResponseEntity.ok("ERROR : 삭제권한 없음");
        }
        else return ResponseEntity.ok("ERROR : 없는 데이터");
    }

}
