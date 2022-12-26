package com.hanghae.instagram.comment.entity;

import com.hanghae.instagram.comment.dto.RequestComment;
import com.hanghae.instagram.common.entity.Timestamped;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    private int likeCount;

    private String nickname;

    private Long parentCommentId;

    private String posting;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> commentList;

    public Comment(Long postingId, RequestComment requestDto) {
        super();
        this.contents = requestDto.getComment();
        this.nickname = requestDto.getNickname();
        this.posting = getPosting();
        this.likeCount = getLikeCount();
        this.parentCommentId = getParentCommentId();
        this.id = postingId;

    }
}
