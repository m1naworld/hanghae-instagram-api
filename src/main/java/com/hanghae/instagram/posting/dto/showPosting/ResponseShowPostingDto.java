package com.hanghae.instagram.posting.dto.showPosting;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseShowPostingDto {
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

    @Builder
    public ResponseShowPostingDto(long id, String contents, long likeCount, String nickname, long commentCount, boolean postingLike, List<String> hashtagList, List<String> membertagList, String createdAt, String modifiedAt, List<ShowPostingImgDto> imgList) {
        this.id = id;
        this.contents = contents;
        this.likeCount = likeCount;
        this.nickname = nickname;
        this.commentCount = commentCount;
        this.postingLike = postingLike;
        this.hashtagList = hashtagList;
        this.membertagList = membertagList;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.imgList = imgList;
    }
}
