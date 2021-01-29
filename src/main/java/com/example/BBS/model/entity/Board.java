package com.example.BBS.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Accessors(chain = true)
@ToString(exclude = {"user","category"})
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String writing;

    private Integer inquiryNumber;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;
}
