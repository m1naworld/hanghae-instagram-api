package com.hanghae.instagram.comment.repository;

import com.hanghae.instagram.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Transactional
    @Modifying
    @Query("delete from Comment where postingId.id = :postingId")
    void deleteAllByPostingIdInQuery(@Param("postingId") long postingId);
}
