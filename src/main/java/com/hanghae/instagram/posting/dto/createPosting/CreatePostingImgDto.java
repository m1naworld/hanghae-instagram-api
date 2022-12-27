package com.hanghae.instagram.posting.dto.createPosting;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class CreatePostingImgDto {
    private long postingImgNum;
    private String postingImg;
    private List<CreatePostingImgMemberTagDto> memberTagList = new ArrayList<>();

    @Builder
    public CreatePostingImgDto(long postingImgNum, String postingImg, List<CreatePostingImgMemberTagDto> memberTagList) {
        this.postingImgNum = postingImgNum;
        this.postingImg = postingImg;
        this.memberTagList = memberTagList;
    }
}
