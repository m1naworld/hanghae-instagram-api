package com.hanghae.instagram.like.controller;

import com.hanghae.instagram.common.response.DataResponse;

import com.hanghae.instagram.like.dto.RequestLikeDto;
import com.hanghae.instagram.like.dto.ResponseLikeDto;
import com.hanghae.instagram.like.service.LikeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hanghae.instagram.common.response.SuccessCode.LIKE_CANCEL_SUCCESS;
import static com.hanghae.instagram.common.response.SuccessCode.LIKE_SUCCESS;


@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/posting/{postingId}")
    public ResponseEntity<DataResponse<ResponseLikeDto>> changePostingLikeState(@PathVariable Long postingId, @RequestBody RequestLikeDto requestLike) {
        String nickname = "mina"; // 임시
        ResponseLikeDto responseLike = likeService.changePostingLikeState(requestLike, postingId, nickname);

        if (responseLike.isLike()) {
            return DataResponse.toResponseEntity(LIKE_SUCCESS, responseLike);
        }
        return DataResponse.toResponseEntity(LIKE_CANCEL_SUCCESS, responseLike);
    }

    @PostMapping("/comment/{commentId}")
    public ResponseEntity<DataResponse<ResponseLikeDto>> changeCommentLikeState(@PathVariable Long commentId, @RequestBody RequestLikeDto requestLike) {
        String nickname = "mina"; // 임시
        ResponseLikeDto responseLike = likeService.changeCommentLikeState(requestLike, commentId, nickname);

        if (responseLike.isLike()) {
            return DataResponse.toResponseEntity(LIKE_SUCCESS, responseLike);
        }
        return DataResponse.toResponseEntity(LIKE_CANCEL_SUCCESS, responseLike);
    }
}
