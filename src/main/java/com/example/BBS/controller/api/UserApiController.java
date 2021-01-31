package com.example.BBS.controller.api;

import com.example.BBS.model.network.request.UserRequests.UserCreateRequest;
import com.example.BBS.model.network.request.UserRequests.UserUpdateRequest;
import com.example.BBS.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController{

    private final UserApiLogicService userApiLogicService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userApiLogicService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(userApiLogicService.read(id));
    }

    @PutMapping("/password/{id}")
    public ResponseEntity passwordUpdate(@RequestBody UserUpdateRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(userApiLogicService.passwordUpdate(request, id));
    }
    @PutMapping("/phoneNumber/{id}")
    public ResponseEntity phoneNumberUpdate(@RequestBody UserUpdateRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(userApiLogicService.phoneNumberUpdate(request, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userApiLogicService.delete(id);
        return ResponseEntity.ok().build();
    }
}
