package com.hanghae.instagram.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
// 1. Field HttpStatus, message를 인자로 ErrorCode 객체를 만들기 위해 선언
@AllArgsConstructor
// 2. Custom Exception의 Field로 사용될 Enum 값 정의
public enum ErrorCode {
    // 200 : 잘못된 요청
    INVALID_EMAIL(OK, "유효하지 않은 이메일입니다."),
    INVALID_PASSWORD(OK, "유효하지 않은 비밀번호입니다."),
    INCORRECT_PASSWORD(OK, "비밀번호가 일치하지 않습니다."),
    REQUIRED_ALL(OK,"모든 항목이 필수값입니다."), // 에러코드 고민해봐야 겠음

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    JWT_NOT_FOUND(UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    INVALID_JWT(UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 일치하지 않는 토큰입니다"),
    UNAUTHORIZED_USER(UNAUTHORIZED, "작성자만 삭제/수정할 수 있습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    COMMENT_NOT_FOUND(NOT_FOUND, "해당 댓글을 찾을 수 없습니다"),
    FORUM_NOT_FOUND(NOT_FOUND, "해당 게시글을 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_EMAIL(CONFLICT, "중복된 이메일입니다."),
    DUPLICATE_NICKNAME(CONFLICT, "중복된 닉네임입니다.");









    // field
    private final HttpStatus httpStatus;
    private final String message;
}
