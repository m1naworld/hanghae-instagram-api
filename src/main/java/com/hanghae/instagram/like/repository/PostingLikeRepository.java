package com.hanghae.instagram.like.repository;

import com.hanghae.instagram.like.entity.PostingLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostingLikeRepository extends JpaRepository<PostingLike, Long> {

    Optional<PostingLike> findPostingLikeByNicknameAndPostingId(String nickname, Long postingId);

}
