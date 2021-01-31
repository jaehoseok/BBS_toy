package com.example.BBS.controller.api;

import com.example.BBS.model.network.request.CategoryRequests.CategoryCreateRequest;
import com.example.BBS.model.network.request.CategoryRequests.CategoryUpdateRequest;
import com.example.BBS.service.CategoryApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApiController{

    private final CategoryApiLogicService categoryApiLogicService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody CategoryCreateRequest request) {
        return ResponseEntity.ok(categoryApiLogicService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(categoryApiLogicService.read(id));
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody CategoryUpdateRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(categoryApiLogicService.update(request, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryApiLogicService.delete(id);
        return ResponseEntity.ok().build();
    }
}
