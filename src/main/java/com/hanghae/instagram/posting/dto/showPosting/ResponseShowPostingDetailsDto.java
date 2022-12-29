package com.hanghae.instagram.posting.dto.showPosting;

import com.hanghae.instagram.comment.dto.ResponseComment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseShowPostingDetailsDto {
    @Getter
    @Builder
    static private class PostDetail {
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
        private String profileImg;
    }
    private PostDetail postDetail;
    private List<ShowPostingImgDto> imgList;
    private List<ResponseComment> commentList;

    @Builder
    public ResponseShowPostingDetailsDto(long id, String contents, long likeCount, String nickname, long commentCount, boolean postingLike, List<String> hashtagList, List<String> membertagList, String createdAt, String modifiedAt, List<ShowPostingImgDto> imgList, List<ResponseComment> commentList, String profileImg) {
        this.postDetail = new PostDetail(id, contents, likeCount, nickname, commentCount, postingLike,
                hashtagList, membertagList, createdAt, modifiedAt, profileImg);
        this.imgList = imgList;
        this.commentList = commentList;
    }
}
