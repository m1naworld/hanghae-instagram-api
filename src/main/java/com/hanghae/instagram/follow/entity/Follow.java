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

    @MapsId("followerId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member follower;

    @MapsId("followingId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member following;

    public Follow(FollowCompositeKey id, Member follower, Member following) {
        this.id = id;
        this.follower = follower;
        this.following = following;
    }
}
