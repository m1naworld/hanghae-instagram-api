package com.hanghae.instagram.common.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
// 1. Field ErrorCode를 인자로 CustomException 객체를 만들기 위해 선언.
@AllArgsConstructor
// 2. Runtime Exception을 extend 하는 CustomException 선언
// 2-1. Runtime Exception : 프로그램의 실행 중에 발생하여, 시스템 환경 혹은 입력받은 변수가 잘못된 경우 발생한다.
// 2-2. 의도적으로 throw 하는 모든 종류의 Exception을 하나의 Exception으로 처리하기 위해 상위에 있는 RuntimeException을 사용.
public class CustomException extends RuntimeException {
    // 3. ResponseEntity를 구성할 때 사용할 Enum ErrorCode 사용
    private final ErrorCode errorCode;
}