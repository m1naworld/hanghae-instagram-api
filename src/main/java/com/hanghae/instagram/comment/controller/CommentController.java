package com.hanghae.instagram.comment.controller;

import com.hanghae.instagram.comment.dto.RequestComment;
import com.hanghae.instagram.comment.dto.ResponseComment;
import com.hanghae.instagram.comment.service.CommentService;
import com.hanghae.instagram.common.response.DataResponse;
import com.hanghae.instagram.common.response.SuccessCode;
import com.hanghae.instagram.member.entity.Member;
import com.hanghae.instagram.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posting/{postingId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@PathVariable Long postingId, @RequestBody RequestComment requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member loginMember = userDetails.getMember();
        ResponseComment responseComment = commentService.createComment(postingId, requestDto, loginMember);
        return DataResponse.toResponseEntity(SuccessCode.CREATE_COMMENT_SUCCESS, responseComment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable Long postingId, @PathVariable Long commentId, @RequestBody RequestComment requestDto,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        Member loginMember = userDetails.getMember();
        ResponseComment responseComment = commentService.editComment(postingId, commentId, requestDto, loginMember);
        return DataResponse.toResponseEntity(SuccessCode.CREATE_COMMENT_SUCCESS, responseComment);
    }
}
