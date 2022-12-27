package com.hanghae.instagram.posting.dto.createPosting;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RequestCreatePostingDto {
    private String contents;
    private List<RequestCreatePostingImgDto> imgList = new ArrayList<>();
    private List<String> hashtagList = new ArrayList<>();
    private List<String> membertagList  = new ArrayList<>();;

    public CreatePostingDto toCreatePostingDto() {
        List<CreatePostingImgDto> tmpImgList = new ArrayList<>();
        for (int i = 0; i < imgList.size(); i++) {
            tmpImgList.add(imgList.get(i).toCreatePostingImgDto());
        }
        List<String> tmpHashtagList = new ArrayList<>();
        for (int i = 0; i < hashtagList.size(); i++) {
            tmpHashtagList.add(hashtagList.get(i));
        }
        List<String> tmpMembertagList = new ArrayList<>();
        for (int i = 0; i < membertagList.size(); i++) {
            tmpMembertagList.add(membertagList.get(i));
        }
        return CreatePostingDto.builder()
                .contents(contents)
                .imgList(tmpImgList)
                .hashtagList(tmpHashtagList)
                .membertagList(tmpMembertagList)
                .build();
    }
}
