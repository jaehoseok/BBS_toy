package com.example.BBS.service;

import com.example.BBS.ifs.CategoryApiInterface;
import com.example.BBS.model.entity.Category;
import com.example.BBS.model.network.Header;
import com.example.BBS.model.network.request.CategoryApiRequest;
import com.example.BBS.model.network.response.CategoryApiResponse;
import com.example.BBS.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryApiLogicService{

    private final CategoryRepository categoryRepository;

    public ResponseEntity create(CategoryApiRequest request) {

        Category category = Category.builder()
                .type(request.getType())
                .build();

        Category newCategory = categoryRepository.save(category);

        return ResponseEntity.ok(newCategory);
    }

    public ResponseEntity read(Long id) {
        return ResponseEntity.ok(categoryRepository.findById(id));
    }

    public ResponseEntity update(CategoryApiRequest request) {

        Optional<Category> optionalCategory = categoryRepository.findById(request.getId());

       optionalCategory
                .map(category -> {
                    category.setId(request.getId())
                        .setType(request.getType());
                return category;
                })
                .map(category -> categoryRepository.save(category));

       return ResponseEntity.ok(optionalCategory);
    }

    public ResponseEntity delete(Long id) {
        if(categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("");
        }
        else return ResponseEntity.ok("ERROR : 없는 데이터");
    }
}
