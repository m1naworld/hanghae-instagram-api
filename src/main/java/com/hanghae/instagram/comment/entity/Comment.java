package com.hanghae.instagram.comment.entity;

import com.hanghae.instagram.common.entity.Timestamped;
import com.hanghae.instagram.posting.entity.Posting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String nickName;

    @ManyToOne
    @JoinColumn(name = "posting_id")
    private Posting postingId;

    @Column
    private Long memberId;

    private int likeCount;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> commentList;

    public void update(String editComment) {
        this.content = editComment;
    }

    public void updateLikeCount(int currentLikeCount) {
        this.likeCount = currentLikeCount;
    }
}
