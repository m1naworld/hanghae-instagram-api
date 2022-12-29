package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.Posting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    List<Posting> findAllByOrderByLikeCountDesc(Pageable pageable);
}
