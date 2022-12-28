package com.hanghae.instagram.follow.service;

import com.hanghae.instagram.common.exception.CustomException;
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

import static com.hanghae.instagram.common.exception.ErrorCode.MEMBER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public boolean doFollowAndUnfollow(Member member, RequestFollowDto follow) throws PropertyValueException {

        Member myFollowingMember = memberRepository.findByNickname(follow.getMyFollowingMemberNickname())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        int getFollowerCount = myFollowingMember.getFollowerCount();
        int getFollowingCount = member.getFollowingCount();

        FollowCompositeKey compositeKey = new FollowCompositeKey(member.getId(), myFollowingMember.getId());

        // 팔로우
        if(!follow.isFollowState()){
            Follow newFollow = new Follow(compositeKey, member, myFollowingMember);
            followRepository.save(newFollow);

            memberRepository.updateFollowingCount(getFollowingCount + 1, member.getId());
            myFollowingMember.updateFollowerCount(getFollowerCount + 1);

            return true;
        }

        // 언팔로우
        followRepository.deleteById(compositeKey);

        memberRepository.updateFollowingCount(getFollowingCount -1, member.getId());
        myFollowingMember.updateFollowerCount(myFollowingMember.getFollowerCount() -1);

        return false;
    }



}
