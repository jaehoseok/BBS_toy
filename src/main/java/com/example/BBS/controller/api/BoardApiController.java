package com.example.BBS.controller.api;

import com.example.BBS.model.entity.Board;
import com.example.BBS.model.network.request.BoardApiRequest;
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
public class BoardApiController {

    private final BoardApiLogicService boardApiLogicService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody BoardApiRequest request) {
        Board board = boardApiLogicService.create(request);
        return ResponseEntity.ok(board);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody BoardApiRequest request,@PathVariable Long id) {
        Board update = boardApiLogicService.update(id, request);
        return ResponseEntity.ok(update);
    }
}
