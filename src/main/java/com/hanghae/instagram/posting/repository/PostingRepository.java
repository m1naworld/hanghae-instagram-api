package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.Posting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    List<Posting> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @Modifying
    @Query("update Posting p set p.likeCount = :newLikeCount where p.id = :postingId ")
    void updateLikeCount(Long postingId, int newLikeCount);
}
