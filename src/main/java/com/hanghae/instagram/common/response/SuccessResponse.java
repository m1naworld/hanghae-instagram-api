package com.hanghae.instagram.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class SuccessResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String code;
    private final String message;

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
