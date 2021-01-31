package com.example.BBS.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String type;

    @OneToMany(mappedBy = "category")
    private List<Board> boardList;

    public static Category createCategory(String type){
        Category createCategory = Category.builder()
                .type(type)
                .build();
        return createCategory;
    }

    public void updateType(String type){
        this.type = type;
    }
}
