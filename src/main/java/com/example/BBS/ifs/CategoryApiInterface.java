package com.example.BBS.ifs;

import com.example.BBS.model.network.Header;
import com.example.BBS.model.network.request.CategoryApiRequest;
import org.springframework.http.ResponseEntity;

public interface CategoryApiInterface {
    ResponseEntity create(CategoryApiRequest request);

    ResponseEntity read(Long id);

    ResponseEntity update(CategoryApiRequest request);

    ResponseEntity delete(Long id);
}
