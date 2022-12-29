package com.hanghae.instagram.posting.dto.showPosting;

import com.hanghae.instagram.comment.dto.ResponseComment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ShowPostingDetailsDto {
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
    private List<ResponseComment> commentList;
    private String profileImg;

    public ResponseShowPostingDetailsDto toResponse() {
        return ResponseShowPostingDetailsDto.builder()
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
                .commentList(commentList)
                .profileImg(profileImg)
                .build();
    }
}
