package com.hanghae.instagram.comment.dto;

import com.hanghae.instagram.comment.entity.Comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseComment {

    private Long Id;

    private Long parentCommentId;

    private Long postingId;

    private String nickname;

    private String comment;

    private LocalDateTime createdAt;

    public ResponseComment(Long id, Comment comment){
        this.Id = id;
        this.createdAt = comment.getCreatedAt();
        this.parentCommentId = comment.getParentCommentId();
        this.nickname = comment.getNickname();
        this.comment = this.getComment();
        this.postingId = getPostingId();
    }
}
