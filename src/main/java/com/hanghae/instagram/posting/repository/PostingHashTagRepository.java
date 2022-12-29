package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.Posting;
import com.hanghae.instagram.posting.entity.PostingHashTag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostingHashTagRepository extends JpaRepository<PostingHashTag, Long> {
    List<PostingHashTag> findAllByPostingId(long postingId);
    List<PostingHashTag> findAllByHashtag(String hashtag, Pageable pageable);
    @Transactional
    @Modifying
    @Query("delete from PostingHashTag where posting.id = :postingId")
    void deleteAllByPostingIdInQuery(@Param("postingId") long postingId);
}
