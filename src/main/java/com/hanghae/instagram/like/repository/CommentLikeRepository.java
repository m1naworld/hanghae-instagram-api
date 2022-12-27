package com.hanghae.instagram.like.repository;

import com.hanghae.instagram.like.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findCommentLikeByNicknameAndCommentId(String nickname, Long commentId);
}
