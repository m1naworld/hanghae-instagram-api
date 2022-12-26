package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.PostingImgMemberTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingImgMemberTagRepository extends JpaRepository<PostingImgMemberTag, Long> {
    List<PostingImgMemberTag> findAllByPostingImgId(long id);
}
