package com.hanghae.instagram.posting.dto.showPosting;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ShowPostingDto {
    private long id;
    private String contents;
    private long likeCount;
    private String nickname;
    private long commentCount;
    private boolean postingLike;
    private List<String> hashtagList;
    private List<String> membertagList;
    private String createdAt;
    private String modifiedAt;
    private List<ShowPostingImgDto> imgList;
    private String profileImg;

    public ResponseShowPostingDto toResponse(){
        return ResponseShowPostingDto.builder()
                .id(id)
                .contents(contents)
                .likeCount(likeCount)
                .nickname(nickname)
                .commentCount(commentCount)
                .postingLike(postingLike)
                .hashtagList(hashtagList)
                .membertagList(membertagList)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .imgList(imgList)
                .profileImg(profileImg)
                .build();
    }
}
