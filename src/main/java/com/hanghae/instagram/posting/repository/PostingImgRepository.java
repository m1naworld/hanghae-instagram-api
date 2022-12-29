package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.Posting;
import com.hanghae.instagram.posting.entity.PostingImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostingImgRepository extends JpaRepository<PostingImg, Long> {
    List<PostingImg> findAllByPostingId(long id);
    PostingImg findByPostingAndPostingImgNum(Posting posting, long postingImgNum);
    @Transactional
    @Modifying
    @Query("delete from PostingImg where posting.id = :postingId")
    void deleteAllByPostingIdInQuery(@Param("postingId") long postingId);
}
