package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.PostingHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingHashTagRepository extends JpaRepository<PostingHashTag, Long> {
    List<PostingHashTag> findAllByPostingId(long postingId);
}
