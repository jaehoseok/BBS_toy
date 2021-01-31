package com.example.BBS.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String contents;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "board")
    private List<Reply> replyList;

    public static Board createBoard(String title, String contents, User user, Category category){
        Board createdBoard = Board.builder()
                .title(title)
                .contents(contents)
                .user(user)
                .category(category)
                .build();

        return createdBoard;
    }
    public void updateContents(String contents){
        this.contents = contents;
    }

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateCategory(Category category){
        this.category = category;
    }
}
