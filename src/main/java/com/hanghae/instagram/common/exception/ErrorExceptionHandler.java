package com.hanghae.instagram.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
// 1. RestControllerAdvice: @ContollerAdvice + @ResponseBody
// 1-1. @ControllerAdvice: @InitBinder, @ModelAttribute, @ExceptionHandler 관련 어노테이션을 여러 컨트롤러에 걸쳐 공통으로 설정 할 수 있게 해주는 어노테이션
// 1-2. assignableTypes: ExceptionHandler가 적용되는 범위를 지정, 조건을 과하게 걸면 성능에 매우 안좋은 영향을 끼칠 수 있다.
@RestControllerAdvice
// 2. ResponseEntityExceptionHandler를 extend한 CustomExceptionHandler 선언
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {
    // 3. ExceptionHandler가 적용될 Exception의 종류를 명시(CustomException)
    @ExceptionHandler(value = { CustomException.class })
            // 4. ResponseEntity 형식으로 ErrorResponse를 변환하여 반환.
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ErrorResponse> handlerRequestNullValueException(PropertyValueException e){

        String staticMessage = " 값이 null로 들어와 데이터를 저장 혹은 수정 할 수 없습니다.";
        String message = e.getPropertyName() + staticMessage;

        return ErrorResponse.toResponseEntity(HttpStatus.BAD_REQUEST, message);


    }
}
