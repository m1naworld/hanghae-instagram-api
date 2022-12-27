package com.hanghae.instagram.like.service;

import com.hanghae.instagram.comment.entity.Comment;
import com.hanghae.instagram.comment.repository.CommentRepository;
import com.hanghae.instagram.common.exception.CustomException;

import com.hanghae.instagram.like.dto.RequestLikeDto;
import com.hanghae.instagram.like.dto.ResponseLikeDto;
import com.hanghae.instagram.like.entity.CommentLike;
import com.hanghae.instagram.like.entity.PostingLike;
import com.hanghae.instagram.like.repository.CommentLikeRepository;
import com.hanghae.instagram.like.repository.PostingLikeRepository;

import com.hanghae.instagram.posting.entity.Posting;
import com.hanghae.instagram.posting.repository.PostingRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import static com.hanghae.instagram.common.exception.ErrorCode.COMMENT_NOT_FOUND;
import static com.hanghae.instagram.common.exception.ErrorCode.DUPLICATE_LIKE_CANCEL;
import static com.hanghae.instagram.common.exception.ErrorCode.FORUM_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostingLikeRepository postingLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final PostingRepository postingRepository;

    @Transactional
    public ResponseLikeDto changePostingLikeState(RequestLikeDto requestLike, Long postingId, String nickname) {

        boolean like = requestLike.isLike();
        System.out.println(like);
        // 좋아요 취소
        if (like) {
            PostingLike postingLikeFind = postingLikeRepository.findPostingLikeByNicknameAndPostingId(nickname, postingId)
                    .orElseThrow(() -> new CustomException(DUPLICATE_LIKE_CANCEL));

            Posting posting = postingLikeFind.getPosting();
            int currentLikeCount = posting.getLikeCount() - 1;
            posting.updateLikeCount(currentLikeCount);

            postingLikeRepository.deleteById(postingLikeFind.getId());
            return new ResponseLikeDto(false, currentLikeCount);
        }

        // 좋아요
        Posting postingFind = postingRepository.findById(postingId)
                .orElseThrow(() -> new CustomException(FORUM_NOT_FOUND));

        PostingLike newPostingLike = new PostingLike(nickname, postingFind);
        postingLikeRepository.save(newPostingLike);
        int currentLikeCount = postingFind.getLikeCount() + 1;

        postingFind.updateLikeCount(currentLikeCount);

        return new ResponseLikeDto(true, currentLikeCount);
    }


    @Transactional
    public ResponseLikeDto changeCommentLikeState(RequestLikeDto requestLike, Long commentId, String nickname) {

        boolean like = requestLike.isLike();

        // 좋아요 취소
        if (like) {
            CommentLike commentLikeFind = commentLikeRepository.findCommentLikeByNicknameAndCommentId(nickname, commentId)
                    .orElseThrow(() -> new CustomException(DUPLICATE_LIKE_CANCEL));

            Comment comment = commentLikeFind.getComment();
            int currentLikeCount = comment.getLikeCount() - 1;
            comment.updateLikeCount(currentLikeCount);

            commentLikeRepository.deleteById(commentLikeFind.getId());
            return new ResponseLikeDto(false, currentLikeCount);
        }

        // 좋아요
        Comment commentFind = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));

        CommentLike newCommentLike = new CommentLike(nickname, commentFind);
        commentLikeRepository.save(newCommentLike);
        int currentLikeCount = commentFind.getLikeCount() + 1;

        commentFind.updateLikeCount(currentLikeCount);

        return new ResponseLikeDto(true, currentLikeCount);
    }

}
