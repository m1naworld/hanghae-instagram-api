package com.hanghae.instagram.posting.dto.showPosting;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class ShowPostingImgDto {
    private long postingImgNum;
    private String postingImg;
    private List<ShowPostingImgMemberTagDto> memberTagList;
}
