package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.PostingMemberTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostingMemberTagRepository extends JpaRepository<PostingMemberTag, Long> {
    List<PostingMemberTag> findAllByPostingId(long postingId);
    @Transactional
    @Modifying
    @Query("delete from PostingMemberTag where posting.id = :postingId")
    void deleteAllByPostingIdInQuery(@Param("postingId") long postingId);
}
