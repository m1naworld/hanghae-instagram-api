package com.hanghae.instagram.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class DataResponse<T> {
    private final int status;
    private final String message;
    private T data;

    public static<T> ResponseEntity<DataResponse<T>> toResponseEntity(SuccessCode successCode, T data) {
        return ResponseEntity
                .status(successCode.getHttpStatus())
                .body(DataResponse.<T>builder()
                        .status(successCode.getHttpStatus().value())
                        .message(successCode.getMessage())
                        .data(data)
                        .build()
                );
    }
}