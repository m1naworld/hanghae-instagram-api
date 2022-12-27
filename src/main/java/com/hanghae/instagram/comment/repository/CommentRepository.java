package com.hanghae.instagram.comment.repository;

import com.hanghae.instagram.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
