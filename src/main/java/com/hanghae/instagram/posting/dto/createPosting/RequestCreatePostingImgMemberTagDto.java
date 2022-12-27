package com.hanghae.instagram.posting.dto.createPosting;

import lombok.Getter;

@Getter
public class RequestCreatePostingImgMemberTagDto {
    private String nickname;
    private long coordinateX;
    private long coordinateY;

    public CreatePostingImgMemberTagDto toCreatePostingMemberTagDto(){
        return CreatePostingImgMemberTagDto.builder()
                .nickname(nickname)
                .coordinateX(coordinateX)
                .coordinateY(coordinateY)
                .build();
    }
}
