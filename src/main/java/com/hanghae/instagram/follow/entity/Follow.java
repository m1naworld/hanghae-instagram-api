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

    @MapsId("followingId")
    private Member following;
    @ManyToOne(fetch = FetchType.LAZY)

    @MapsId("followerId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member follower;

    public Follow(FollowCompositeKey id, Member following, Member follower) {
        this.id = id;
        this.following = following;
        this.follower = follower;
    }
}