package com.example.BBS.service;

import com.example.BBS.model.entity.Board;
import com.example.BBS.model.entity.User;
import com.example.BBS.model.network.PageResponse;
import com.example.BBS.model.network.Pagination;
import com.example.BBS.model.network.request.BoardApiRequest;
import com.example.BBS.model.network.response.BoardApiResponse;
import com.example.BBS.model.network.response.UserApiResponse;
import com.example.BBS.repository.BoardRepository;
import com.example.BBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardApiLogicService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    public ResponseEntity create(BoardApiRequest request) {


        Board board = Board.builder()
                .title(request.getTitle())
                .writing(request.getWriting())
                .inquiryNumber(0)
                .updatedAt(LocalDateTime.now())
                .user(request.getUser())
                .category(request.getCategory())
                .build();

        boardRepository.save(board);

        BoardApiResponse boardApiResponse = BoardApiResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .writing(board.getWriting())
                .inquiryNumber(board.getInquiryNumber())
                .updatedAt(board.getUpdatedAt())
                .userId(board.getUser().getId())
                .categoryId(board.getCategory().getId())
                .build();

        return ResponseEntity.ok(boardApiResponse);
    }

    public ResponseEntity read(Long id) {

        return ResponseEntity.ok(boardRepository.findById(id)
                .map(board -> board.setInquiryNumber(board.getInquiryNumber()+1))
        );
    }

    public ResponseEntity update(BoardApiRequest request) {

        Optional<Board> optionalBoard = boardRepository.findById(request.getId());

        if(optionalBoard.isPresent()) {
            if (request.getUser().getId().equals(optionalBoard.get().getId())) {
                optionalBoard.get().setTitle(request.getTitle())
                        .setWriting(request.getWriting())
                        .setUpdatedAt(LocalDateTime.now());

                boardRepository.save(optionalBoard.get());
                return ResponseEntity.ok(optionalBoard.get());
            }
            else return ResponseEntity.ok("ERROR : 수정권한 없음");
        }
        else return ResponseEntity.ok("ERROR : 없는 게시물");
    }

    public ResponseEntity delete(BoardApiRequest request) {

        Optional<Board> optionalBoard = boardRepository.findById(request.getId());

        if (optionalBoard.isPresent()) {
            if(request.getUser().getId().equals(optionalBoard.get().getUser().getId())) {
                boardRepository.deleteById(request.getId());
                return ResponseEntity.ok("삭제 완료");
            } else return ResponseEntity.ok("ERROR : 삭제권한 없음");
        } else return ResponseEntity.ok("ERROR : 없는 데이터");
    }

    public ResponseEntity serach(Pageable pageable) {

        Page<Board> boards = boardRepository.findAll(pageable);

        List<Board> boardApiResponseList = boards.stream().collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(boards.getTotalPages())
                .totalElements(boards.getTotalElements())
                .currentPage(boards.getNumber())
                .currentElements(boards.getNumberOfElements())
                .build();

        PageResponse pageResponse = PageResponse.builder()
                .datas(boardApiResponseList)
                .pagination(pagination)
                .build();
        return ResponseEntity.ok(pageResponse);
    }
}
