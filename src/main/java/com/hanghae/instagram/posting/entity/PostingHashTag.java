package com.hanghae.instagram.posting.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PostingHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String hashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTING_ID", nullable = false)
    private Posting posting;

    @Builder
    public PostingHashTag(String hashtag, Posting posting) {
        this.hashtag = hashtag;
        this.posting = posting;

//        posting.getPostingHashTagList().add(this);
    }
}
