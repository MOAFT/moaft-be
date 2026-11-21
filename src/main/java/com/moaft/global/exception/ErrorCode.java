package com.moaft.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력입니다."),

    // Auth
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A001", "유효하지 않은 토큰입니다."),

    // Record
    RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "R001", "기록을 찾을 수 없습니다."),
    RECORD_ACCESS_DENIED(HttpStatus.FORBIDDEN, "R002", "접근할 수 없는 기록입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
