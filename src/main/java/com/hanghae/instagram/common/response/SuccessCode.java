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
    LOGIN_USER_SUCCESS(OK, "유저 로그인 성공");

    private final HttpStatus httpStatus;
    private final String message;
}