package com.example.BBS.ifs;

import com.example.BBS.model.entity.User;
import com.example.BBS.model.network.Header;
import com.example.BBS.model.network.request.BoardApiRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface BoardApiInterface {

    ResponseEntity create(BoardApiRequest request);

    ResponseEntity read (Long id);

    ResponseEntity update (BoardApiRequest request);

    ResponseEntity delete (BoardApiRequest request);

    ResponseEntity search(Pageable pageable);
}
