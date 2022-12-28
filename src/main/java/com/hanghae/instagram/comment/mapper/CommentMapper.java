package com.hanghae.instagram.comment.mapper;

import com.hanghae.instagram.comment.dto.RequestComment;
import com.hanghae.instagram.comment.dto.ResponseComment;
import com.hanghae.instagram.comment.entity.Comment;
import com.hanghae.instagram.posting.entity.Posting;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toDepthZeroComment(Posting posting, RequestComment requestDto, Long memberId){
        return Comment.builder()
                .content(requestDto.getComment())
                .nickName(requestDto.getNickname())
                .postingId(posting)
                .memberId(memberId)
                .build();
    }
}
