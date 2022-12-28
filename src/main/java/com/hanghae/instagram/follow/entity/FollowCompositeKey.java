package com.hanghae.instagram.follow.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class FollowCompositeKey implements Serializable {

    @Column(nullable = false)
    private Long followingId;
    @Column(nullable = false)

    private Long followerId;
    public FollowCompositeKey(Long followingId, Long followerId){
        this.followerId = followerId;
        this.followingId = followingId;
    }
}