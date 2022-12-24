package com.hanghae.instagram.posting.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class ImgMemberTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String memberNickname;

    @Column(nullable = false)
    private long coordinateX;

    @Column(nullable = false)
    private long coordinateY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTING_IMG_ID")
    private PostingImg postingImg;

}
