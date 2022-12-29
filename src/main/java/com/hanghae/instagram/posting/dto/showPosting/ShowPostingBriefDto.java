package com.hanghae.instagram.posting.dto.showPosting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShowPostingBriefDto {
    private long id;
    private String postingImg;
    private long likeCount;
    private long commentCount;
}
