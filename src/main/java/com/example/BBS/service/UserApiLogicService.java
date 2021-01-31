package com.example.BBS.service;

import com.example.BBS.model.entity.User;
import com.example.BBS.model.network.request.UserRequests.UserCreateRequest;
import com.example.BBS.model.network.request.UserRequests.UserUpdateRequest;
import com.example.BBS.model.network.response.UserResponses.UserCreateResponse;
import com.example.BBS.model.network.response.UserResponses.UserReadResponse;
import com.example.BBS.model.network.response.UserResponses.UserUpdateResponse;
import com.example.BBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApiLogicService {

    private final UserRepository userRepository;


    public UserCreateResponse create(UserCreateRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new IllegalStateException("ERROR : 이메일 중복");

        User createdUser = User.createUser(request.getEmail(), request.getName(),request.getPassword(),request.getPhoneNumber());
        User saveUser = userRepository.save(createdUser);

        UserCreateResponse response = UserCreateResponse.builder()
                .email(saveUser.getEmail())
                .name(saveUser.getName())
                .phoneNumber(saveUser.getPhoneNumber())
                .registeddAt(saveUser.getRegisteredAt())
                .build();
        return response;
    }

    public UserReadResponse read(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 유저 없음"));

        UserReadResponse response = UserReadResponse.builder()
                .email(findUser.getEmail())
                .name(findUser.getName())
                .registeddAt(findUser.getRegisteredAt())
                .updatedAt(findUser.getUpdatedAt())
                .build();

        return response;
    }

    public UserUpdateResponse passwordUpdate(UserUpdateRequest request, Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 유저 없음"));

        findUser.updatePassword(request.getPassword());

        UserUpdateResponse response = UserUpdateResponse.builder()
                .email(findUser.getEmail())
                .name(findUser.getName())
                .registeddAt(findUser.getRegisteredAt())
                .updatedAt(findUser.getUpdatedAt())
                .build();
        return response;
    }
    public UserUpdateResponse phoneNumberUpdate(UserUpdateRequest request, Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 유저 없음"));

        findUser.updatePhoneNumber(request.getPhoneNumber());

        UserUpdateResponse response = UserUpdateResponse.builder()
                .email(findUser.getEmail())
                .name(findUser.getName())
                .registeddAt(findUser.getRegisteredAt())
                .updatedAt(findUser.getUpdatedAt())
                .build();
        return response;
    }

    public void delete(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 유저 없음"));
        userRepository.delete(findUser);
    }

}
