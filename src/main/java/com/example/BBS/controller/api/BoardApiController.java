package com.example.BBS.controller.api;

import com.example.BBS.ifs.BoardApiInterface;
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
public class BoardApiController implements BoardApiInterface{

    private final BoardApiLogicService boardApiLogicService;

    @Override
    @PostMapping("")
    public ResponseEntity create(@RequestBody BoardApiRequest request) {
        return boardApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return boardApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public ResponseEntity update(@RequestBody BoardApiRequest request) {
        return boardApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boardApiLogicService.delete(id);
        return ResponseEntity.ok("");
    }

    @Override
    @GetMapping("")
    public ResponseEntity search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        return boardApiLogicService.serach(pageable);
    }
}
