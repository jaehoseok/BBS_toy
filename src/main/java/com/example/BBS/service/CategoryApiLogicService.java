package com.example.BBS.service;

import com.example.BBS.model.entity.Category;
import com.example.BBS.model.network.request.CategoryRequests.CategoryCreateRequest;
import com.example.BBS.model.network.request.CategoryRequests.CategoryUpdateRequest;
import com.example.BBS.model.network.response.CategoryResponses.CategoryCreateResponse;
import com.example.BBS.model.network.response.CategoryResponses.CategoryReadResponse;
import com.example.BBS.model.network.response.CategoryResponses.CategoryUpdateResponse;
import com.example.BBS.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryApiLogicService{

    private final CategoryRepository categoryRepository;

    public CategoryCreateResponse create(CategoryCreateRequest request) {

        Category createdCategory = Category.createCategory(request.getType());

        Category savedCategory = categoryRepository.save(createdCategory);

        CategoryCreateResponse response = CategoryCreateResponse.builder()
                .registeredAt(savedCategory.getRegisteredAt())
                .type(savedCategory.getType())
                .build();

        return response;

    }

    public CategoryReadResponse read(Long id) {
        Category findCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 카테고리 없음"));

        CategoryReadResponse response = CategoryReadResponse.builder()
                .registeredAt(findCategory.getRegisteredAt())
                .updatedAt(findCategory.getUpdatedAt())
                .type(findCategory.getType())
                .build();

        return response;
    }

    public CategoryUpdateResponse update(CategoryUpdateRequest request, Long id) {

        Category findCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 카테고리 없음"));

        findCategory.updateType(request.getType());

        CategoryUpdateResponse response = CategoryUpdateResponse.builder()
                .registeredAt(findCategory.getRegisteredAt())
                .updatedAt(findCategory.getUpdatedAt())
                .type(findCategory.getType())
                .build();
        return response;
    }

    public void delete(Long id) {
        Category findCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ERROR : 해당 카테고리 없음"));

        categoryRepository.delete(findCategory);
    }
}
