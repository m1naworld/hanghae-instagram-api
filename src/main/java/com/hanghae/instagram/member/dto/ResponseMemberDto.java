package com.hanghae.instagram.member.dto;

import lombok.Getter;

@Getter
public class ResponseMemberDto {
    private String profileImg;
    private String nickname;

    public ResponseMemberDto(String profileImg, String nickname) {
        this.profileImg = profileImg;
        this.nickname = nickname;
    }
}
