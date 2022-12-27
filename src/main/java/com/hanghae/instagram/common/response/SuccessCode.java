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
    CREATE_POSTING_SUCCESS(OK, "포스팅 등록 성공"),
    SHOW_POSTING_SUCCESS(OK, "전체 포스팅 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;
}