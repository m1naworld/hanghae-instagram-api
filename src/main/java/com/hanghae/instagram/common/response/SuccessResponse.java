package com.hanghae.instagram.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class SuccessResponse {
    private final int status;
    private final String message;

    public static ResponseEntity<SuccessResponse> toResponseEntity(SuccessCode successCode) {
        return ResponseEntity
                .status(successCode.getHttpStatus())
                .body(SuccessResponse.builder()
                        .status(successCode.getHttpStatus().value())
                        .message(successCode.getMessage())
                        .build()
                );
    }
}
