package com.example.BBS.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // ==table
@Builder
@Accessors(chain = true)
@ToString(exclude = {"boardList"})
@EntityListeners(AuditingEntityListener.class)
//@Table(name="user") table명과 class명이 같으므로 자동으로 맵핑
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    private String phoneNumber;

    // 연관관계의 거울
    @OneToMany(mappedBy = "user")
    private List<Board> boardList;



}
