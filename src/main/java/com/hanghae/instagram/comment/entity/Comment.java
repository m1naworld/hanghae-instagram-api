package com.hanghae.instagram.comment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    private int likeCount;
    
    private Long parentCommentId;

    private String posting;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> commentList;


    public void updateLikeCount(int likeCount){
        this.likeCount = likeCount;
    }
}
