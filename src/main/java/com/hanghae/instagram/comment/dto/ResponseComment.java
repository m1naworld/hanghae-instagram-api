package com.hanghae.instagram.comment.dto;

import com.hanghae.instagram.comment.entity.Comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseComment {

    private Long postingId;

    private Long commentId;

    private String nickName;

    private String comment;

    private String profileImg;

    private LocalDateTime createdAt;
    private int likeCount;

    public ResponseComment(Long id, Comment comment){
        this.postingId = id;
        this.commentId = comment.getId();
        this.nickName = comment.getNickName();
        this.comment = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.profileImg = comment.getProfileImg();
        this.likeCount = comment.getLikeCount();
    }
}
