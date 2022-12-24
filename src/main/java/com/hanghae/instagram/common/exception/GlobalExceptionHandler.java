package com.hanghae.instagram.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
// 1. RestControllerAdvice: @ContollerAdvice + @ResponseBody
// 1-1. @ControllerAdvice: @InitBinder, @ModelAttribute, @ExceptionHandler 관련 어노테이션을 여러 컨트롤러에 걸쳐 공통으로 설정 할 수 있게 해주는 어노테이션
// 1-2. assignableTypes: ExceptionHandler가 적용되는 범위를 지정, 조건을 과하게 걸면 성능에 매우 안좋은 영향을 끼칠 수 있다.
@RestControllerAdvice
// 2. ResponseEntityExceptionHandler를 extend한 CustomExceptionHandler 선언
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // 3. ExceptionHandler가 적용될 Exception의 종류를 명시(CustomException)
//    @ExceptionHandler(value = { CustomException.class }
//            // 4. ResponseEntity 형식으로 ErrorResponse를 변환하여 반환.
//            public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
//        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
//        return ErrorResponse.toResponseEntity(e.getErrorCode());
//    }
}
