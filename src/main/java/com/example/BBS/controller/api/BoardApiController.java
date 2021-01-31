package com.example.BBS.controller.api;

import com.example.BBS.model.network.request.BoardRequests.BoardCreateRequest;
import com.example.BBS.model.network.request.BoardRequests.BoardUpdateRequest;
import com.example.BBS.service.BoardApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController{

    private final BoardApiLogicService boardApiLogicService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody BoardCreateRequest request) {
        return ResponseEntity.ok(boardApiLogicService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(boardApiLogicService.read(id));
    }

    @PutMapping("/contents/{id}")
    public ResponseEntity contentsUpdate(@RequestBody BoardUpdateRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(boardApiLogicService.contentsUpdate(request, id));
    }
    @PutMapping("/title/{id}")
    public ResponseEntity titleUpdate(@RequestBody BoardUpdateRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(boardApiLogicService.titleUpdate(request, id));
    }
    @PutMapping("/category/{id}")
    public ResponseEntity categoryUpdate(@RequestBody BoardUpdateRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(boardApiLogicService.categoryUpdate(request, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boardApiLogicService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        return ResponseEntity.ok(boardApiLogicService.serach(pageable));
    }
}
