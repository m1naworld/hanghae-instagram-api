package com.hanghae.instagram.posting.dto.createPosting;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RequestCreatePostingImgDto {
    private long postingImgNum;
    private String postingImg;
    private List<RequestCreatePostingImgMemberTagDto> memberTagList = new ArrayList<>();

    public CreatePostingImgDto toCreatePostingImgDto(){
        List<CreatePostingImgMemberTagDto> tmp = new ArrayList<>();
        for (int i = 0; i < memberTagList.size(); i++) {
            tmp.add(memberTagList.get(i).toCreatePostingMemberTagDto());
        }
        return CreatePostingImgDto.builder()
                .postingImgNum(postingImgNum)
                .postingImg(postingImg)
                .memberTagList(tmp)
                .build();
    }
}
