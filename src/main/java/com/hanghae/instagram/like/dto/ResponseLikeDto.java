package com.hanghae.instagram.like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseLikeDto {

    private boolean like;
    private int likeCount;

    public ResponseLikeDto(boolean like, int likeCount) {
        this.like = like;
        this.likeCount = likeCount;
    }
}
