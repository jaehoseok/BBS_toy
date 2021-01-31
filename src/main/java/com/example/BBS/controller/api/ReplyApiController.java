package com.example.BBS.controller.api;

import com.example.BBS.model.network.request.ReplyRequests.ReplyCreateRequest;
import com.example.BBS.model.network.request.ReplyRequests.ReplyUpdateRequest;
import com.example.BBS.service.ReplyApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyApiLogicService replyApiLogicService;

    @PostMapping("")
    public ResponseEntity create (@RequestBody ReplyCreateRequest request){
        return ResponseEntity.ok(replyApiLogicService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity read (@PathVariable long id){
        return ResponseEntity.ok(replyApiLogicService.read(id));
    }

    @PutMapping("{id}")
    public ResponseEntity update (@RequestBody ReplyUpdateRequest request, @PathVariable Long id){
        return ResponseEntity.ok(replyApiLogicService.update(request, id));
    }

    @DeleteMapping("{id")
    public ResponseEntity delete (@PathVariable Long id){
        replyApiLogicService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(replyApiLogicService.serach(pageable));
    }


}
