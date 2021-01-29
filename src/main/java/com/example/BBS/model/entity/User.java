package com.example.BBS.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

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
//@Table(name="user") table명과 class명이 같으므로 자동으로 맵핑
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "name") Colum명과 변수명이 같으므로 자동으로 맵핑
    private String name;

    private String password;

    @CreatedDate
    private LocalDateTime registeredAt;

    private LocalDateTime lastLoginAt;

    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Board> boardList;
}
