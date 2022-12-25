package com.hanghae.instagram.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
// 1. ErrorResponse를 Builder Pattern을 이용해 ResponseEntity로 변환하기 위해 선언
@Builder
public class ErrorResponse {
    private final int status;
    private final String message;

    // 2. ErrorResponse를 Response Entity로 변환하는 Method
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .message(errorCode.getMessage())
                        .build()
                );
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus status, String message ) {
        return ResponseEntity
                .status(status)
                .body(ErrorResponse.builder()
                        .status(status.value())
                        .message(message)
                        .build()
                );
    }

}
