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
    private Long kakaoId;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String  profileImg;

    @Column
    private String username;

    @Column
    private int followerCount;

   @Column
    private int followingCount;

    @Column
    private boolean activated = true;

    @Builder
    public Member(String email, String password, String nickname, String username, String profileImg) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.username = username;
        this.profileImg = profileImg;
    }

    @Builder
    public Member(String email, Long kakaoId, String password, String nickname, String username, String profileImg) {
        this.email = email;
        this.kakaoId = kakaoId;
        this.password = password;
        this.nickname = nickname;
        this.username = username;
        this.profileImg = profileImg;
    }

    public Member kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

    public void updateFollowerCount(int followerCount){
        this.followerCount = followerCount;
    }

}
