package com.hanghae.instagram.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400 : 잘못된 요청
    INVALID_EMAIL(BAD_REQUEST, "유효하지 않은 이메일입니다.", OK),
    INVALID_PASSWORD(BAD_REQUEST, "유효하지 않은 비밀번호입니다.", OK),
    INCORRECT_PASSWORD(BAD_REQUEST, "비밀번호가 일치하지 않습니다.", OK),
    REQUIRED_ALL(BAD_REQUEST,"모든 항목이 필수값입니다.", OK), // 에러코드 고민해봐야 겠음

    DUPLICATE_LIKE_CANCEL(BAD_REQUEST, "이미 좋아요가 취소 되었습니다.", OK),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    JWT_NOT_FOUND(UNAUTHORIZED, "토큰이 존재하지 않습니다.", UNAUTHORIZED),
    INVALID_JWT(UNAUTHORIZED, "유효하지 않은 토큰입니다.", UNAUTHORIZED),
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 일치하지 않는 토큰입니다", UNAUTHORIZED),
    UNAUTHORIZED_USER(UNAUTHORIZED, "작성자만 삭제/수정할 수 있습니다.", UNAUTHORIZED),
    REFRESH_TOKEN_NOT_FOUND(UNAUTHORIZED, "로그아웃 된 사용자입니다", UNAUTHORIZED),


    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */

    EMAIL_NOT_FOUND(NOT_FOUND, "존재하지 않는 이메일 입니다", OK),
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다", NOT_FOUND),
    COMMENT_NOT_FOUND(NOT_FOUND, "해당 댓글을 찾을 수 없습니다", OK),
    FORUM_NOT_FOUND(NOT_FOUND, "해당 게시글을 찾을 수 없습니다", NOT_FOUND),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_EMAIL(CONFLICT, "중복된 이메일입니다.", OK),
    DUPLICATE_NICKNAME(CONFLICT, "중복된 닉네임입니다.", OK);









    // field
    private final HttpStatus bodyStatus;
    private final String message;
    private final HttpStatus httpStatus;
}
