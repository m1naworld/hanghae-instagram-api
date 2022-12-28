package com.hanghae.instagram.member.dto;

import lombok.Getter;

@Getter
public class ResponseMemberDto {
    private String profileImg;
    private String nickname;
    private int followerCount;
    private int followingCount;


    public ResponseMemberDto(String profileImg, String nickname, int followerCount, int followingCount) {
        this.profileImg = profileImg;
        this.nickname = nickname;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }
}
