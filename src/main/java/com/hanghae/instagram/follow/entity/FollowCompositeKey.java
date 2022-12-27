package com.hanghae.instagram.follow.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class FollowCompositeKey implements Serializable {

    @Column(nullable = false)
    private Long followerId;
    @Column(nullable = false)
    private Long followingId;

    public FollowCompositeKey(Long followerId, Long followingId){
        this.followerId = followerId;
        this.followingId = followingId;
    }
}
