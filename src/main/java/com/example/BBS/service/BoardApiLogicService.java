package com.example.BBS.service;

import com.example.BBS.model.entity.Board;
import com.example.BBS.model.entity.Category;
import com.example.BBS.model.entity.User;
import com.example.BBS.model.network.PageResponse;
import com.example.BBS.model.network.Pagination;
import com.example.BBS.model.network.request.BoardApiRequest;
import com.example.BBS.model.network.response.BoardApiResponse;
import com.example.BBS.model.network.response.UserApiResponse;
import com.example.BBS.repository.BoardRepository;
import com.example.BBS.repository.CategoryRepository;
import com.example.BBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardApiLogicService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Board create(BoardApiRequest request) {

        User findUser = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalStateException("찾았는데 없음"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("찾았는데 없음"));

        Board board = Board.createBoard(request.getTitle(),
                request.getContents(),
                findUser,
                category);

        Board saveBoard = boardRepository.save(board);
        return saveBoard;
    }


    @Transactional
    public Board update(Long id,BoardApiRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("찾았는데 없음"));

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("찾았는데 없음"));

        board.setCategory(category);
        return board;
    }


}
