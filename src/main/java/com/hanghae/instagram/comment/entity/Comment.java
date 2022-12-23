package com.hanghae.instagram.comment.entity;

import javax.persistence.*;
import java.util.List;

@Entity
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


}
