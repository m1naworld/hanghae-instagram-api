package com.hanghae.instagram.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
// 1. Response를 Builder Pattern을 이용해 ResponseEntity로 변환하기 위해 선언
@Builder
public class SuccessResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String code;
    private final String message;

    // 2. Response를 Response Entity로 변환하는 Method
    public static ResponseEntity<SuccessResponse> toResponseEntity(SuccessCode successCode) {
        return ResponseEntity
                .status(successCode.getHttpStatus())
                .body(SuccessResponse.builder()
                        .status(successCode.getHttpStatus().value())
                        .code(successCode.name())
                        .message(successCode.getMessage())
                        .build()
                );
    }
}
