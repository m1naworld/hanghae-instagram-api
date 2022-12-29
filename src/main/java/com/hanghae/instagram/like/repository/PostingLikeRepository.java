package com.hanghae.instagram.like.repository;

import com.hanghae.instagram.like.entity.PostingLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PostingLikeRepository extends JpaRepository<PostingLike, Long> {
    @Transactional
    @Modifying
    @Query("delete from PostingLike where posting.id = :postingId")
    void deleteAllByPostingIdInQuery(@Param("postingId") long postingId);
    Optional<PostingLike> findPostingLikeByNicknameAndPostingId(String nickname, Long postingId);

}
