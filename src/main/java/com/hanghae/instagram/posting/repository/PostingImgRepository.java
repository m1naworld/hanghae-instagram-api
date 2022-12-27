package com.hanghae.instagram.posting.repository;

import com.hanghae.instagram.posting.entity.PostingImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingImgRepository extends JpaRepository<PostingImg, Long> {
    List<PostingImg> findAllByPostingId(long id);
}
