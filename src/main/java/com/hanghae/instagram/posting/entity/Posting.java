package com.hanghae.instagram.posting.entity;

import com.hanghae.instagram.comment.entity.Comment;
import com.hanghae.instagram.common.entity.Timestamped;
import com.hanghae.instagram.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Posting extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String contents;
    
    @Column(nullable = false)
    private int likeCount;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "posting", fetch = FetchType.LAZY)
    private List<PostingHashTag> postingHashTagList = new ArrayList<>();

    @OneToMany(mappedBy = "posting", fetch = FetchType.LAZY)
    private List<PostingMemberTag> postingMemberTagList = new ArrayList<>();

    @OneToMany(mappedBy = "posting", fetch = FetchType.LAZY)
    private List<PostingImg> postingImgList = new ArrayList<>();

    @OneToMany(mappedBy = "posting", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();
}