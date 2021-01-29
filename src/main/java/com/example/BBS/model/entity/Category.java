package com.example.BBS.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Accessors(chain = true)
@ToString(exclude = {"boardList"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Board> boardList;
}
