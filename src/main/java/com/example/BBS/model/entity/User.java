package com.example.BBS.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity // ==table
@Builder
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
//@Table(name="user") table명과 class명이 같으므로 자동으로 맵핑
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    //@Column(name = "name") Colum명과 변수명이 같으므로 자동으로 맵핑
    private String name;

    private String password;

    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList;

    @OneToMany(mappedBy = "user")
    private List<Reply> replyList;

    public static User createUser(String email, String name, String password, String phoneNumber){
        User createdUser = User.builder()
                .email(email)
                .name(name)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();

        return createdUser;
    }

    public void updatePassword(String password){
        this.password = password;
    }
    public void updatePhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
