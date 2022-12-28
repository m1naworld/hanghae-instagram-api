package com.hanghae.instagram.follow.entity;

import com.hanghae.instagram.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@NoArgsConstructor
@Getter
public class Follow {

    @EmbeddedId
    private FollowCompositeKey id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @MapsId("myFollowingId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member myFollowing;

    public Follow(FollowCompositeKey id, Member member, Member myFollowing) {
        this.id = id;
        this.member = member;
        this.myFollowing = myFollowing;
    }
}