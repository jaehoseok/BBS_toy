package com.example.BBS.service;

import com.example.BBS.model.entity.Board;
import com.example.BBS.model.entity.Category;
import com.example.BBS.model.entity.User;
import com.example.BBS.model.network.PageResponse;
import com.example.BBS.model.network.Pagination;
import com.example.BBS.model.network.request.BoardRequests.BoardCreateRequest;
import com.example.BBS.model.network.request.BoardRequests.BoardUpdateRequest;
import com.example.BBS.model.network.response.BoardResponses.BoardCreateResponse;
import com.example.BBS.model.network.response.BoardResponses.BoardReadResponse;
import com.example.BBS.model.network.response.BoardResponses.BoardUpdateResponse;
import com.example.BBS.repository.BoardRepository;
import com.example.BBS.repository.CategoryRepository;
import com.example.BBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardApiLogicService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public BoardCreateResponse create(BoardCreateRequest request) {

        User findUser = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 없는 유저"));
        Category findCategory = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 없는 카테고리"));

        Board createdBoard = Board.createBoard(
                request.getTitle(),
                request.getContents(),
                findUser,
                findCategory
        );
        Board savedBoard = boardRepository.save(createdBoard);

        BoardCreateResponse response = BoardCreateResponse.builder()
                .registeredAt(savedBoard.getRegisteredAt())
                .contents(savedBoard.getContents())
                .title(savedBoard.getTitle())
                .categoryId(savedBoard.getCategory().getId())
                .userId(savedBoard.getUser().getId())
                .build();

        return response;
    }

    public BoardReadResponse read(Long id) {
        Board findboard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 게시글 없음"));

        BoardReadResponse response = BoardReadResponse.builder()
                .registeredAt(findboard.getRegisteredAt())
                .updatedAt(findboard.getUpdatedAt())
                .contents(findboard.getContents())
                .title(findboard.getTitle())
                .categoryId(findboard.getCategory().getId())
                .userId(findboard.getUser().getId())
                .build();

        return response;
    }

    @Transactional
    public BoardUpdateResponse contentsUpdate(BoardUpdateRequest request, Long id) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 게시글 없음"));

        findBoard.updateContents(request.getContents());

        BoardUpdateResponse updatedBoard = BoardUpdateResponse.builder()
                .registeredAt(findBoard.getRegisteredAt())
                .updatedAt(findBoard.getUpdatedAt())
                .contents(findBoard.getContents())
                .title(findBoard.getTitle())
                .categoryId(findBoard.getCategory().getId())
                .userId(findBoard.getUser().getId())
                .build();

        return updatedBoard;
    }
    @Transactional
    public BoardUpdateResponse titleUpdate(BoardUpdateRequest request, Long id) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 게시글 없음"));

        findBoard.updateTitle(request.getTitle());

        BoardUpdateResponse updatedBoard = BoardUpdateResponse.builder()
                .registeredAt(findBoard.getRegisteredAt())
                .updatedAt(findBoard.getUpdatedAt())
                .contents(findBoard.getContents())
                .title(findBoard.getTitle())
                .categoryId(findBoard.getCategory().getId())
                .userId(findBoard.getUser().getId())
                .build();
        return updatedBoard;
    }
    @Transactional
    public BoardUpdateResponse categoryUpdate(BoardUpdateRequest request, Long id) {
        Category findCategory = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 카테고리 없음"));
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 게시글 없음"));

        findBoard.updateCategory(findCategory);

        BoardUpdateResponse updatedBoard = BoardUpdateResponse.builder()
                .registeredAt(findBoard.getRegisteredAt())
                .updatedAt(findBoard.getUpdatedAt())
                .contents(findBoard.getContents())
                .title(findBoard.getTitle())
                .categoryId(findBoard.getCategory().getId())
                .userId(findBoard.getUser().getId())
                .build();

        return updatedBoard;
    }

    @Transactional
    public void delete(Long id) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 게시글 없음"));

        boardRepository.delete(findBoard);
    }

    public PageResponse serach(Pageable pageable) {

        Page<Board> boards = boardRepository.findAll(pageable);

        List<Board> boardReadResponseList = boards.stream().collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(boards.getTotalPages())
                .totalElements(boards.getTotalElements())
                .currentPage(boards.getNumber())
                .currentElements(boards.getNumberOfElements())
                .build();

        PageResponse pageResponse = PageResponse.builder()
                .datas(boardReadResponseList)
                .pagination(pagination)
                .build();
        return pageResponse;
    }
}
