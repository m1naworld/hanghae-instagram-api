package com.hanghae.instagram.comment.service;

import com.hanghae.instagram.comment.dto.RequestComment;
import com.hanghae.instagram.comment.dto.ResponseComment;
import com.hanghae.instagram.comment.entity.Comment;
import com.hanghae.instagram.comment.repository.CommentRepository;
import com.hanghae.instagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
//    private final PostingRepository postingRepository;
//    private final MemberRepository memberRepository;

    //댓글 작성
    @Transactional

    public ResponseComment createComment(@PathVariable Long postingId, @RequestBody RequestComment requestDto) {
//        Posting posting = checkPosting(postingId);
        Comment comment = new Comment(postingId, requestDto);
        commentRepository.save(comment);
        return new ResponseComment(postingId, comment);
    }

//    private Posting checkPosting(Long id) {
//        return postingRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("게시물을 찾을 수 없습니다.")
//        );
//    }
}
