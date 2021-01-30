package com.example.BBS.service;

import com.example.BBS.model.entity.User;
import com.example.BBS.model.network.request.UserApiRequest;
import com.example.BBS.model.network.request.UserUpdateRequest;
import com.example.BBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserApiLogicService {

    private final UserRepository userRepository;
    private final EntityManager em;

    @Transactional
    public User create(UserApiRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalStateException("이미 있는 이메일입니다.");
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();

        User save = userRepository.save(user);
        return save;
    }

    public User read(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("찾았는데 없음"));
        return user;
    }


    @Transactional
    public User update(Long id, UserUpdateRequest request) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("찾았는데 없음"));

        findUser.setName(request.getName());
        findUser.setPhoneNumber(request.getPhoneNumber());
        findUser.setPassword(request.getPassword());
        return findUser;
    }

    public void delete(Long id) {

        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("찾았는데 없음"));

        userRepository.delete(findUser);
    }

}
