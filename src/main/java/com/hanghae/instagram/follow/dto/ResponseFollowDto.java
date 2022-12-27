package com.hanghae.instagram.follow.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseFollowDto {

    private boolean followState;

    public ResponseFollowDto(boolean followState) {
        this.followState = followState;
    }
}
