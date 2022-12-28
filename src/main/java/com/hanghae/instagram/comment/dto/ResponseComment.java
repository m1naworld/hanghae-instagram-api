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

    private LocalDateTime createdAt;

    public ResponseComment(Long id, Comment comment){
        this.postingId = id;
        this.commentId = comment.getId();
        this.nickName = comment.getNickName();
        this.comment = comment.getContent();
        this.createdAt = getCreatedAt();
    }
}
