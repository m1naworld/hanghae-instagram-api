package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.PostingMemberTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingMemberTagRepository extends JpaRepository<PostingMemberTag, Long> {
    List<PostingMemberTag> findAllByPostingId(long postingId);
}
