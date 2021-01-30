package com.example.BBS.model.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@Accessors(chain = true)
@ToString(exclude = {"user","category"})
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String contents;

    // 연관관계의 주인을 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    // 연관관계의 주인을 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static Board createBoard(String title,String contents,User user,Category category){
        Board board = Board.builder()
                .title(title)
                .contents(contents)
                .user(user)
                .category(category)
                .build();
        return board;

    }


}
