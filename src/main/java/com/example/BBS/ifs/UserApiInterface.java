package com.example.BBS.ifs;

import com.example.BBS.model.network.Header;
import com.example.BBS.model.network.request.UserApiRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public interface UserApiInterface{
    ResponseEntity create(UserApiRequest request);

    ResponseEntity read (Long id);

    ResponseEntity update (UserApiRequest request);

    ResponseEntity delete (Long id);
}
