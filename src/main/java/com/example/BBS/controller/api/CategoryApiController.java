package com.example.BBS.controller.api;

import com.example.BBS.ifs.CategoryApiInterface;
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
public class CategoryApiController implements CategoryApiInterface{

    @Autowired
    private CategoryApiLogicService categoryApiLogicService;

    @Override
    @PostMapping("")
    public ResponseEntity create(@RequestBody CategoryApiRequest request) {
        return categoryApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return categoryApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public ResponseEntity update(@RequestBody CategoryApiRequest request) {
        return categoryApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryApiLogicService.delete(id);
        return ResponseEntity.ok("");
    }
}
