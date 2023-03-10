
package com.hanghae.instagram.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    // User
    SIGNUP_USER_SUCCESS(OK, "회원 가입 성공"),
    LOGIN_USER_SUCCESS(OK, "유저 로그인 성공"),
    LOGIN_KAKAO_SUCCESS(OK, "카카오 로그인 성공"),

    MEMBER_SUCCESS(OK, "멤버 정보 요청 성공"),
    // Like
    LIKE_SUCCESS(OK, "좋아요 성공"),
    LIKE_CANCEL_SUCCESS(OK, "좋아요 취소 성공"),

    // Follow
    FOLLOW_SUCCESS(OK, "팔로우 성공"),
    UNFOLLOW_SUCCESS(OK, "언팔로우 성공"),

    // Posting
    CREATE_POSTING_SUCCESS(OK, "포스팅 등록 성공"),
    SHOW_POSTING_SUCCESS(OK, "전체 포스팅 조회 성공"),
    DELETE_POSTING_SUCCESS(OK, "포스팅 삭제 성공"),

    SHOW_POSTING_DETAIL_SUCCESS(OK, "포스팅 상세 조회 성공"),
    SHOW_POSTING_BY_HASHTAG_SUCCESS(OK, "해쉬태그 검색 성공"),
    CREATE_COMMENT_SUCCESS(OK, "댓글 작성 성공"),
    UPDATE_COMMENT_SUCCESS(OK, "댓글 수정 성공"),
    DELETE_COMMENT_SUCCESS(OK, "댓글 삭제 성공");


    private final HttpStatus httpStatus;
    private final String message;
}