package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.PostingImgMemberTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostingImgMemberTagRepository extends JpaRepository<PostingImgMemberTag, Long> {
    List<PostingImgMemberTag> findAllByPostingImgId(long id);
    @Transactional
    @Modifying
    @Query("delete from PostingImgMemberTag where postingImg.id = :postingImgId")
    void deleteAllByPostingImgIdInQuery(@Param("postingImgId") long postingImgId);
}
