package com.example.BBS.controller.api;

import com.example.BBS.ifs.UserApiInterface;
import com.example.BBS.model.network.Header;
import com.example.BBS.model.network.request.UserApiRequest;
import com.example.BBS.model.network.response.UserApiResponse;
import com.example.BBS.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController implements UserApiInterface{

    private final UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")
    public ResponseEntity create(@RequestBody UserApiRequest request) {
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable(name = "id") Long id) {
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public ResponseEntity update(@RequestBody UserApiRequest request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userApiLogicService.delete(id);
        return ResponseEntity.ok("");
    }
}
