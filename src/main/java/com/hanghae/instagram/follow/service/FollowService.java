package com.hanghae.instagram.follow.service;

import com.hanghae.instagram.follow.dto.RequestFollowDto;
import com.hanghae.instagram.follow.entity.Follow;
import com.hanghae.instagram.follow.entity.FollowCompositeKey;
import com.hanghae.instagram.follow.repository.FollowRepository;

import com.hanghae.instagram.member.entity.Member;
import com.hanghae.instagram.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.hibernate.PropertyValueException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public boolean doFollowAndUnfollow(RequestFollowDto follow) throws PropertyValueException {

        Member following = memberRepository.findByNickname(follow.getFollowing()).orElseThrow();
        Member follower = memberRepository.findByNickname(follow.getFollower()).orElseThrow();

        FollowCompositeKey compositeKey = new FollowCompositeKey(following.getId(), follower.getId());

        // 팔로우
        if(!follow.isFollowState()){
            Follow newFollow = new Follow(compositeKey, following, follower);
            followRepository.save(newFollow);

            follower.updateFollower(follower.getFollowerCount() + 1);
            following.updateFollowing(following.getFollowingCount() + 1);

            return true;
        }

        // 언팔로우
        followRepository.deleteById(compositeKey);
        follower.updateFollower(follower.getFollowerCount() -1);
        following.updateFollowing(following.getFollowingCount() -1);

        return false;
    }



}
