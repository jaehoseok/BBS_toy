package com.example.BBS.service;

import com.example.BBS.model.entity.Board;
import com.example.BBS.model.network.PageResponse;
import com.example.BBS.model.network.Pagination;
import com.example.BBS.model.network.request.BoardApiRequest;
import com.example.BBS.repository.BoardRepository;
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

    public ResponseEntity create(BoardApiRequest request) {

        Board board = Board.builder()
                .title(request.getTitle())
                .writing(request.getWriting())
                .inquiryNumber(request.getInquiryNumber())
                .updatedAt(LocalDateTime.now())
                .build();

        Board newBoard = boardRepository.save(board);

        return ResponseEntity.ok(newBoard);
    }

    public ResponseEntity read(Long id) {

        return ResponseEntity.ok(boardRepository.findById(id));
    }

    public ResponseEntity update(BoardApiRequest request) {

        Optional<Board> optionalBoard = boardRepository.findById(request.getId());

        optionalBoard
                .map(board -> {
                    board.setTitle(request.getTitle())
                            .setWriting(request.getWriting())
                            .setInquiryNumber(request.getInquiryNumber())
                            .setUpdatedAt(request.getUpdatedAt());

                    return board;
                })
                .map(board -> boardRepository.save(board));

        return ResponseEntity.ok(optionalBoard);
    }

    public ResponseEntity delete(Long id) {
        if (boardRepository.findById(id).isPresent()) {
            boardRepository.deleteById(id);
            return ResponseEntity.ok("");
        }
        else return ResponseEntity.ok("ERROR : 없는 데이터");
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
