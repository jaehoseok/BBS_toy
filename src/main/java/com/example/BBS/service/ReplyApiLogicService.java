package com.example.BBS.service;

import com.example.BBS.model.entity.Board;
import com.example.BBS.model.entity.Reply;
import com.example.BBS.model.entity.User;
import com.example.BBS.model.network.PageResponse;
import com.example.BBS.model.network.Pagination;
import com.example.BBS.model.network.request.ReplyRequests.ReplyCreateRequest;
import com.example.BBS.model.network.request.ReplyRequests.ReplyUpdateRequest;
import com.example.BBS.model.network.response.ReplyResponses.ReplyCreateResponse;
import com.example.BBS.model.network.response.ReplyResponses.ReplyReadResponse;
import com.example.BBS.model.network.response.ReplyResponses.ReplyUpdateResponse;
import com.example.BBS.repository.BoardRepository;
import com.example.BBS.repository.ReplyRepository;
import com.example.BBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyApiLogicService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public ReplyCreateResponse create(ReplyCreateRequest request){
        User findUser = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 유저 없음"));
        Board findBoard = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 게시글 없음"));

        Reply createdReply = Reply.createReply(request.getContents(), findUser, findBoard);

        Reply savedReply = replyRepository.save(createdReply);

        ReplyCreateResponse response = ReplyCreateResponse.builder()
                .registeredAt(savedReply.getRegisteredAt())
                .contents(savedReply.getContents())
                .userId(savedReply.getUser().getId())
                .boardId(savedReply.getBoard().getId())
                .build();

        return response;
    }

    public ReplyReadResponse read(Long id){
        Reply findReply = replyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 댓글 없음"));

        ReplyReadResponse response = ReplyReadResponse.builder()
                .registeredAt(findReply.getRegisteredAt())
                .updatedAt(findReply.getUpdatedAt())
                .contents(findReply.getContents())
                .userId(findReply.getUser().getId())
                .boardId(findReply.getBoard().getId())
                .build();

        return response;
    }

    public ReplyUpdateResponse update(ReplyUpdateRequest request, Long id){

        Reply findReply = replyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 댓글 없음"));

        findReply.updateContents(request.getContents());

        ReplyUpdateResponse response = ReplyUpdateResponse.builder()
                .registeredAt(findReply.getRegisteredAt())
                .updatedAt(findReply.getUpdatedAt())
                .boardId(findReply.getBoard().getId())
                .contents(findReply.getContents())
                .userId(findReply.getUser().getId())
                .build();

        return response;
    }

    public void delete(Long id){

        Reply findReply = replyRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("ERROR : 해당 댓글 없음"));
        replyRepository.delete(findReply);
    }

    public PageResponse serach(Pageable pageable) {

        Page<Reply> replies = replyRepository.findAll(pageable);

        List<Reply> replyReadResponseList = replies.stream().collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(replies.getTotalPages())
                .totalElements(replies.getTotalElements())
                .currentPage(replies.getNumber())
                .currentElements(replies.getNumberOfElements())
                .build();

        PageResponse pageResponse = PageResponse.builder()
                .datas(replyReadResponseList)
                .pagination(pagination)
                .build();
        return pageResponse;
    }
}
