package com.hanghae.instagram.posting.dto.showPosting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShowPostingImgMemberTagDto {
    private String nickname;
    private long coordinateX;
    private long coordinateY;
}
