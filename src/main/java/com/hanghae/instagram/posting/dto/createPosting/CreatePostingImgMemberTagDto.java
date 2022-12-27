package com.hanghae.instagram.posting.dto.createPosting;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreatePostingImgMemberTagDto {
    private String nickname;
    private long coordinateX;
    private long coordinateY;

    @Builder
    public CreatePostingImgMemberTagDto(String nickname, long coordinateX, long coordinateY) {
        this.nickname = nickname;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }
}
