package com.example.BBS.controller.api;

import com.example.BBS.model.network.Header;
import com.example.BBS.model.network.request.CategoryApiRequest;
import com.example.BBS.model.network.response.CategoryApiResponse;
import com.example.BBS.service.CategoryApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryApiController {

    @Autowired
    private CategoryApiLogicService categoryApiLogicService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody CategoryApiRequest request) {
        return categoryApiLogicService.create(request);
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return categoryApiLogicService.read(id);
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody CategoryApiRequest request) {
        return categoryApiLogicService.update(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryApiLogicService.delete(id);
        return ResponseEntity.ok("");
    }
}
