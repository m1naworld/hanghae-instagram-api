package com.hanghae.instagram.member.repository;

import com.hanghae.instagram.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);


    Optional<Member> findByKakaoId(Long kakaoId);

    @Modifying
    @Query("UPDATE member SET followingCount = ?1 WHERE id = ?2")
    void updateFollowingCount(int followingCount, Long memberId);

}
