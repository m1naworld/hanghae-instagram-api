package com.hanghae.instagram.comment.service;

import com.hanghae.instagram.comment.dto.RequestComment;
import com.hanghae.instagram.comment.dto.ResponseComment;
import com.hanghae.instagram.comment.entity.Comment;
import com.hanghae.instagram.comment.mapper.CommentMapper;
import com.hanghae.instagram.comment.repository.CommentRepository;
import com.hanghae.instagram.member.entity.Member;
import com.hanghae.instagram.posting.entity.Posting;
import com.hanghae.instagram.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostingRepository postingRepository;
    private final CommentMapper commentMapper;

    //댓글 작성
    @Transactional
    public ResponseComment createComment(Long postingId, RequestComment requestDto, Member member) {
        /* board 있는지 확인 */
        Posting posting = checkPosting(postingId);

        /* 댓글 저장 */
        Comment comment = commentMapper.toDepthZeroComment(posting, requestDto, member.getId(), member.getNickname());
        commentRepository.save(comment);

        return new ResponseComment(postingId, comment);
    }

    @Transactional
    public ResponseComment editComment(Long postingId, Long commentId, RequestComment requestDto, Member member) {
        //댓글 확인, 수정
        Comment comment = checkComment(commentId);
        // 유저 확인
        if (comment.getMemberId() != member.getId()) {
            throw new IllegalArgumentException("작성자가 아닙니다.");
        }
        //게시글 확인
        Posting posting = checkPosting(postingId);
        if (comment.getPostingId().getId() != posting.getId()) {
            throw new IllegalArgumentException("게시글에 댓글이 존재하지 않습니다.");
        }
        /* 수정한 댓글 변수에 담기 */
        String editComment = requestDto.getComment();
        comment.update(editComment);
        return new ResponseComment(postingId, comment);
    }

    public void deleteComment(Long postingId, Long commentId, Member member) {

        Comment comment = checkComment(commentId);

        //유저 확인
        if (comment.getMemberId() != member.getId()) {
            throw new IllegalArgumentException("작성자가 아닙니다.");
        }
        //게시글 확인
        Posting posting = checkPosting(postingId);
        if (comment.getPostingId().getId() != posting.getId()) {
            throw new IllegalArgumentException("게시글에 댓글이 존재하지 않습니다.");
        }
        //댓글 삭제
        commentRepository.delete(comment);
    }

    private Posting checkPosting(Long id) {
        return postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
    }
    private Comment checkComment(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
    }
}
