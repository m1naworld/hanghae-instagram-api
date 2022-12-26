package com.hanghae.instagram.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.persistence.*;
import javax.sql.DataSource;

@Entity(name = "member")
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column
    private String profileImg;

    @Column
    private String introduce;

    @Column
    private int followerCount;

   @Column
    private int followingCount;

    @Column
    private boolean activated = true;

    @Builder
    public Member(String email, String password, String nickname, String introduce) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.introduce = introduce;
//        this.activated = activated;
    }


}
