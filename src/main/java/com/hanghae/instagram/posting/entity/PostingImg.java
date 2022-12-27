package com.hanghae.instagram.posting.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class PostingImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String postingImg;

    @Column(nullable = false)
    private long postingImgNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTING_ID", nullable = false)
    private Posting posting;

    @OneToMany(mappedBy = "postingImg", fetch = FetchType.LAZY)
    private List<PostingImgMemberTag> imgMemberTagList = new ArrayList<>();

    @Builder
    public PostingImg(String postingImg, long postingImgNum, Posting posting) {
        this.postingImg = postingImg;
        this.postingImgNum = postingImgNum;
        this.posting = posting;

//        posting.getPostingImgList().add(this);
    }
}
