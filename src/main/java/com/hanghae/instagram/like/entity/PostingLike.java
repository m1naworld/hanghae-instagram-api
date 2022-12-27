package com.hanghae.instagram.like.entity;

import com.hanghae.instagram.posting.entity.Posting;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PostingLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @ManyToOne(fetch = FetchType.EAGER)
    private Posting posting;

    public PostingLike(String nickname, Posting posting) {
        this.nickname = nickname;
        this.posting = posting;

        posting.getPostingLikeList().add(this);
    }
}
