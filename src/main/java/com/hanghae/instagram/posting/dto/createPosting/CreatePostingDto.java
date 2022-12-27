package com.hanghae.instagram.posting.dto.createPosting;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CreatePostingDto {
    private String contents;
    private List<CreatePostingImgDto> imgList = new ArrayList<>();
    private List<String> hashtagList = new ArrayList<>();
    private List<String> membertagList = new ArrayList<>();

    @Builder
    public CreatePostingDto(String contents, List<CreatePostingImgDto> imgList, List<String> hashtagList, List<String> membertagList) {
        this.contents = contents;
        this.imgList = imgList;
        this.hashtagList = hashtagList;
        this.membertagList = membertagList;
    }
}
