package com.hanghae.instagram.comment.mapper;

import com.hanghae.instagram.comment.dto.RequestComment;
import com.hanghae.instagram.comment.entity.Comment;
import com.hanghae.instagram.posting.entity.Posting;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toDepthZeroComment(Posting posting, RequestComment requestDto, Long id,String nickName){
        return Comment.builder()
                .content(requestDto.getComment())
                .nickName(nickName)
                .postingId(posting)
                .memberId(id)
                .build();
    }
}
