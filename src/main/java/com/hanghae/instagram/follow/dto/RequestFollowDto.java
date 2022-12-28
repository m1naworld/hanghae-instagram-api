package com.hanghae.instagram.follow.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestFollowDto {

    private boolean followState;
    private String following;
    private String follower;
}
