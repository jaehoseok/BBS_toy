package com.example.BBS.controller.api;

import com.example.BBS.model.entity.User;
import com.example.BBS.model.network.request.UserApiRequest;
import com.example.BBS.model.network.request.UserUpdateRequest;
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
    public ResponseEntity create(@RequestBody UserApiRequest request) {

        User user = userApiLogicService.create(request);
        return ResponseEntity.ok(user);
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        User read = userApiLogicService.read(id);
        return ResponseEntity.ok(read);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody UserUpdateRequest request, @PathVariable Long id) {
        User updateUser = userApiLogicService.update(id, request);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userApiLogicService.delete(id);
        return ResponseEntity.ok().build();
    }
}
