package com.hanghae.instagram.posting.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PostingMemberTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String memberNickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTING_ID", nullable = false)
    private Posting posting;

    @Builder
    public PostingMemberTag(String memberNickname, Posting posting) {
        this.memberNickname = memberNickname;
        this.posting = posting;

//        posting.getPostingMemberTagList().add(this);
    }
}
