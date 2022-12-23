package com.hanghae.instagram.like.entity;

import com.hanghae.instagram.posting.entity.Posting;

import javax.persistence.*;

@Entity
public class PostingLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTING_ID")
    private Posting posting;




}
